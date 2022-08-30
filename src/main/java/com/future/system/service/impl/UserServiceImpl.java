package com.future.system.service.impl;

import com.future.common.exception.BizException;
import com.future.common.utils.EncryptUtils;
import com.future.system.domain.User;
import com.future.system.dto.UserDto;
import com.future.system.repository.UserRepo;
import com.future.system.service.UserService;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    @Override
    public void resetPassword(UserDto dto) {
        if(StringUtils.isEmpty(dto.getUsername())) {
            throw new BizException("用户名不能为空");
        }
        User user = this.userRepository.findByUsername(dto.getUsername());
        String encodedPassword = EncryptUtils.encodePassword(dto.getOldPassword(), user.getSalt());
        if(!user.getPassword().equals(encodedPassword)) {
            throw new BizException("旧密码输入错误");
        }
        if(StringUtils.isEmpty(dto.getNewPassword())) {
            throw new BizException("新密码不能为空");
        }
        if(dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new BizException("两次输入的密码不一致");
        }
        String password = EncryptUtils.encodePassword(dto.getNewPassword(), user.getSalt());
        user.setPassword(password);
        this.userRepository.update(user);
    }

    @Override
    public User loginFindUser(String username) {
        return this.userRepository.findByUsernameOrEmailOrPhone(username, username, username);
    }

    @Cacheable(key = "#id")
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
