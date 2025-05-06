package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.EmployeeInfoMapper;
import com.tencent.wxcloudrun.dao.RegInfoMapper;
import com.tencent.wxcloudrun.model.Department;
import com.tencent.wxcloudrun.model.DepartmentIndex;
import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.EmployeeInfoService;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
  final EmployeeInfoMapper employeeInfoMapper;

  public EmployeeInfoServiceImpl(@Autowired EmployeeInfoMapper employeeInfoMapper) {
    this.employeeInfoMapper = employeeInfoMapper;
  }

  @Override
  public Optional<EmployeeInfoItem> employeeInfoCheck(String empolyerName, String pwd) {
    return Optional.ofNullable(employeeInfoMapper.employeeInfoCheck(empolyerName, pwd));
  }

  @Override
  public int employeeInfoExistCheck(String empolyerName) {
    return employeeInfoMapper.employeeInfoExistCheck(empolyerName);
  }


  @Override
  public void insertUser(Map<String, Object> params) {
    employeeInfoMapper.insertUser(params);
  }

  @Override
  public void modifyUserInfo(Map<String, Object> params) {
    employeeInfoMapper.modifyUserInfo(params);
  }

  @Override
  public List<DepartmentIndex> getAllDepartmentIndexes() {
    return employeeInfoMapper.getAllDepartmentIndexes();
  }

  @Override
  public List<Department> getDepartmentOfIndex(String departmentIndex) {
    return employeeInfoMapper.getDepartmentOfIndex(departmentIndex);
  }
}
