package com.microservice.department_service.specification.account;

import com.microservice.department_service.entity.Account;
import com.microservice.department_service.form.AccountFilterForm;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(String search, AccountFilterForm filterForm) {
        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification username = new CustomSpecification("username", search);
            CustomSpecification departmentName = new CustomSpecification("departmentName", search);
            where = Specification.where(username).or(departmentName);
        }

        if (filterForm != null && filterForm.getMinId() != null) {
            CustomSpecification minId = new CustomSpecification("minId", filterForm.getMinId());
            if (where == null) {
                where = minId;
            } else {
                where = where.and(minId);
            }
        }

        if (filterForm != null && filterForm.getMaxId() != null) {
            CustomSpecification maxId = new CustomSpecification("maxId", filterForm.getMaxId());
            if (where == null) {
                where = maxId;
            } else {
                where = where.and(maxId);
            }
        }

        return where;
    }
}

class CustomSpecification implements Specification<Account> {
    @NonNull
    private String field;
    @NonNull
    private Object value;

    public CustomSpecification(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(
            Root<Account> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("username")) {
            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
        }
        if (field.equalsIgnoreCase("minId")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), Integer.parseInt(value.toString()));
        }
        if (field.equalsIgnoreCase("maxId")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("id"), Integer.parseInt(value.toString()));
        }
        if (field.equalsIgnoreCase("departmentName")) {
            return criteriaBuilder.like(root.get("department").get("name"), "%" + value.toString() + "%");
        }
        return null;
    }
}