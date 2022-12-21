package com.oio.practice.jdk.extend;

/**
 * @Author: Liqc
 * @Date: 2022/8/31 14:48
 */
public class Children implements Parent{

    private Long id;

    private Long parentId;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
