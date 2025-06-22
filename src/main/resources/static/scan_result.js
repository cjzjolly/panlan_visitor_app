
    function init() {
      set('{ID : 1}');
      // document.getElementsByClassName('company')[0].textContent = 'asdasdasd';
      // document.querySelector('.visitorName').textContent = 'asdasdasdasdasd';
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
          // document.getElementsByClassName('visitorInfo')[0].textContent = data.visitorInfo;
          // document.getElementsByClassName('visitorName')[0].textContent = data.visitorName;
          // document.getElementsByClassName('company')[0].textContent = data.company;
          // document.getElementsByClassName('visitorFromAddr')[0].textContent = data.visitorFromAddr;
          // document.getElementsByClassName('visitorTel')[0].textContent = data.visitorTel;
          // document.getElementsByClassName('visitorCount')[0].textContent = data.visitorCount;
          // document.getElementsByClassName('visitorCarID')[0].textContent = data.visitorCarID;
          // document.getElementsByClassName('visitorTime')[0].textContent = data.visitorTime;
          // document.getElementsByClassName('enterTime')[0].textContent = data.enterTime;
          // document.getElementsByClassName('leaveTime')[0].textContent = data.leaveTime;
          // document.getElementsByClassName('visitorToAddr')[0].textContent = data.visitorToAddr;
          // document.getElementsByClassName('visitorToApartment')[0].textContent = data.visitorToApartment;
          // document.getElementsByClassName('visitorsReceptionistName')[0].textContent = data.visitorsReceptionistName;
          // document.getElementsByClassName('visitorsReceptionistTel')[0].textContent = data.visitorsReceptionistTel;
          // document.getElementsByClassName('remark')[0].textContent = data.remark;
          
          document.querySelector('.visitorName').textContent = data.visitorName;
        
        }
      });
    }

    window.onload = function() {
      console.log("test test test ");
      init();
    };