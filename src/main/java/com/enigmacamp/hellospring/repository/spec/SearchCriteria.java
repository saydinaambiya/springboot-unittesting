package com.enigmacamp.hellospring.repository.spec;

import com.enigmacamp.hellospring.util.QueryOperator;

public class SearchCriteria {
    private String key;
    private QueryOperator operation;
    private Object value;

    public SearchCriteria(String key, QueryOperator operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public QueryOperator getOperation() {
        return operation;
    }

    public void setOperation(QueryOperator operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
