let main = {
    init: function (){
        $("#boardSaveButton").on("click", function (){
            let data = {}

            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()

            main.save(JSON.stringify(data));
        });

        $("#boardUpdateButton").on("click", function (){
            let data = {}

            data.boardId = $("#boardId").val();
            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()

            main.update(data.boardId, JSON.stringify(data));
        });

        $("#boardDeleteButton").on("click", function (){
            let boardId = $("#boardId").val();

            main.delete(boardId);
        });

        $("#moveBoardInsertPageButton").on("click", function (){
            location.href = "/admin/board/save"
        });

        $(document).on("click",".move-detail-button",function (){
            let boardId = $(this).data("board-id");
            location.href = "/admin/board/detail/"+boardId;
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
    update: function (boardId, jsonData){
        $.ajax({
            url: "/api/board/update/"+boardId,
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage)
                    location.reload();
                }else{
                    alert(result.resultMessage);
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
                location.reload();
            }
        });
    },
    delete: function (boardId){
        $.ajax({
            url: "/api/board/delete/"+boardId,
            method: "DELETE",
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage)
                    location.href = "/admin/board/list";
                }else{
                    alert(result.resultMessage);
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
                location.reload();
            }
        })
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
                                        "<td class='text-center'>" +
                                            "<button class='btn btn-outline-primary move-detail-button' data-board-id='"+element.boardId+"'>수정</button>" +
                                        "</td>" +
                                    "</tr>"
                        });
                    }else{
                        html += "<tr  class='text-center'><td colspan='7'> 생성된 게시판이 없습니다. </td></tr>"
                    }
                }

                $("#boardListBody").html(html);
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    },
    findBoardById: function (boardId){
        $.ajax({
            url: "/api/board/"+boardId,
            method: "GET",
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    let data = result.data;

                    $("#inputBoardName").val(data.boardName);
                    $("input[name='inputUseYn'][value='"+data.useYn+"']").attr("checked", true);
                    $("input[name='inputAttachmentAllowYn'][value='"+data.attachmentAllowYn+"']").attr("checked", true);
                    $("#appendDate").val(data.appendDate);
                    $("#updateDate").val(data.updateDate);

                }else{
                    alert(result.resultMessage)
                    location.href = "/admin/board/list";
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    }

}

main.init();