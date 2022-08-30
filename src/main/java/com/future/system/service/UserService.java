package com.future.system.service;

import com.future.system.domain.User;
import com.future.system.dto.UserDto;

public interface UserService {

    /**
     * 重置密码
     *
     * @param userDto username oldPassword newPassword condirmPassword
     */
    void resetPassword(UserDto userDto);

    User findById(Long id);

    User loginFindUser(String username);

}
