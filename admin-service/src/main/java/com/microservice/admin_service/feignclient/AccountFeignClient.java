package com.microservice.admin_service.feignclient;

import com.microservice.admin_service.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "account-service", path = "/api/v1")
public interface AccountFeignClient {
    @GetMapping("/accounts")
    List<AccountDTO> getListAccounts();
}
