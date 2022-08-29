package com.future.system.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserType {

    ADMIN(0, "管理员"),// 管理员
    NORMAL(1, "普通用户")// 普通用户
    ;

    private final Integer value;

    private final String desc;

    UserType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Converter(autoApply = true)
    public static class UserTypeConverter implements AttributeConverter<UserType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(UserType attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public UserType convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                return null;
            }
            return Stream.of(UserType.values())
                .filter(u -> u.getValue().equals(dbData))
                .findFirst()
                .orElse(NORMAL);
        }
    }

}
