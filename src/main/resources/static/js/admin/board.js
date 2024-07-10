let main = {
    init: function (){
        $("#boardSaveButton").on("click", function (){
            let data = {}

            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()

            main.save(JSON.stringify(data));
        });

        $("#moveBoardInsertPageButton").on("click", function (){
            location.href = "/admin/board/save"
        });
    },
    save: function (jsonData){
        console.log(jsonData);
        $.ajax({
            url: "/api/board/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage);
                    location.href = "/admin/board/list";
                }
                console.log(result)
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    }

}

main.init();