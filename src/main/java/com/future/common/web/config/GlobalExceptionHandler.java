package com.future.common.web.config;

import com.future.common.exception.BizException;
import com.future.common.web.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@ConditionalOnWebApplication
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public R handleBizException(BizException e) {
        return R.fail(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        log.error("", e);
        return R.fail(e.getMessage());
    }

}
