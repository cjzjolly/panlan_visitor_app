package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.RegInfoItem;

import java.util.Optional;
import java.util.List;

public interface CounterService {

  Optional<Counter> getCounter(Integer id);

  void upsertCount(Counter counter);

  void clearCount(Integer id);

  /**更新数据库中**/
  void insertRegInfo(RegInfoItem regInfoItem);
}
