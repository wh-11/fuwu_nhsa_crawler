package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

/**
 * 定点医疗机构查询
 * @date: 2024/3/6
 */
@Data
public class FixedHospitalRequestDTO {

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 地区编码
     */
    private String regnCode;

    /**
     * 医疗机构名称
     */
    private String medinsName;

    /**
     * 医疗机构等级
     * 具体等级列表，需要单独查询
     */
    private String medinsLvCode;

    /**
     * 医疗机构类型
     * 具体类型列表，需要单独查询
     */
    private String medinsTypeCode;

    private String openElec;

    private Integer pageNum;

    /**
     * pageSize 最大100
     */
    private Integer pageSize;

    /**
     * queryDataSource = "es"
     */
    private String queryDataSource;
}
