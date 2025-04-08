package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.EmployeeInfoMapper;
import com.tencent.wxcloudrun.dao.RegInfoMapper;
import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.EmployeeInfoService;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
  final EmployeeInfoMapper employeeInfoMapper;

  public EmployeeInfoServiceImpl(@Autowired EmployeeInfoMapper employeeInfoMapper) {
    this.employeeInfoMapper = employeeInfoMapper;
  }

  @Override
  public boolean employeeInfoCheck(EmployeeInfoItem employeeInfoItem) {
      return employeeInfoMapper.employeeInfoCheck(employeeInfoItem.getEmpolyerName(), employeeInfoItem.getPwd()) >= 1;
  }
}
