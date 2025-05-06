package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Department;
import com.tencent.wxcloudrun.model.DepartmentIndex;
import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeInfoService {

    /**审查员工登录信息是否正确**/
    Optional<EmployeeInfoItem> employeeInfoCheck(String empolyerName, String pwd);

    int employeeInfoExistCheck(String empolyerName);

    void insertUser(Map<String, Object> params);

    void modifyUserInfo(Map<String, Object> params);

    List<DepartmentIndex> getAllDepartmentIndexes();

    List<Department> getDepartmentOfIndex(String departmentIndex);
}
