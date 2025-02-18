package com.microservice.department_service.controller;

import com.microservice.department_service.dto.AccountDTO;
import com.microservice.department_service.entity.Account;
import com.microservice.department_service.form.AccountFilterForm;
import com.microservice.department_service.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountService service;

    @GetMapping()
    public Page<AccountDTO> getAllAccounts(
            Pageable pageable,
            @RequestParam(value = "search", required = false) String search,
            AccountFilterForm filterForm) {

        Page<Account> entityPages = service.getAllAccounts(pageable, search, filterForm);

        // convert entities --> dtos
        List<AccountDTO> dtos = modelMapper.map(
                entityPages.getContent(),
                new TypeToken<List<AccountDTO>>() {}.getType());

        Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;
    }

}