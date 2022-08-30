package com.future.system.vo;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class LoginUser {

    private Long id;
    private String username;

    private Set<String> roles = new HashSet<>();

}
