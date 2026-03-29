package com.btoy.trial.persistence.base;

import com.btoy.trial.model.base.BaseEnum;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * @created 29/03/2026 ~~ 11:46
 * author: batu
 */
public class FilterCriteria {

    private final String criteriaName;
    private final List<Object> data;
    private final Operation operation;

    public enum Operation implements BaseEnum<String> {
        EQ(0, "="), NEQ(1, "!="), GT(2, ">"), LT(3, "<"), IN(4, "IN"), LIKE(5, "%"), BETWEEN(6, "BETWEEN");

        private final Integer code;
        private final String value;

        Operation(Integer code, String value) {
            this.value = value;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getLabel() {
            return name();
        }

        @Override
        public String getValue() {
            return this.value;
        }
    }

    public static FilterCriteria of(String name, List<Object> data, Operation operation) {
        return new FilterCriteria(name, data, operation);
    }

    public FilterCriteria(String criteriaName, List<Object> data, Operation operation) {
        this.criteriaName = criteriaName;
        this.data = data;
        this.operation = operation;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public List<Object> getData() {
        return Collections.unmodifiableList(this.data);
    }

    public Operation getOperation() {
        return operation;
    }

}
