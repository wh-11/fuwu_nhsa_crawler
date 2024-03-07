package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

/**
 * @date: 2024/3/6
 */
@Data
public class ResponseDTO {

    /**
     * code == 0 ,即请求成功
     */
    private Integer code;

    /**
     * 返回的加密实体
     */
    private EncryptDTO data;

    /**
     * message
     */
    private String message;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 处理结果
     * type = ”success“
     */
    private String type;
}
