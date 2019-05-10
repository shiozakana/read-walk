package com.rtmart.readwalk.core.exception;


import com.rtmart.readwalk.core.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 系统异常
 */
public class SystemException extends ApplicationException {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemException.class);

    private static final long serialVersionUID = -8216248010896000768L;

    public static final String DEFAULT_SYSTEM_CODE = ResponseType.SYSTEM_EXCEPTION.getCode();

    public static final String DEFAULT_SYSTEM_MESSAGE = ResponseType.SYSTEM_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    public SystemException() {
        super();
        this.code = DEFAULT_SYSTEM_CODE;
        this.message = DEFAULT_SYSTEM_MESSAGE;
    }

    public SystemException(final String message) {
        super(DEFAULT_SYSTEM_CODE, message);
        this.code = DEFAULT_SYSTEM_CODE;
        this.message = message;
    }

    public SystemException(final String code, final String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public SystemException(final Exception e) {
        super(DEFAULT_SYSTEM_CODE, e.getMessage() == null ? DEFAULT_SYSTEM_CODE : e.getMessage());
        this.code = DEFAULT_SYSTEM_CODE;
        final StringBuilder builder = new StringBuilder();
        builder.append(DEFAULT_SYSTEM_MESSAGE);
        if (e.getMessage() != null) {
            builder.append(": ").append(e.getMessage()); 
        }

        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        if (stringWriter != null) {
            builder.append("\r\n").append(stringWriter.toString()); 
        }
        printWriter.close();
        try {
            stringWriter.close();
        } catch (final IOException e1) {
            LOGGER.error(e.getMessage(), e1);
        }

        this.message = builder.toString();
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
