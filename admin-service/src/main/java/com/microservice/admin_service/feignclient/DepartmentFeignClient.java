package com.microservice.admin_service.feignclient;

import com.microservice.admin_service.dto.DepartmentDTO;
import com.microservice.admin_service.form.DepartmentFilterForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "department-service", path = "/api/v1")
public interface DepartmentFeignClient {

    @GetMapping("/departments")
    Page<DepartmentDTO> getAllDepartments(
            Pageable pageable,
            @RequestParam(name = "search", required = false) String search,
            @SpringQueryMap DepartmentFilterForm filterForm);
}