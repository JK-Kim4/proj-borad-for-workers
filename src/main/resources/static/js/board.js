let main = {
    init: function (){
        let _this = this;

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
            data.postContent = editor.getHTML();
            let formData = new FormData($('#fileForm')[0]);
            console.log(formData)

            if($("#attachmentFlag").val() === "true"
                && $("#attachmentFile").val() !== ''){
                _this.uploadAttachmentFile();
                data.attachmentId = $("#attachmentId").val();
            }

            _this.savePost(JSON.stringify(data), boardId);
        });

        $("#postDeleteButton").on("click", function (){
            let postId = $("#postId").val(); let boardId = $("#boardId").val();
            _this.deletePost(postId, boardId);
        });

        $("#postUpdateButton").on("click", function (){
            let data = {};
            let postId = $("#postId").val(); let boardId = $("#boardId").val();

            data.postId = postId;
            data.boardId = boardId;
            data.memberId = $("#memberId").val();
            data.useYn = $("input[name='inputUseYn']:checked").val();
            data.postHead = $("#inputPostHead").val();
            data.postTitle = $("#inputPostTitle").val();
            data.postContent = editor.getHTML();

            _this.updatePost(JSON.stringify(data), postId);
        });

        $("#recommendButton").on("click", function (){
            let postId = $("#postId").val();
            _this.updatePostRecommendCount(postId);
        });

        $("#goToPostListButton").on("click", function (){
            let boardId = $("#boardId").val();
            location.href = "/board/"+boardId+"/post/list";
        });

        $("#reportButton").on("click", function (){
            let data = {
                postId: $("#postId").val(),
                memberId: $("#sessionMemberId").val(),
            }

            _this.reportPost(JSON.stringify(data));
        });

        //댓글 작성 버튼
        $("#saveCommentButton").on("click", function (){
            let data = {
                postId: $("#postId").val(),
                memberId: $("#sessionMemberId").val(),
                comment: $("#inputComment").val()
            }

            console.log(data);
        })

        $(document).on("click", ".post-list-content", function (){
            let postId = $(this).data("post-id");
            let boardId = $("#boardId").val();
            location.href = "/board/"+boardId+"/post/detail/"+postId;
        });

        $(document).on("click", "#goToPostUpdate", function (){
            let postId = $(this).data("post-id");
            let boardId = $("#boardId").val()
            location.href = "/board/"+boardId+"/post/update/"+postId;
        });
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
    uploadAttachmentFile: function (){
        let formData = new FormData($('#fileForm')[0]);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',	// 필수
            url: '/api/board/upload/attachment',
            data: formData,		// 필수
            processData: false,	// 필수
            contentType: false,	// 필수
            cache: false,
            async: false,
            success: function (result) {
                if (RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    $("#attachmentId").val(result.data);
                }else {
                    alert(result.resultMessage);
                }
            },
            error: function (e) {
                console.error(e)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    },
    updatePost: function (jsonData, postId){

        $.ajax({
            url: "/api/board/post/update/"+postId,
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
    deletePost: function (postId, boardId){
        $.ajax({
            url: "/api/board/post/delete/"+postId,
            method: "DELETE",
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage);
                    location.href = "/board/"+ boardId + "/post/list";
                }else{
                    alert(result.resultMessage)
                    location.reload();
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
                location.reload();
            }
        });
    },
    findBoardById: function (boardId){
        let boardDetail = {};
        $.ajax({
            url: "/api/board/"+boardId,
            method: "GET",
            contentType: "application/json; charset=utf-8",
            async: false,
            success: function (result){
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
    findPostById: function (postId){
        let postData = {};

        $.ajax({
            url: "/api/board/post/"+postId,
            method: "GET",
            data: {
                type: "client"
            },
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    postData = result.data;
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });

        return postData;
    },
    findBoardPostsForClients: function (boardId, pageNum, pageSize){
        $.ajax({
            url: "/api/board/client/"+boardId+"/posts",
            method: "GET",
            data: {
                pageNum : pageNum,
                pageSize : pageSize
            },
            contentType: "application/json; charset=utf-8",
            success: function (result){
                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        let data = result.data;
                        $.each(data, function (index, element){
                            let postHeadBadge = main.getPostHeadBadge(element.postHead);
                            html += "<tr class='post-list-content' data-post-id='"+element.postId+"'>" +
                                        "<td class='text-center'>"+element.postId+"</td>" +
                                        "<td class='text-start'>"+postHeadBadge+element.postTitle+"</td>" +
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
    },
    updatePostRecommendCount: function (postId){
        $.ajax({
            url: "/api/board/update/recommend-count/"+postId,
            method: "GET",
            contentType: "application/json; charset=utd-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(RESULT_MESSAGE.SUCCESS_RECOMMEND_RESULT);
                    $("#recommendButton").html("추천 "+result.data);
                }else{
                    alert(result.resultMessage);
                    return;
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
                return;
            }
        })
    },
    reportPost: function (jsonData){
        $.ajax({
            url: "/api/board/report/post",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8",
            success: function (result){
                console.log(result)
                alert(result.resultMessage);
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    },
    downloadAttachment: function (attachmentId){
        console.log(attachmentId)
        location.href = "/api/attachment/download/"+attachmentId;
    },
    addUpdateButton: function (postId){
        let html = "<button class='btn btn-success' id='goToPostUpdate' data-post-id='"+postId+"'>수정</button>";

        $("#postUpdateButtonDiv").html(html);
    },
    renderPostDetailPage: function (data){

        $("#postTitle").html(main.getPostHeadBadge(data.postHead) +" "+ data.postTitle);
        $("#postAuthor").html(data.memberNickName + " ("+ data.memberRealName + ")");
        $("#postContent").html(data.postContent);

        $("#readCount").html("조회수: "+data.readCount);
        $("#recommendButton").html("추천 " + data.recommendCount);

        $("#boardId").val(data.boardId)
        $("#memberId").val(data.memberId)

        if(data.attachmentId != null){
            $("#postAttachment").html("첨부파일: <button class='btn btn-outline-dark' onclick='main.downloadAttachment("+data.attachmentId+")'>"+data.attachmentFileName+"</button>")
        }

        if(data.memberId == $("#sessionMemberId").val()){
            main.addUpdateButton(data.postId);
        }
    },
    renderPostUpdatePage: function (data){
        $("input[name='inputUseYn'][value='"+data.useYn+"']").attr("checked", "checked");
        $("#inputPostHead").val(data.postHead).attr("selected", "selected");
        $("#inputPostTitle").val(data.postTitle);
        $("#editor").html(editor.setHTML(data.postContent));
    },
    getPostHeadBadge: function (postHead){
        let badge = "";
        switch (postHead){
            case 'GENERAL' :
                badge = "<span class='badge badge-pill bg-primary me-2'> 일반 </span>";
                break;
            case 'NOTICE' :
                badge = "<span class='badge badge-pill bg-success me-2'> 공지 </span>";
                break;
            case 'NEWS' :
                badge = "<span class='badge badge-pill bg-warning me-2'> 뉴스 </span>";
                break;
        }
        return badge;
    }
}

main.init();