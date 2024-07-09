let main = {
    init: function (){
        $("#boardSaveButton").on("click", function (){
            let data = {}

            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()

            main.save(JSON.stringify(data));
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