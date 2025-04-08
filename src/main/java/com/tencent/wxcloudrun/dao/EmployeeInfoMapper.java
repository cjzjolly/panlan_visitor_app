package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeInfoMapper {

  Integer employeeInfoCheck(@Param("empolyerName") String empolyerName, @Param("pwd") String pwd);

}
