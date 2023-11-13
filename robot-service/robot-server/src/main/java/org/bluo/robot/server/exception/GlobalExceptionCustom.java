package org.bluo.robot.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.boluo.utils.ResponseEntity;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionCustom {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.clientFailure("请求体异常");
    }


    // 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public ResponseEntity bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.clientFailure("参数校验异常", collect);
    }

    // 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.clientFailure("请求体内容不匹配");
    }

    // 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return ResponseEntity.clientFailure("参数校验异常", collect);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.clientFailure("参数类型不匹配");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessExceptionHandle(BusinessException e) {
        log.warn("业务异常: " + e.getMessage());
        return ResponseEntity.clientFailure(e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity otherExceptionHandle(Exception e) {
        log.error("其他异常: {} + {}", e.getClass(), e.getMessage());
        return ResponseEntity.serverFailure("服务器异常..", e.getMessage());
    }

}
