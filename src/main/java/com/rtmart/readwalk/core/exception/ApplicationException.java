package com.rtmart.readwalk.core.exception;


import com.rtmart.readwalk.core.response.ResponseType;

/**
 * 自定义应用异常
 */
public class ApplicationException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	private final String code;

    private String message;

    public ApplicationException() {
        super(ResponseType.SYSTEM_EXCEPTION.getMessage());
        this.code = ResponseType.SYSTEM_EXCEPTION.getCode();
        this.message = ResponseType.SYSTEM_EXCEPTION.getMessage();

    }

    public ApplicationException(final String message) {
        super(message);
        this.code = ResponseType.SYSTEM_EXCEPTION.getCode();
    }

    public ApplicationException(final String code, final String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {

        return code;
    }

    @Override
    public String getMessage() {

        return message;
    }

}
