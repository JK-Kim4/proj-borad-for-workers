let main = {
    init: function (){
        $("#movePostSavePage").on("click", function (){
            let boardId = $("#boardId").val();
            location.href = "/board/"+boardId+"/post/save"
        });

        $("#postSaveButton").on("click", function (){
            let data = {};

            let boardId = $("#boardId").val();

            data.boardId = boardId;
            data.memberId = $("#memberId").val();
            data.useYn = $("input[name='inputUseYn']:checked").val();
            data.postHead = $("#inputPostHead").val();
            data.postTitle = $("#inputPostTitle").val();
            data.postContent = $("#inputContent").val();

            main.savePost(JSON.stringify(data), boardId);
        });

        $(document).on("click", ".post-list-content", function (){
            let postId = $(this).data("post-id");
            location.href = "/board/post/detail/"+postId;
        })
    },
    savePost: function (jsonData, boardId){
        $.ajax({
            url: "/api/board/post/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage);
                    location.href = "/board/"+boardId+"/post/list";
                }else{
                    alert(result.resultMessage);
                    location.reload();
                }


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
    },
    findBoardPostsForClients: function (boardId){
        $.ajax({
            url: "/api/board/client/"+boardId+"/posts",
            method: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (result){
                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        let data = result.data;
                        $.each(data, function (index, element){
                            html += "<tr class='post-list-content' data-post-id='"+element.postId+"'>" +
                                        "<td class='text-center'>"+element.postId+"</td>" +
                                        "<td class='text-center'>"+element.postTitle+"</td>" +
                                        "<td class='text-center'>"+element.memberRealName+"</td>" +
                                        "<td class='text-center'>"+dayjs(element.appendDate).format('YYYY.MM.DD')+"</td>" +
                                        "<td class='text-center'>"+element.recommendCount+"</td>" +
                                        "<td class='text-center'>"+element.readCount+"</td>" +
                                    "</tr>"
                        });
                    }else{
                        html += "<tr><td colspan='6' class='text-center'>등록된 게시글이 없습니다.</td></tr>"
                    }
                    $("#postListDiv").html(html)
                }
            },
            error: function (x,h,r){
                console.log(e);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    }
}

main.init();