package com.future.system.repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameOrEmailOrPhone(String username, String email, String phone);

}
