package com.microservice.department_service.service;

import com.microservice.department_service.entity.Department;
import com.microservice.department_service.form.DepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {
    Page<Department> getAllDepartments(Pageable pageable, String search, DepartmentFilterForm filterForm);

    Department getDepartmentById(int id);
}
