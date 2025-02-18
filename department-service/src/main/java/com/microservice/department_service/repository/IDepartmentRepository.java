package com.microservice.department_service.repository;

import com.microservice.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
//    Department findById(int id);
}