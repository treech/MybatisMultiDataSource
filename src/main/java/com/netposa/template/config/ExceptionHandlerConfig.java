package com.netposa.template.config;

import com.netposa.template.exception.CustomerException;
import com.netposa.template.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfig {

    /**
     * @param ex Exception
     * @return ExceptionResponse
     * @Title: operateExp
     * @Description: 异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseResult> operateExp(Exception ex) {
        ResponseResult result = new ResponseResult();
        if (ex instanceof CustomerException) {
            CustomerException ex2 = (CustomerException) ex;
            result.setCode(400);
            result.setMessage(ex2.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            //参数验证异常
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            StringBuffer errorMessage = new StringBuffer();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(StringUtils.isBlank(errorMessage.toString()) ? fieldError.getDefaultMessage()
                        : "," + fieldError.getDefaultMessage());
            }
            result.setCode(400);
            result.setMessage(String.format("%s:[%s]", "参数错误", errorMessage.toString()));
        } else {
            Exception exception = (Exception) ex;
            result.setCode(500);
            result.setMessage(exception.getMessage());
        }
        log.error("系统内部异常", ex);
        return ResponseEntity.ok(result);
    }
}