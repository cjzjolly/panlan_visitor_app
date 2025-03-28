package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.dto.RegRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController

public class RegInfoController {

  final RegInfoService regInfoService;
  final Logger logger;

  public RegInfoController(@Autowired RegInfoService regInfoService) {
    this.regInfoService = regInfoService;
    this.logger = LoggerFactory.getLogger(RegInfoController.class);
  }

  /**
   * 登记来访者信息
   * @param request {@link RegRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/reginfo")
  ApiResponse dataFromPhone(@RequestBody RegRequest request) {
    logger.info("/api/reginfo post request, action: {}", request.getAction());
    RegInfoItem regInfoItem = new RegInfoItem();
    JSONObject reginfoJson = null;
    try {
        reginfoJson = new JSONObject(request.getAction());
        regInfoItem.setCompany(reginfoJson.getString("company"));
        regInfoItem.setVisitorName(reginfoJson.getString("visitorName"));
        regInfoItem.setVisitorFromAddr(reginfoJson.getString("visitorFromAddr"));
        regInfoItem.setVisitorTel(reginfoJson.getString("visitorTel"));
        regInfoItem.setVisitorCount(reginfoJson.getInt("visitorCount"));
      regInfoItem.setVisitorCarID(reginfoJson.getString("visitorCarID"));
      regInfoItem.setVisitorTime(LocalDateTime.parse(reginfoJson.getString("visitorTime")));
      regInfoItem.setRemark(reginfoJson.getString("remark"));
      regInfoItem.setVisitorToAddr(reginfoJson.getString("visitorToAddr"));
      regInfoItem.setVisitorToApartment(reginfoJson.getString("visitorToApartment"));
      regInfoItem.setVisitorsReceptionistName(reginfoJson.getString("visitorsReceptionistName"));
      regInfoItem.setVisitorsReceptionistTel(reginfoJson.getString("visitorsReceptionistTel"));
      logger.info("/api/reginfo post received success.");
    } catch (JSONException e) {
        return ApiResponse.error(e.getMessage());
    }
    regInfoService.insertRegInfo(regInfoItem);
    return ApiResponse.ok(0);
  }
  
}