package com.microservice.department_service.controller;

import com.microservice.department_service.dto.DepartmentDTO;
import com.microservice.department_service.entity.Department;
import com.microservice.department_service.form.DepartmentFilterForm;
import com.microservice.department_service.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public Page<DepartmentDTO> getAllDepartments(
            Pageable pageable,
            @RequestParam(name = "search", required = false) String search,
            DepartmentFilterForm filterForm) {
        Page<Department> entityPages = service.getAllDepartments(pageable, search, filterForm);

        // convert entities --> dtos
        List<DepartmentDTO> dtos = modelMapper.map(
                entityPages.getContent(),
                new TypeToken<List<DepartmentDTO>>() {}.getType());

        Page<DepartmentDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;

    }

    @GetMapping(value = "/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable(name = "id") int id) {
        Department department = service.getDepartmentById(id);

        // convert entity to dto
        DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);

        return dto;
    }
}