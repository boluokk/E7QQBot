package org.boluo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * qq用户
 *
 * @author boluo
 * @date 2023/11/12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class QQUser {
    /**
     * qq号
     */
    private long number;
    /**
     * 密码
     */
    private String password;
}
