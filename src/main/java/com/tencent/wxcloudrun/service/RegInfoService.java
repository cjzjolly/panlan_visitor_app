package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface RegInfoService {

  Optional<RegInfoItem> getRegInfoItems( Integer pageNum);


  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);
}
