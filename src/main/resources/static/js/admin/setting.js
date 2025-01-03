let main = {
    init: function (){
        let _this = this;
        $("#updateServiceNameButton").on("click", function (){
            let value = $("#serviceName").val();
            this.update(META_TYPE.SERVICE_NAME, value);
        });

        $("#uploadIconImageButton").on("click", function (){
            $("#loading").show();
            _this.upload("icon", "public");
        });
    },
    update: function (type, value){

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
                    console.log(result)

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
    }
}

main.init();