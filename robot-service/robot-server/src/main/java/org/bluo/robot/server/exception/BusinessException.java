package org.bluo.robot.server.exception;

/**
 * @author boluo
 * @date 2023/11/13
 */


public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
