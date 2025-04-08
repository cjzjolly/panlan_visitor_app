package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

/**员工登记信息**/
@Data
public class EmployeeInfoItem implements Serializable {
    private Integer id;
    /**用户名**/
    private String empolyerName;
    /**用户密码**/
    private String pwd;

    private String emplyerDept;
    private String empolyerArea;
}
