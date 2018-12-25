package cn.hotel.entity.model;

import java.io.Serializable;

public class PagerModel<T> implements Serializable {
    private static final long serialVersionUID = -4497082858073190458L;
    private Integer pageSize;
    private Long total;
    private T pageData;

    public PagerModel() {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getPageData() {
        return this.pageData;
    }

    public void setPageData(T pageData) {
        this.pageData = pageData;
    }
}

