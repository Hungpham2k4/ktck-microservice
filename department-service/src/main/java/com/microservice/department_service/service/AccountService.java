package com.microservice.department_service.service;

import com.microservice.department_service.entity.Account;
import com.microservice.department_service.form.AccountFilterForm;
import com.microservice.department_service.repository.IAccountRepository;
import com.microservice.department_service.specification.account.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repository;

    @Override
    public Page<Account> getAllAccounts(
            Pageable pageable,
            String search,
            AccountFilterForm filterForm) {

        Specification<Account> where = AccountSpecification.buildWhere(search, filterForm);
        return repository.findAll(where, pageable);
    }
}