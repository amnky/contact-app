package com.techlabs.utils;

import java.util.List;

public class PagedResponse <T>{
    private List<T> pagedData;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    public PagedResponse(){}

    public PagedResponse(List<T> pagedData, int page, int size, long totalElements, int totalPages, boolean last) {
        this.pagedData = pagedData;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getPagedData() {
        return pagedData;
    }

    public void setPagedData(List<T> pagedData) {
        this.pagedData = pagedData;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
