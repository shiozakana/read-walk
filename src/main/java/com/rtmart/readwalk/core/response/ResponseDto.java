package com.rtmart.readwalk.core.response;


import com.rtmart.readwalk.core.exception.ApplicationException;

import java.util.Date;

public class ResponseDto<T> implements IResponse<T> {
	
	private String code;

    private String message;

    private Date time = new Date();

    private Object body;

    public <T> ResponseDto() {

    }

    public <T> ResponseDto(final IResponse<T> response) {

        this.code = response.getCode();
        this.message = response.getMessage();
        this.body = response.getBody();
    }

    public <T> ResponseDto(final IResponse<T> response, final Object body) {

        this.code = response.getCode();
        this.message = response.getMessage();
        this.body = body;
    }

    public  ResponseDto(final String code, final String message) {

        this.code = code;
        this.message = message;
    }

    /**
     * @param code error code
     * @param message error message
     * @param body returned body
     */
    public ResponseDto(final String code, final String message, final Object body) {
        super();
        this.code = code;
        this.message = message;
        this.body = body;
    }

    /**
     * @param e ApplicationException
     */
    public ResponseDto(final ApplicationException e) {

        this.code = e.getCode();
        this.message = e.getMessage();
    }

    @Override
    public String getCode() {

        return code;
    }

    public void setCode(final String code) {

        this.code = code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    public void setMessage(final String message) {

        this.message = message;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(final Date time) {

        this.time = time;
    }

    @Override
    public Object getBody() {

        return body;
    }

    public void setBody(final Object body) {

        this.body = body;
    }

}
