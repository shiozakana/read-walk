package com.rtmart.readwalk.article.dto;

import java.util.List;

public class ArticleListDto {

    private List<ArticleDto> dataList;

    private PageInfo pageInfo;

    public List<ArticleDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<ArticleDto> dataList) {
        this.dataList = dataList;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
