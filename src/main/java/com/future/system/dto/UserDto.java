package com.future.system.dto;

import com.future.system.domain.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {

    private String oldPassword;// 旧密码
    private String newPassword;// 新密码
    private String confirmPassword;// 确认密码

    private String captcha;// 验证码

}
