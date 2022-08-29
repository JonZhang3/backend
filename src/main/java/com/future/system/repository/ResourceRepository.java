package com.future.system.repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.Resource;
import com.future.system.domain.enums.ResourceType;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends BaseJpaRepository<Resource, Long> {

    List<Resource> findByTypeIn(List<ResourceType> types);

}
