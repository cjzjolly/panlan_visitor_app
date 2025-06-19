function init() {

}
    
    function set(action) {
      $.ajax("/api/getRegInfoItemsByID", {
        method: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify({
          action: action,
        }),
      }).done(function (res) {
        if (res && res.data !== undefined) {
          const data = res.data;
          console.log("test test test:", data);
          // 填写表格
          $('td:contains("访客信息") + td').text(data.visitorInfo || '');
          $('td:contains("来访者") + td').text(data.visitorName || '');
          $('td:contains("来访者公司名") + td').text(data.company || '');
          $('td:contains("来访人地址") + td').text(data.visitorFromAddr || '');
          $('td:contains("来访人联系方式") + td').text(data.visitorTel || '');
          $('td:contains("来访人数") + td').text(data.visitorCount || '');
          $('td:contains("车牌号码") + td').text(data.visitorCarID || '');
          $('td:contains("预约来访时间") + td').text(data.visitorTime || '');
          $('td:contains("到访地址") + td').text(data.visitorToAddr || '');
          $('td:contains("部门名称") + td').text(data.visitorToApartment || '');
          $('td:contains("接待人") + td').text(data.visitorsReceptionistName || '');
          $('td:contains("接待人联系方式") + td').text(data.visitorsReceptionistTel || '');
          $('td:contains("备注") + td').text(data.remark || '');
        }
      });
    }

    window.onload = function() {
      set({"action":"{\"ID\":1}"});
      console.log("test test test ");
    };