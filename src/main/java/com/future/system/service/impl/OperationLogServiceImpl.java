package com.future.system.service.impl;

import com.future.system.domain.OperationLog;
import com.future.system.domain.enums.OperationLogType;
import com.future.system.repository.OperationLogRepo;
import com.future.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogRepo repository;

    @Override
    public void addLog(OperationLog log) {
        repository.saveAndFlush(log);
    }

    @Override
    public void addLog(OperationLogType logType, String content) {

    }
}
