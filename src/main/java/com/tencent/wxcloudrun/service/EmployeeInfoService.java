package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.EmployeeInfoItem;

public interface EmployeeInfoService {

    /**审查员工登录信息是否正确**/
    boolean employeeInfoCheck(EmployeeInfoItem employeeInfoItem);
}
