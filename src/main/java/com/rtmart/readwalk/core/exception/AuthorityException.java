package com.rtmart.readwalk.core.exception;


import com.rtmart.readwalk.core.response.IResponse;
import com.rtmart.readwalk.core.response.ResponseType;

/**
 * 权限异常
 */
public class AuthorityException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_AUTHORITY_CODE = ResponseType.AUTHORITY_EXCEPTION.getCode();

    public static final String DEFAULT_AUTHORITY_MESSAGE = ResponseType.AUTHORITY_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    public AuthorityException() {
        super();
        this.code = DEFAULT_AUTHORITY_CODE;
        this.message = DEFAULT_AUTHORITY_MESSAGE;
    }

    public AuthorityException(final String message) {
        super(DEFAULT_AUTHORITY_CODE, message);
        this.code = DEFAULT_AUTHORITY_MESSAGE;
        this.message = message;
    }

    public AuthorityException(final String code, final String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public AuthorityException(final IResponse response) {
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
