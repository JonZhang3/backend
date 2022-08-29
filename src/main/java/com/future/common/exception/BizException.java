package com.future.common.exception;

/**
 * 业务异常。通常在业务校验发生错误时抛出，不会携带错误堆栈，仅仅为了方便处理业务错误，而不是使用复杂的返回参数
 * <br/>
 * 该异常已在全局异常处理中捕获
 *
 * @see com.future.common.web.config.GlobalExceptionHandler
 */
public class BizException extends RuntimeException {

    private int code = -1;

    public BizException(String message) {
        super(message, null, false, false);
    }

    public BizException(int code, String message) {
        super(message, null, false, false);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
