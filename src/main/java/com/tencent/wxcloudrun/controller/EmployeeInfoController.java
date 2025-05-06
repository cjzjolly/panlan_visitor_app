package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.EmployeeInfoRequest;
import com.tencent.wxcloudrun.model.Department;
import com.tencent.wxcloudrun.model.DepartmentIndex;
import com.tencent.wxcloudrun.model.EmployeeInfoItem;
import com.tencent.wxcloudrun.service.EmployeeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
        try {
            Map<String, Object> employeeInfo = gson.fromJson(request.getAction(), Map.class);
            String empolyerName = ((String) employeeInfo.get("empolyerName")).trim();
            String pwd = ((String) employeeInfo.get("pwd")).trim();
            logger.info("/api/checkEmployeeCountInfo post request, empolyerName: {}", empolyerName);
            logger.info("/api/checkEmployeeCountInfo post request, pwd: {}", pwd);
            Optional<EmployeeInfoItem> employeeInfoItem = employeeInfoService.employeeInfoCheck(empolyerName, pwd);
            if (employeeInfoItem.isPresent()) {
                EmployeeInfoItem infoItem= employeeInfoItem.get();
                logger.info("/api/checkEmployeeCountInfo return test: {}", infoItem.getPwd());
                if (infoItem.getPwd().equals(pwd) && infoItem.getEmpolyerName().equals(empolyerName)) {
                    return ApiResponse.ok(1);
                }
            }
        } catch (Exception e) {
            logger.error("cjztest /api/reginfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.error("错误的用户名或密码.");
    }

    @PostMapping(value = "/api/signinemployeeinfo")
    ApiResponse signUpEmployeeInfo(@RequestBody EmployeeInfoRequest request) {
        logger.info("/api/signinemployeeinfo post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            Map<String, Object> employeeInfo = gson.fromJson(request.getAction(), Map.class);
            String name = (String) employeeInfo.get("empolyerName");
            String pwd = (String) employeeInfo.get("pwd");
            String emplyerDept = (String) employeeInfo.get("emplyerDept");
            String empolyerArea = (String) employeeInfo.get("empolyerArea");
            if (employeeInfoService.employeeInfoExistCheck(name) >= 1) {
                return ApiResponse.error("该用户已存在");
            }
            Map<String, Object> params = new HashMap<>();
            params.put("empolyerName", name);
            params.put("pwd", pwd);
            params.put("emplyerDept", emplyerDept);
            params.put("empolyerArea", empolyerArea);
            employeeInfoService.insertUser(params);
        } catch (Exception e) {
            logger.error("cjztest /api/signinemployeeinfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.ok(0);
    }

    /**用于修改用户账号信息**/
    @PostMapping(value = "/api/modifyUserInfo")
    ApiResponse modifyUserInfo(@RequestBody EmployeeInfoRequest request) {
        logger.info("/api/modifyUserInfo post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            Map<String, Object> employeeInfo = gson.fromJson(request.getAction(), Map.class);
            String name = (String) employeeInfo.get("empolyerName");
            //用户不存在就提醒
            if (employeeInfoService.employeeInfoExistCheck(name) < 1) {
                return ApiResponse.error("该用户不存在");
            }
            //检查旧密码是否正确，不正确直接返回。
            String oldPwd = (String) employeeInfo.get("oldPwd");
            String oldPwdIncorrect = "和原密码不匹配";
            Optional<EmployeeInfoItem> employeeInfoItem = employeeInfoService.employeeInfoCheck(name, oldPwd);
            if (employeeInfoItem.isPresent()) {
                EmployeeInfoItem infoItem= employeeInfoItem.get();
                logger.info("/api/modifyUserInfo return test: {}", infoItem.getPwd());
                if (! (infoItem.getPwd().equals(oldPwd) && infoItem.getEmpolyerName().equals(name))) {
                    return ApiResponse.error(oldPwdIncorrect);
                }
            } else {
                return ApiResponse.error(oldPwdIncorrect);
            }
            String pwd = (String) employeeInfo.get("pwd");
            String emplyerDept = (String) employeeInfo.get("emplyerDept");
            String empolyerArea = (String) employeeInfo.get("empolyerArea");
            Map<String, Object> params = new HashMap<>();
            params.put("empolyerName", name);
            params.put("pwd", pwd);
            params.put("emplyerDept", emplyerDept);
            params.put("empolyerArea", empolyerArea);
            employeeInfoService.modifyUserInfo(params);
        } catch (Exception e) {
            logger.error("cjztest /api/modifyUserInfo post request, error: {}", e.toString());
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.ok(0);
    }

    /**获取可预约区域列表**/
    @PostMapping(value = "/api/getAllDepartmentIndexes")
    ApiResponse getAllDepartmentIndexes(@RequestBody EmployeeInfoRequest request) {
        logger.info("/api/getAllDepartmentIndexes post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            List<DepartmentIndex> departmentIndices = employeeInfoService.getAllDepartmentIndexes();
            if (departmentIndices == null || departmentIndices.isEmpty()) {
                return ApiResponse.error("request action is null");
            }
            String infoStr = gson.toJson(departmentIndices);
            logger.info("/api/getAllDepartmentIndexes get request, result: {}", infoStr);
            return ApiResponse.ok(infoStr);
        } catch (Exception e) {
            return ApiResponse.error("错误:\n" + e.getMessage());
        }
    }

    /**获取预约区域对应的部门表**/
    @PostMapping(value = "/api/getDepartmentOfArea")
    ApiResponse getDepartmentOfArea(@RequestBody EmployeeInfoRequest request) {
        logger.info("/api/getDepartmentOfArea post request, action: {}", request.getAction());
        String action = request.getAction();
        if (action == null) {
            return ApiResponse.error("request action is null");
        }
        try {
            Map<String, Object> map = gson.fromJson(request.getAction(), Map.class);
            String departmentIndex = (String) map.get("departmentIndex");
            List<Department> departments = employeeInfoService.getDepartmentOfIndex(departmentIndex);
            String infoStr = gson.toJson(departments);
            logger.info("/api/getDepartmentOfArea get request, result: {}", infoStr);
            return ApiResponse.ok(infoStr);
        } catch (Exception e) {
            return ApiResponse.error("错误:\n" + e.getMessage());
        }
    }

}
