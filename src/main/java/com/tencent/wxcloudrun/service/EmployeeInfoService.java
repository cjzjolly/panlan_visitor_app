package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface EmployeeInfoService {

    /**审查员工登录信息是否正确**/
    Optional<EmployeeInfoItem> employeeInfoCheck(String empolyerName, String pwd);
}
