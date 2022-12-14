package com.future.system.repository;

import org.springframework.stereotype.Repository;

import com.future.common.jpa.BaseJpaRepository;
import com.future.system.domain.DictionaryItem;

@Repository
public interface DictionaryItemRepo extends BaseJpaRepository<DictionaryItem, Long> {

}
