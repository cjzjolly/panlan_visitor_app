package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.RegRequest;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import com.google.gson.Gson;

import java.util.Map;

/**
 * counter控制器
 */
@RestController

public class RegInfoController {

    final RegInfoService regInfoService;
    final Logger logger;
    final Gson gson;

    public RegInfoController(@Autowired RegInfoService regInfoService) {
        this.regInfoService = regInfoService;
        this.logger = LoggerFactory.getLogger(RegInfoController.class);
        this.gson = new Gson();
    }

    /**
     * 登记来访者信息
     *
     * @param request {@link RegRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/reginfo")
    ApiResponse dataFromPhone(@RequestBody RegRequest request) {
        logger.info("/api/reginfo post request, action: {}", request.getAction());
        RegInfoItem regInfoItem = new RegInfoItem();
        try {
            // 将 action 字符串解析为 Map
            Map<String, Object> reginfoMap = gson.fromJson(request.getAction(), Map.class);
            regInfoItem.setCompany((String) reginfoMap.get("company"));
            regInfoItem.setVisitorName((String) reginfoMap.get("visitorName"));
            regInfoItem.setVisitorFromAddr((String) reginfoMap.get("visitorFromAddr"));
            regInfoItem.setVisitorTel((String) reginfoMap.get("visitorTel"));
            regInfoItem.setVisitorCount((int) reginfoMap.get("visitorCount")); // Gson 将数字解析为 Double
            regInfoItem.setVisitorCarID((String) reginfoMap.get("visitorCarID"));
            regInfoItem.setVisitorTime(LocalDateTime.parse((String) reginfoMap.get("visitorTime")));
            regInfoItem.setRemark((String) reginfoMap.get("remark"));
            regInfoItem.setVisitorToAddr((String) reginfoMap.get("visitorToAddr"));
            regInfoItem.setVisitorToApartment((String) reginfoMap.get("visitorToApartment"));
            regInfoItem.setVisitorsReceptionistName((String) reginfoMap.get("visitorsReceptionistName"));
            regInfoItem.setVisitorsReceptionistTel((String) reginfoMap.get("visitorsReceptionistTel"));
            logger.info("/api/reginfo post received success.");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
        regInfoService.insertRegInfo(regInfoItem);
        return ApiResponse.ok(0);
    }

}