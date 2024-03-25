package com.quarkus.example.dto;

import jakarta.json.bind.annotation.JsonbProperty;

public class FilterRequestDTO {

    @JsonbProperty(value = "sortBy")
    private String sortBy;

    @JsonbProperty(value = "sortDirection")
    private String sortDirection;

    @JsonbProperty(value = "filterValue")
    private String filterValue;

    @JsonbProperty(value = "filterColumn")
    private String filterColumn;

    @JsonbProperty(value = "pageNo")
    private Integer pageNo;

    @JsonbProperty(value = "pageSize")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }
}
