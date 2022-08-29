package com.future.system.service;

import com.future.system.domain.OperationLog;
import com.future.system.domain.enums.OperationLogType;

public interface OperationLogService {

    void addLog(OperationLog log);

    void addLog(OperationLogType logType, String content);

}
