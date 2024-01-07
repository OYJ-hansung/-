package com.ssafy.attraction.model;

import java.util.List;

public class PaginationList {
    List<AttractionInfoDto> list;
    int startPage;
    int endPage;
    public PaginationList(){}
    public PaginationList(List<AttractionInfoDto> list, int startPage, int endPage){
        this.list=list;
        this.startPage=startPage;
        this.endPage=endPage;
    }

    public void setList(List<AttractionInfoDto> list) {
        this.list = list;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List<AttractionInfoDto> getList() {
        return list;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }
}
