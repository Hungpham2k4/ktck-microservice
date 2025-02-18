package com.microservice.department_service.service;

import com.microservice.department_service.entity.Account;
import com.microservice.department_service.form.AccountFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<Account> getAllAccounts(
            Pageable pageable,
            String search,
            AccountFilterForm filterForm);
}
