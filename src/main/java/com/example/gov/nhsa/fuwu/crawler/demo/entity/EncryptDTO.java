package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

/**
 * 加密的传输实体
 * @date: 2024/3/6
 */
@Data
public class EncryptDTO {

    /**
     * sm4 加密的json请求体
     */
    private EncDataDTO data;

    /**
     * appCode = "T98HPCGN5ZVVQBS8LZQNOAEXVI9GYHKQ"
     * 固定写死字段
     */
    private String appCode;

    /**
     * version = "1.0.0"
     * 固定写死字段
     */
    private String version;

    /**
     * encType = ”SM4“
     * 固定写死字段
     */
    private String encType;

    /**
     * signType = ”SM2“
     * 固定写死字段
     */
    private String signType;

    /**
     * 秒级别的时间戳
     */
    private Long timestamp;

    /**
     * sm2 加密的内容
     */
    private String signData;

    @Data
    public static class EncDataDTO {
        private String encData;
    }
}
