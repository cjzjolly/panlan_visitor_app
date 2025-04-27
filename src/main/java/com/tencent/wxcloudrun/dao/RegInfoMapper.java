package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegInfoMapper {

  List<RegInfoItem> getRegInfoItems(@Param("params") Map<String, Object> params);

  void insertRegInfo(@Param("params") RegInfoItem regInfoItem);

  // 计算总页数
  int getTotalPages(@Param("params") Map<String, Object> params);

  void deleteRegInfo(@Param("id") Integer id);
}
