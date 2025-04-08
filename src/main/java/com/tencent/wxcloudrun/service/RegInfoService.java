package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.RegInfoItem;

public interface RegInfoService {
  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);
}
