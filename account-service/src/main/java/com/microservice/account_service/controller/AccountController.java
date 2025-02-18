package com.microservice.account_service.controller;

import com.microservice.account_service.dto.AccountDTO;
import com.microservice.account_service.dto.AccountRequestDTO;
import com.microservice.account_service.dto.DepartmentDTO;
import com.microservice.account_service.dto.ResponseAPIDTO;
import com.microservice.account_service.entity.Account;
import com.microservice.account_service.entity.Department;
import com.microservice.account_service.feignclient.DepartmentFeignClient;
import com.microservice.account_service.service.IAccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    private final IAccountService acService;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final DepartmentFeignClient departmentFeignClient;

    @GetMapping
    public List<AccountDTO> getListAccounts() {

        List<Account> accounts = acService.getListAccounts();

        List<AccountDTO> listAccountDTO = modelMapper.map(
                accounts,
                new TypeToken<List<AccountDTO>>() {}.getType());

        return listAccountDTO;
    }

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @CircuitBreaker(name = "departmentService", fallbackMethod = "fallbackNotCallDepartmentService")
    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentByAccountId(@PathVariable final int id) {
        Account account = acService.findAccountById(id);
        int departmentId = account.getDepartment().getId();
//        DepartmentDTO departmentDTO = restTemplate.getForObject("http://localhost:8080/api/v1/departments/" + departmentId, DepartmentDTO.class);
        DepartmentDTO departmentDTO = departmentFeignClient.getDepartmentById(departmentId);
        log.info("Department: {}", departmentDTO);
        return departmentDTO;
    }

    public String fallbackNotCallDepartmentService(int id, Throwable throwable) {
        return "Department Servers Down";
    }

    @PostMapping
    public ResponseAPIDTO<AccountDTO> createAccount(@RequestBody AccountRequestDTO acRequestDTO) {
        Account account = modelMapper.map(acRequestDTO, Account.class);
        Account ac = acService.createAccount(account);

        return ResponseAPIDTO.<AccountDTO>builder()
                .result(modelMapper.map(ac, AccountDTO.class))
                .build();
    }
}