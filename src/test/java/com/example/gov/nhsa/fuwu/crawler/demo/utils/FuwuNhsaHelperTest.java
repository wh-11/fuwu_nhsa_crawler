package com.example.gov.nhsa.fuwu.crawler.demo.utils;

import com.alibaba.fastjson2.JSONObject;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.AdmdvsDTO;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.FixedHospitalRequestDTO;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.FixedHospitalResponseDTO;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.PagedResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @date: 2024/3/7
 */
@SpringBootTest
class FuwuNhsaHelperTest {

    @Autowired
    private FuwuNhsaHelper fuwuNhsaHelper;

    @Test
    void doRequest() {
        long startTime = System.currentTimeMillis();
        FixedHospitalRequestDTO requestDTO = new FixedHospitalRequestDTO();
        requestDTO.setPageNum(1);
        requestDTO.setPageSize(100);
        requestDTO.setRegnCode("110000");

        String url = "/nthl/api/CommQuery/queryFixedHospital";

        String v = fuwuNhsaHelper.httpRequestHelper(requestDTO, url);
        PagedResponseDTO<FixedHospitalResponseDTO> dto = JSONObject.parseObject(v, new com.alibaba.fastjson2.TypeReference<PagedResponseDTO<FixedHospitalResponseDTO>>() {
        });
        System.out.println("cost time : " + (System.currentTimeMillis() - startTime) / 1000);
        System.out.println(dto);
        System.out.println(v);
        Assertions.assertFalse(dto.getList().isEmpty());
    }


    @Test
    void queryAdmdvsTree() {
        List<AdmdvsDTO> dtoList = fuwuNhsaHelper.queryAdmdvsTree();
        System.out.println(dtoList);
        Assertions.assertFalse(dtoList.isEmpty());
    }
}