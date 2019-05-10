package com.rtmart.readwalk.core.advice;

import com.rtmart.readwalk.core.exception.ApplicationException;
import com.rtmart.readwalk.core.response.ResponseDto;
import com.rtmart.readwalk.core.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常通知
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler({ ApplicationException.class })
    @ResponseBody
    public ResponseDto systemApplicationExceptionHandler(final ApplicationException e) {

        return ResponseUtil.error(e);
    }

	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResponseDto commonExceptionHandler(Exception e) {
		
		LOGGER.error(e.getMessage(), e);

        return ResponseUtil.error(e);
	}
}
