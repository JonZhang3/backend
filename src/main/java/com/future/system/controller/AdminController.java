package com.future.system.controller;

import com.future.common.utils.EncryptUtils;
import com.future.system.domain.User;
import com.future.system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.future.common.web.R;
import com.future.system.repository.DictionaryItemRepo;
import com.future.system.repository.DictionaryRepo;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    
    @Autowired
    private DictionaryRepo dictionaryRepository;
    @Autowired
    private DictionaryItemRepo dictionaryItemRepository;

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/test")
    public R test() {
        User user = new User();
        user.setUsername("admin");
        user.setSalt("test");
        user.setPassword(EncryptUtils.encodePassword("admin", "test"));
        userRepository.save(user);
        return R.ok();
    }

}
