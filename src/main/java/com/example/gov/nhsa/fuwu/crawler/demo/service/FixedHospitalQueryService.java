package com.example.gov.nhsa.fuwu.crawler.demo.service;


import com.example.gov.nhsa.fuwu.crawler.demo.entity.FixedHospitalRequestDTO;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.FixedHospitalResponseDTO;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.PagedResponseDTO;

/**
 * @date: 2024/3/6
 */
public interface FixedHospitalQueryService {

    PagedResponseDTO<FixedHospitalResponseDTO> query(FixedHospitalRequestDTO requestDTO);
}
