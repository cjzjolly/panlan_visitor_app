package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.RegInfoItem;

import java.util.Optional;

public interface RegInfoService {
  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);
}
