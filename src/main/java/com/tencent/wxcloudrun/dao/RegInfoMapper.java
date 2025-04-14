package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegInfoMapper {

  RegInfoItem getRegInfoItems(@Param("pageNum") Integer pageNum);

  void insertRegInfo(RegInfoItem regInfoItem);
}
