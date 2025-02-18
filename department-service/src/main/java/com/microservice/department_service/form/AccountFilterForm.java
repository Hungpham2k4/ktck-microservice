package com.microservice.department_service.form;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AccountFilterForm {
    private Integer minId;
    private Integer maxId;

}