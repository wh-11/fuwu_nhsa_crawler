package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @date: 2024/3/7
 * 省市 两级区域
 */
@Data
public class AdmdvsDTO {

    /**
     * 区域等级
     */
    private String admdvsLv;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 区域名称
     */
    private String name;

    /**
     * prntCode = 100000
     */
    private String prntCode;

    /**
     * 下级区域
     */
    private List<AdmdvsDTO> list;
}
