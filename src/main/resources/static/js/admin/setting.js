let main = {
    init: function (){
        let _this = this;
        $("#updateServiceNameButton").on("click", function (){
            let value = $("#serviceName").val();
            _this.update(META_TYPE.SERVICE_NAME, value);
        });

        $("#uploadIconImageButton").on("click", function (){
            $("#loading").show();
            _this.upload("icon", "public");
        });

        $("#updateServiceIconButton").on("click", function (){
            let value = $("#serviceIconImagePath").val();
            _this.update(META_TYPE.SERVICE_ICON, value);
        });
    },
    getMetaValue: function (type){
        let metaValue = "";
      $.ajax({
          url: "/api/common/meta/"+type,
          method: "GET",
          async: false,
          success: function (result){
              metaValue = result.metaValue;
          },
          error: function (x,h,r){
              console.error(x)
              alert(RESULT_MESSAGE.FAIL_SYSTEM);
          }
      });
      return metaValue;
    },
    update: function (type, value){
        $.ajax({
            url: "/api/common/meta/"+type,
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            data: value,
            success: function (result){
                if(result.resultCode != null && result.resultCode === "0000"){
                    alert("수정되었습니다.");
                    console.log(result);
                    if(type === META_TYPE.SERVICE_NAME){
                        $("#serviceName").val(result.data);
                    }else{
                        $("#imgControl").html(main.getImageControlHtml(result.data));
                    }
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    },
    upload: function (fileType, accessControl){
        let file = $("#serviceIconImage")[0].files[0];

        let formData = new FormData();
        formData.append("file", file, file.name);

        $.ajax({
            url: "/api/common/upload/" +fileType+ "/" +accessControl,
            method: "POST",
            enctype: 'multipart/form-data',
            data : formData,
            processData: false,
            contentType: false,
            cache: false,
            success: function (result){
                if(result.resultCode === "0000"){
                    $("#serviceIconImagePath").val(result.data.filePath);
                    let html = "";
                    html += "<img src='" + COMMON_VALUES.BUCKET_BASE_URL+result.data.filePath + "' alt='uploadImage' style='max-width: 150px;'>";
                    $("#imgControl").html(html);
                    $("#loading").hide();
                }
            },
            error: function (x,h,r){
                console.log(x);
                $("#loading").hide();
                alert("시스템 오류 발생. 관리자에게 문의해주세요.");
                return;
            }
        });
    },
    getImageControlHtml: function (filePath){
        return "<img src='" + COMMON_VALUES.BUCKET_BASE_URL+filePath + "' alt='uploadImage' style='max-width: 150px;'>";
    }
}

main.init();