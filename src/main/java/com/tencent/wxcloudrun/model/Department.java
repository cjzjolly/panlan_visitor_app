package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {
    private int id;
    private String department;
    private String departmentIndex;
    /**权限，如果为0可以看所有的登记信息**/
    private Integer power;
}