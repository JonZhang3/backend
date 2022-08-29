package com.future.common.jpa;

import org.hibernate.dialect.MySQL8Dialect;

public class CustomDialect extends MySQL8Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC ";
    }
}
