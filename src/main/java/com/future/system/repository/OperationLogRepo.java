package com.future.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future.system.domain.OperationLog;

@Repository
public interface OperationLogRepo extends JpaRepository<OperationLog, Long> {

    

}
