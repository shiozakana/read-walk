package com.rtmart.readwalk.core.exception;


import com.rtmart.readwalk.core.response.ResponseType;

/**
 * 参数异常
 */
public class ParameterException extends ApplicationException {

    private static final long serialVersionUID = 6257945742204588120L;

    public static final String DEFAULT_PARAM_CODE = ResponseType.PARAMETER_EXCEPTION.getCode();

    public static final String DEFAULT_PARAM_MESSAGE = ResponseType.PARAMETER_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    public ParameterException() {
        super();
        this.code = DEFAULT_PARAM_CODE;
        this.message = DEFAULT_PARAM_MESSAGE;
    }

    public ParameterException(final String message) {
        super(DEFAULT_PARAM_CODE, message);
        this.code = DEFAULT_PARAM_CODE;
        this.message = message;
    }

    public ParameterException(final String code, final String message) {
        super(code, message);
        this.code = code;
        this.message = message;
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
