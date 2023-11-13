package org.boluo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boluo
 * @date 2023/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity {
    private int code;
    private String message;
    private Object data;

    public static ResponseEntity success(String message, Object data) {
        return new ResponseEntity(200, message, data);
    }

    public static ResponseEntity success(String message) {
        return new ResponseEntity(200, message, null);
    }

    public static ResponseEntity success(int code, String message, Object data) {
        return new ResponseEntity(code, message, data);
    }


    public static ResponseEntity serverFailure(String message, Object data) {
        return new ResponseEntity(500, message, data);
    }

    public static ResponseEntity clientFailure(String message, Object data) {
        return new ResponseEntity(400, message, data);
    }

    public static ResponseEntity clientFailure(String message) {
        return new ResponseEntity(400, message, null);
    }

}
