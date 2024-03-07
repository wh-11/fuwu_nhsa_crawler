package com.example.gov.nhsa.fuwu.crawler.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @date: 2024/3/6
 */
@Data
public class PagedResponseDTO<T> {

    private Integer startRow;

    private Integer prePage;

    private Boolean hasNextPage;

    private Integer nextPage;

    private Integer pageSize;

    private Integer endRow;

    private List<T> list;

    private Integer pageNum;

    private Integer navigatePages;

    private Integer total;

    private Integer navigateFirstPage;

    private Integer pages;

    private Integer size;

    private Boolean isLastPage;

    private Boolean hasPreviousPage;

    private Integer navigateLastPage;

    private Boolean isFirstPage;

}
