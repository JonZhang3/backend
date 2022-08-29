package com.future.system.domain.enums;

public enum OperationLogType {

    LOGIN(0, "登录"),
    LOGOUT(1, "登出"),
    ;

    private final Integer value;
    private final String name;

    OperationLogType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
