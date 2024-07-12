let main = {
    init: function (){
        $("#movePostSavePage").on("click", function (){
            let boardId = $("#boardId").val();
            location.href = "/board/"+boardId+"/post/save"
        });

        $("#postSaveButton").on("click", function (){
            let data = {};

            data.boardId = $("#boardId").val();
            data.memberId = $("#memberId").val();
            data.useYn = $("input[name='inputUseYn']:checked").val();
            data.postHead = $("#inputPostHead").val();
            data.postTitle = $("#inputPostTitle").val();
            data.postContent = $("#inputContent").val();

            main.savePost(JSON.stringify(data));
        });
    },
    savePost: function (jsonData){
        $.ajax({
            url: "/api/board/post/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                console.log(result)
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    },
    findBoardById: function (boardId){
        let boardDetail = {};
        $.ajax({
            url: "/api/board/"+boardId,
            method: "GET",
            contentType: "application/json; charset=utf-8",
            async: false,
            success: function (result){
                console.log(result)
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    boardDetail = result.data;
                }else{
                    alert(result.resultMessage)
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
        return boardDetail;
    }
}

main.init();