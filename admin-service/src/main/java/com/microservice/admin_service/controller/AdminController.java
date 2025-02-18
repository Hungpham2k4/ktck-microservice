package com.microservice.admin_service.controller;

import com.microservice.admin_service.dto.AccountDTO;
import com.microservice.admin_service.dto.DepartmentDTO;
import com.microservice.admin_service.feignclient.AccountFeignClient;
import com.microservice.admin_service.feignclient.DepartmentFeignClient;
import com.microservice.admin_service.form.DepartmentFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private DepartmentFeignClient departmentFeignClient;
    @Autowired
    private AccountFeignClient accountFeignClient;

    @GetMapping("/departments")
    public Page<DepartmentDTO> getAllDepartments(
            Pageable pageable,
            @RequestParam(name = "search", required = false) String search,
            DepartmentFilterForm filterForm) {

        return departmentFeignClient.getAllDepartments(pageable, search, filterForm);
    }


    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountFeignClient.getListAccounts();
    }
}
