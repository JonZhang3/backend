package com.future.system.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum State {

    VALID(1),// 有效的
    INVALID(0),// 无效的
    LOCKED(-1)// 锁定的
    ;

    private final Integer value;

    State(Integer value) {
        this.value = value;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class StateConverter implements AttributeConverter<State, Integer> {

        @Override
        public Integer convertToDatabaseColumn(State attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public State convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                return null;
            }
            return Stream.of(State.values())
                .filter(s -> s.getValue().equals(dbData))
                .findFirst()
                .orElse(INVALID);
        }
    }

}
