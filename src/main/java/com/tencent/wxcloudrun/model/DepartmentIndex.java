package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentIndex implements Serializable {
    private int id;
    private String indexName;
}