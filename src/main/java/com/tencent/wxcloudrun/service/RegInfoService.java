package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RegInfoService {

  Optional<List<RegInfoItem>> getRegInfoItems(Map<String, Object> params);

  Optional<List<RegInfoItem>> getRegInfoItemsByDepts(Map<String, Object> params);

  Optional<List<RegInfoItem>> getRegInfoItemsByID(Integer id);

  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);

  // 计算总页数
  int getTotalPages(Map<String, Object> params);

  int getTotalPagesByDept(@Param("params") Map<String, Object> params);

  /**取消预约**/
  void deleteRegInfo(Integer id);
}
