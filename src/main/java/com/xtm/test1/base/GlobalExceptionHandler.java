package com.xtm.test1.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size : 1MB}")
    private String maxFileSize;

    @ExceptionHandler(value = Exception.class)
    public CommonResult handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        if (e.getClass().equals(MaxUploadSizeExceededException.class)) {
            return CommonResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(),"上传文件大小限制" + maxFileSize);
        }
        return  CommonResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统内部异常");
    }

    @ExceptionHandler(value = CommonException.class)
    public CommonResult handleException(CommonException e) {
        log.error("系统错误", e);
        return CommonResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    public CommonResult validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return CommonResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(),message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return CommonResult.build(HttpStatus.BAD_REQUEST.value(),message.toString());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public CommonResult handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException", e);
        return CommonResult.build(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ExceptionHandler(value = ExpiredSessionException.class)
    public CommonResult handleExpiredSessionException(ExpiredSessionException e) {
        log.error("ExpiredSessionException", e);
        return CommonResult.build(HttpStatus.UNAUTHORIZED.value(),"访问超时");
    }
}