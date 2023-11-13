package org.boluo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author boluo
 * @date 2023/11/12
 */
@Data
@Accessors(chain = true)
public class Message {
    /*
     * 信息
     */
    private String info;
    /**
     * 数据
     */
    private String data;
    /**
     * 接收者
     */
    private long to;
}
