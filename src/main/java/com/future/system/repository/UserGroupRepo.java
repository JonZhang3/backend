package com.future.system.repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.UserGroup;

import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepo extends BaseJpaRepository<UserGroup, Long> {
}
