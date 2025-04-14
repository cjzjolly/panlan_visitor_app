package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RegInfoService {

  Optional<List<RegInfoItem>> getRegInfoItems(Map<String, Object> params);


  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);
}
