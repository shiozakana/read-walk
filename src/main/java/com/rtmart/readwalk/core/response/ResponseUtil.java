package com.rtmart.readwalk.core.response;


import com.rtmart.readwalk.core.exception.ApplicationException;
import com.rtmart.readwalk.core.exception.SystemException;

public final class ResponseUtil {

    private ResponseUtil() {

    }

    public static <T> ResponseDto<T> success() {

        return new ResponseDto(ResponseType.SUCCESS);
    }

    public static <T> ResponseDto<T> success(final T body) {

        return new ResponseDto(ResponseType.SUCCESS, body);
    }

    public static <T>  ResponseDto<T> error(final String code, final String message) {

        return new ResponseDto(code, message);
    }

    public static ResponseDto error(final ApplicationException e) {

        return new ResponseDto(e);
    }

    public static <T> ResponseDto<T> error(final Exception e) {

        return error(new SystemException(e));
    }

}
