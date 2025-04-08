package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.EmployeeInfoRequest;
import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.service.EmployeeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmployeeInfoController {

    final EmployeeInfoService employeeInfoService;
    final Logger logger;
    final Gson gson;

    public EmployeeInfoController(@Autowired EmployeeInfoService employeeInfoService) {
        this.employeeInfoService = employeeInfoService;
        this.logger = LoggerFactory.getLogger(EmployeeInfoController.class);
        this.gson = new Gson();
    }

    /**
     * 核对员工登录信息是否正确
     *
     * @param request {@link EmployeeInfoRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/checkEmployeeCountInfo")
    ApiResponse checkEmployeeCountInfo(@RequestBody EmployeeInfoRequest request) {
        //cjzmark todo 这里暂时不安全，做好要弥补
        logger.info("/api/checkEmployeeCountInfo post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        boolean isRight = false;
        try {
            Map<String, Object> employeeInfo = gson.fromJson(request.getAction(), Map.class);
            isRight = employeeInfoService.employeeInfoCheck((String) employeeInfo.get("name"), (String) employeeInfo.get("pwd")).isPresent();
        } catch (Exception e) {
            logger.error("cjztest /api/reginfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.ok(isRight ? 1 : 0);
    }
}
