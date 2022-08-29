package com.future.system.controller;

import com.future.common.config.FrameworkConfig;
import com.future.common.utils.EncryptUtils;
import com.future.common.utils.TokenUtils;
import com.future.common.web.R;
import com.future.system.domain.Role;
import com.future.system.domain.User;
import com.future.system.domain.enums.OperationLogType;
import com.future.system.domain.enums.State;
import com.future.system.dto.UserDto;
import com.future.system.service.OperationLogService;
import com.future.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogService logService;

    @Autowired
    private FrameworkConfig frameworkConfig;

    @PostMapping("/api/login")
    private R login(UserDto dto) {
        User user = userService.loginFindUser(dto.getUsername());
        if(user == null) {
            logService.addLog(OperationLogType.LOGIN, "登录失败，用户[" + dto.getUsername() + "]不存在");
            return R.fail("该用户不存在");
        }
        if(user.getState() == State.INVALID) {
            logService.addLog(OperationLogType.LOGIN, "登录失败，用户 [" + user.getUsername() + "] 已注销");
            return R.fail("该用户已注销");
        }
        if(user.getState() == State.LOCKED) {
            logService.addLog(OperationLogType.LOGIN, "登录失败，用户 [" + user.getUsername() + "] 已冻结");
            return R.fail("该用户被冻结");
        }
        String password = EncryptUtils.encodePassword(dto.getPassword(), user.getSalt());
        if(!user.getPassword().equals(password)) {
            return R.fail("用户名或密码错误");
        }
        // 填充用户信息
        Map<String, Object> userInfo = userInfo(user);
        logService.addLog(OperationLogType.LOGIN, "用户 [" + user.getUsername() + "] 登录成功");
        return R.ok(userInfo);
    }

    private Map<String, Object> userInfo(User user) {
        Map<String, Object> data = new HashMap<>();

        FrameworkConfig.Jwt jwt = frameworkConfig.getJwt();
        if(jwt == null) {
            jwt = new FrameworkConfig.Jwt();
        }
        data.put("user", "");
        // 角色
        Set<Role> roles = user.getRoles();
        data.put("roles", roles);
        // 菜单

        data.put("menus", "");
        Map<String, Object> payload = new HashMap<>();
        payload.put("userid", user.getId());
        payload.put("username", user.getUsername());
        String token = TokenUtils.generate(payload, jwt.getDuration(), jwt.getSecret());
        data.put("token", token);
        return data;
    }

    public void logout() {

    }



}
