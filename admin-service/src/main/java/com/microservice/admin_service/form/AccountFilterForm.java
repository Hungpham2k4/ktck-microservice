package com.microservice.admin_service.form;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AccountFilterForm {
    private Integer minId;
    private Integer maxId;

}