package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**访客登记信息**/
@Data
public class RegInfoItem implements Serializable {
  /**公司名**/
  private String company;
  /**来访者**/
  private String visitorName;
  /**来访人地址**/
  private String visitorFromAddr;
  /**来访人联系方式**/
  private String visitorTel;
  /**来访人数**/
  private int visitorCount;
  /**车牌号码**/
  private String visitorCarID;
  /**预约来访时间**/
  private LocalDateTime visitorTime;
  /**备注**/
  private String remark;
  /**到访地址**/
  private String visitorToAddr;
  /**部门名称**/
  private String visitorToApartment;
  /**接待人**/
  private String visitorsReceptionistName;
  /**接待人联系方式**/
  private String visitorsReceptionistTel;
}
