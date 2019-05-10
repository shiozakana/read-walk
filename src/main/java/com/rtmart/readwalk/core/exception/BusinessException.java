package com.rtmart.readwalk.core.exception;


import com.rtmart.readwalk.core.response.IResponse;
import com.rtmart.readwalk.core.response.ResponseType;

/**
 * 业务异常
 */
public class BusinessException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_BUSINESS_CODE = ResponseType.BUSINESS_EXCEPTION.getCode();

    public static final String DEFAULT_BUSINESS_MESSAGE = ResponseType.BUSINESS_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    public BusinessException() {
        super();
        this.code = DEFAULT_BUSINESS_CODE;
        this.message = DEFAULT_BUSINESS_MESSAGE;
    }

    public BusinessException(final String message) {
        super(DEFAULT_BUSINESS_CODE, message);
        this.code = DEFAULT_BUSINESS_CODE;
        this.message = message;
    }

    public BusinessException(final String code, final String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(final IResponse response) {
        super(response.getCode(), response.getMessage());
        this.code = response.getCode();
        this.message = response.getMessage();
    }

    @Override
    public String getCode() {

        return code;
    }

    @Override
    public String getMessage() {

        return message;
    }

}
