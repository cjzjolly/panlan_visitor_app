package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.RegRequest;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

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
     * 分页地获取当前计数
     * @return API response json
     */
    @PostMapping(value = "/api/getRegInfo")
    ApiResponse getRegInfoCount(@RequestBody RegRequest request) {
        logger.info("/api/getRegInfo get request, action: {}", request.getAction());
        String action = request.getAction();
        try {
            Map<String, Object> pageParams = gson.fromJson(action, Map.class);
            int pageNum = ((Double) pageParams.get("pageNum")).intValue();
            int pageSize = ((Double) pageParams.get("pageSize")).intValue();
            String empolyerName = (String) pageParams.get("empolyerName");
            if (pageSize <= 0) {
                pageSize = 50;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("pageSize", pageSize);
            params.put("offset", (pageNum - 1) * pageSize);
            //cjzmark todo 增加根据当前登录的员工进行，权限为0的可以查看所有预约信息，权限>0只能查看自己所属部门的数据
            params.put("empolyerName", empolyerName);
            Optional<List<RegInfoItem>> infoItem = regInfoService.getRegInfoItems(params);
            String infoStr = infoItem.isPresent() ? gson.toJson(infoItem.get()) : "";
            logger.info("/api/getRegInfo get request, result: {}", infoStr);
            return ApiResponse.ok(infoStr);
        } catch (Exception e) {
            return ApiResponse.error("参数action错误:\n" + e.getMessage());
        }
    }

    /**
     * 以database 表 ID为key获取
     * @return API response json
     */
    @PostMapping(value = "/api/getRegInfoItemsByID")
    ApiResponse getRegInfoByID(@RequestBody RegRequest request) {
        logger.info("/api/getRegInfoItemsByID get request, action: {}", request.getAction());
        String action = request.getAction();
        try {
            Map<String, Object> pageParams = gson.fromJson(action, Map.class);
            int id = ((Double) pageParams.get("ID")).intValue();
            logger.info("/api/getRegInfoItemsByID get infoItem id is: {}", id);

            Optional<List<RegInfoItem>> infoItem = regInfoService.getRegInfoItemsByID(id);
            logger.info("/api/getRegInfoItemsByID get infoItem isPresent: {}", infoItem.isPresent());
            String infoStr = infoItem.isPresent() ? gson.toJson(infoItem.get()) : "";
            logger.info("/api/getRegInfoItemsByID get request, result: {}", infoStr);
            return ApiResponse.ok(infoStr);
        } catch (Exception e) {
            logger.error("/api/getRegInfoItemsByID error, result: {}", e.getMessage());
            return ApiResponse.error("参数action错误:\n" + e.getMessage());
        }
    }


    /**cjzmark todo 通过区域、部门名为筛选条件获取预约记录**/
    @PostMapping(value = "/api/getRegInfoByDept")
    ApiResponse getRegInfoByDept(@RequestBody RegRequest request) {
        logger.info("/api/getRegInfoByDept get request, action: {}", request.getAction());
        String action = request.getAction();
        try {
            Map<String, Object> pageParams = gson.fromJson(action, Map.class);
            int pageNum = ((Double) pageParams.get("pageNum")).intValue();
            int pageSize = ((Double) pageParams.get("pageSize")).intValue();
            String area = (String) pageParams.get("area");
            String dept = (String) pageParams.get("dept");
            if (pageSize <= 0) {
                pageSize = 50;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("pageSize", pageSize);
            params.put("offset", (pageNum - 1) * pageSize);
            //cjzmark todo 增加根据当前登录的员工进行，权限为0的可以查看所有预约信息，权限>0只能查看自己所属部门的数据
            params.put("area", area);
            params.put("dept", dept);
            Optional<List<RegInfoItem>> infoItem = regInfoService.getRegInfoItemsByDepts(params);
            String infoStr = infoItem.isPresent() ? gson.toJson(infoItem.get()) : "";
            logger.info("/api/getRegInfoByDept get request, result: {}", infoStr);
            return ApiResponse.ok(infoStr);
        } catch (Exception e) {
            return ApiResponse.error("参数action错误:\n" + e.getMessage());
        }
    }

    /**
     * 获取总页数
     * 输入pageSize，获取按照pageSize数分页的页数
     * @return API response json
     */
    @PostMapping(value = "/api/getRegInfoPageCount")
    ApiResponse getRegInfoPageCount(@RequestBody RegRequest request) {
        logger.info("/api/getRegInfoPageCount get request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            Map<String, Object> params = gson.fromJson(action, Map.class);
            int pageSize = ((Double) params.get("pageSize")).intValue();
            String empolyerName = (String) params.get("empolyerName");
            Map<String, Object> pageParams = gson.fromJson(action, Map.class);
            pageParams.put("pageSize", pageSize);
            pageParams.put("empolyerName", empolyerName);
            int pageCount = regInfoService.getTotalPages(pageParams);
            logger.info("/api/getRegInfoPageCount result: {}", pageCount);
            return ApiResponse.ok(pageCount);
        } catch (Exception e) {
            logger.error("cjztest /api/getRegInfoPageCount post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 通过区域、部门名获取总页数
     * 输入pageSize，获取按照pageSize数分页的页数
     * @return API response json
     */
    @PostMapping(value = "/api/getRegInfoPageCountByDept")
    ApiResponse getRegInfoPageCountByDept(@RequestBody RegRequest request) {
        logger.info("/api/getRegInfoPageCountByDept get request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            Map<String, Object> params = gson.fromJson(action, Map.class);
            int pageSize = ((Double) params.get("pageSize")).intValue();
            String area = (String) params.get("area");
            String dept = (String) params.get("dept");
            Map<String, Object> pageParams = gson.fromJson(action, Map.class);
            pageParams.put("pageSize", pageSize);
            pageParams.put("area", area);
            pageParams.put("dept", dept);
            int pageCount = regInfoService.getTotalPagesByDept(pageParams);
            logger.info("/api/getRegInfoPageCountByDept result: {}", pageCount);
            return ApiResponse.ok(pageCount);
        } catch (Exception e) {
            logger.error("cjztest /api/getRegInfoPageCountByDept post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
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
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        RegInfoItem regInfoItem = new RegInfoItem();
        try {
            // 将 action 字符串解析为 Map
            Map<String, Object> reginfoMap = gson.fromJson(action, Map.class);
            regInfoItem.setCompany((String) reginfoMap.get("company"));
            regInfoItem.setVisitorName((String) reginfoMap.get("visitorName"));
            regInfoItem.setVisitorFromAddr((String) reginfoMap.get("visitorFromAddr"));
            regInfoItem.setVisitorTel((String) reginfoMap.get("visitorTel"));
            regInfoItem.setVisitorCount((int) Double.parseDouble((String) reginfoMap.get("visitorCount"))); // Gson 将数字解析为 Double
            regInfoItem.setVisitorCarID((String) reginfoMap.get("visitorCarID"));
            try {
                // 定义自定义的日期时间格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                regInfoItem.setVisitorTime(LocalDateTime.parse((String) reginfoMap.get("visitorTime"), formatter));
            } catch (Exception e) {
                logger.error("cjztest Date parsing error: {}", e.getMessage());
            }
            regInfoItem.setRemark((String) reginfoMap.get("remark"));
            regInfoItem.setVisitorToAddr((String) reginfoMap.get("visitorToAddr"));
            regInfoItem.setVisitorToApartment((String) reginfoMap.get("visitorToApartment"));
            regInfoItem.setVisitorsReceptionistName((String) reginfoMap.get("visitorsReceptionistName"));
            regInfoItem.setVisitorsReceptionistTel((String) reginfoMap.get("visitorsReceptionistTel"));
            regInfoService.insertRegInfo(regInfoItem);
            logger.info("/api/reginfo post received success.");
        } catch (Exception e) {
            logger.error("cjztest /api/reginfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.ok(0);
    }



    /**删除某一预约条目**/
    @PostMapping(value = "/api/delreginfo")
    ApiResponse delreginfo(@RequestBody RegRequest request) {
        logger.info("/api/delreginfo post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            // 将 action 字符串解析为 Map
            Map<String, Object> reginfoMap = gson.fromJson(action, Map.class);
            int deleteID = ((Double) reginfoMap.get("deleteid")).intValue();
            regInfoService.deleteRegInfo(deleteID);
        } catch (Exception e) {
            logger.error("cjztest /api/reginfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.ok(0);
    }



}