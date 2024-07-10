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
    },
    findBoards: function (pageNum, pageSize){
        $.ajax({
            url: "/api/board/boards",
            method: "GET",
            data: {
                pageNum: pageNum,
                pageSize: pageSize
            },
            contentType: "application/json; charset=utf-8",
            success: function (result){
                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        $.each(result.data, function (index, element){
                            html += "<tr>" +
                                        "<td class='text-center'>"+element.boardId+"</td>" +
                                        "<td class='text-center'>"+element.boardName+"</td>" +
                                        "<td class='text-center'>"+element.useYn+"</td>" +
                                        "<td class='text-center'>"+element.attachmentAllowYn+"</td>" +
                                        "<td class='text-center'>"+element.appendDate+"</td>" +
                                        "<td class='text-center'><button class='btn btn-outline-primary'>수정</button></td>" +
                                        "<td class='text-center'><button class='btn btn-outline-danger'>삭제</button></td>" +
                                    "</tr>"
                        });
                    }else{
                        html += "<tr  class='text-center'><td colspan='5'> 생성된 게시판이 없습니다. </td></tr>"
                    }
                }

                $("#boardListBody").html(html);
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    }

}

main.init();