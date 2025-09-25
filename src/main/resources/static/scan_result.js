
    function init() {
      // 获取 URL 中的参数
      const urlParams = new URLSearchParams(window.location.search);
      const id = urlParams.get('id'); // 获取 ID 值
      set(id);
      // document.getElementsByClassName('company')[0].textContent = 'asdasdasd';
      // document.querySelector('.visitorName').textContent = 'asdasdasdasdasd';
    }

    function set(id) {
      $.ajax("/api/getRegInfoItemsByID", {
        method: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: { id: id } ,
      }).done(function (res) {
        if (res && res.data !== undefined) {
          const jsonStr = res.data;
          console.log("test test test:", jsonStr);
          // 解析 JSON 字符串
          const dataArray = JSON.parse(jsonStr);
          const data = dataArray[0];
          // 填写表格
          // document.getElementsByClassName('visitorInfo')[0].textContent = data.visitorInfo;
          document.getElementsByClassName('visitorName')[0].textContent = data.visitorName;
          document.getElementsByClassName('company')[0].textContent = data.company;
          document.getElementsByClassName('visitorFromAddr')[0].textContent = data.visitorFromAddr;
          document.getElementsByClassName('visitorTel')[0].textContent = data.visitorTel;
          document.getElementsByClassName('visitorCount')[0].textContent = data.visitorCount;
          document.getElementsByClassName('visitorCarID')[0].textContent = data.visitorCarID;
          
          const visitDate = data.visitorTime.date
          const visitTime = data.visitorTime.time
          // 格式化为“年月日时分秒”
          const formattedDateTime = `${visitDate.year}年${visitDate.month}月${visitDate.day}日 ${time.hour}时${visitTime.minute}分${visitTime.second}秒`;
          
          document.getElementsByClassName('visitorTime')[0].textContent = formattedDateTime;
          document.getElementsByClassName('enterTime')[0].textContent = data.enterTime;
          document.getElementsByClassName('leaveTime')[0].textContent = data.leaveTime;
          document.getElementsByClassName('visitorToAddr')[0].textContent = data.visitorToAddr;
          document.getElementsByClassName('visitorToApartment')[0].textContent = data.visitorToApartment;
          document.getElementsByClassName('visitorsReceptionistName')[0].textContent = data.visitorsReceptionistName;
          document.getElementsByClassName('visitorsReceptionistTel')[0].textContent = data.visitorsReceptionistTel;
          document.getElementsByClassName('remark')[0].textContent = data.remark;
          
          

        }
      });
    }

    window.onload = function() {
      console.log("test test test ");
      init();
    };