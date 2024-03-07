package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

/**
 * 定点医疗机构查询
 * @date: 2024/3/6
 */
@Data
public class FixedHospitalResponseDTO {

    private String medinsTypeName;

    private String medinsNatu;

    private String hospLv;

    private String medinsType;

    private String uscc;

    private String openElec;

    private String businessLvEpc;

    private String lnt;

    private String businessLvCfc;

    private String businessLvMpc;

    private String medinsLvName;

    private String medinsLv;

    private String medinsName;

    private String addr;

    private String medinsCode;

    private String lat;

    private String businessLvEbc;
}
