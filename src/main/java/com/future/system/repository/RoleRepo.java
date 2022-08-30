package com.future.system.repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.Role;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends BaseJpaRepository<Role, Long> {
}
