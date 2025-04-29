package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EmployeeInfoMapper {

  EmployeeInfoItem employeeInfoCheck(@Param("empolyerName") String empolyerName, @Param("pwd") String pwd);

  int employeeInfoExistCheck(@Param("empolyerName") String empolyerName);

  void insertUser(@Param("params") Map<String, Object> params);

  void modifyUserInfo(@Param("params") Map<String, Object> params);

}
