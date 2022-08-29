package com.future.system.repository;

import org.springframework.stereotype.Repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.Dictionary;

@Repository
public interface DictionaryRepository extends BaseJpaRepository<Dictionary, Long> {

}
