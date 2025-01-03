let main = {
    init: function (){
        $("#boardSaveButton").on("click", function (){
            let data = {}

            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()
            data.readRole = $("#inputReadRole").val();
            data.writeRole = $("#inputWriteRole").val();
            data.depth = 1;

            main.save(JSON.stringify(data));
        });

        $("#boardUpdateButton").on("click", function (){
            let data = {}

            data.boardId = $("#boardId").val();
            data.boardName = $("#inputBoardName").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.attachmentAllowYn = $("input[name='inputAttachmentAllowYn']:checked").val()
            data.readRole = $("#inputReadRole").val();
            data.writeRole = $("#inputWriteRole").val();

            main.update(data.boardId, JSON.stringify(data));
        });

        $("#boardDeleteButton").on("click", function (){
            let boardId = $("#boardId").val();

            main.delete(boardId);
        });

        $("#postUpdateButton").on("click", function (){
            let data= {};
            let postId = $("#postId").val();

            data.postId = postId;
            data.boardId = $("#boardId").val();
            data.useYn = $("input[name='inputUseYn']:checked").val()
            data.postTitle = $("#disablePostTitle").val();
            data.postContent = $("#disablePostContent").val();
            data.postHead = $("#disablePostHead").val()
            /*data.attachmentFileName;
            data.attachmentFilePath;*/

            main.postUpdate(JSON.stringify(data), postId);
        });

        $("#postDeleteButton").on("click", function (){
            let postId = $("#postId").val();

            main.deletePost(postId);
        })

        $("#moveBoardInsertPageButton").on("click", function (){
            location.href = "/admin/board/save"
        });

        $(document).on("click",".move-detail-button",function (){
            let boardId = $(this).data("board-id");
            location.href = "/admin/board/detail/"+boardId;
        });

        $(document).on("click", ".move-post-list-button", function (){
            let boardId = $(this).data("board-id");
            location.href = "/admin/board/"+boardId+"/post/list";
        });

        $(document).on("click", ".move-post-detail-button", function (){
            let postId = $(this).data("post-id");
            location.href = "/admin/board/post/detail/"+postId;
        })
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
    postUpdate: function (jsonData, postId){
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
    deletePost: function (postId){
        $.ajax({
            url: "/api/board/post/delete/"+postId,
            method: "DELETE",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage)
                    location.href = "/admin/board/list";
                }else{
                    alert(result.resultMessage);
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    },
    findBoards: function (pageNum, pageSize){
        $.ajax({
            url: "/api/board/boards",
            method: "GET",
            data: {
                pageNum: pageNum,
                pageSize: pageSize,
                type: "admin"
            },
            contentType: "application/json; charset=utf-8",
            success: function (result){
                console.log(result);

                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        $.each(result.data, function (index, element){

                            html += "<div class='col-md-4'>" +
                                        "<div class='card p-3 mb-2'>" +
                                            "<div class='d-flex justify-content-between'>" +
                                                "<div class='d-flex flex-row align-items-center'>" +
                                                    "<div class='ms-2 c-details'>" +
                                                        "<h6 class='mb-0'>생성일: "+dayjs(element.appendDate).format('YYYY.MM.DD')+"</h6>" +
                                                        "<span class='badge badge-pill bg-primary'> 권한 </span>" +
                                                    "</div>" +
                                                "</div>" +
                                                "<div class=''> <button class='btn btn-outline-dark move-post-list-button' data-board-id='"+element.boardId+"'>게시물 목록</button> </div>" +
                                            "</div>" +
                                            "<div class='mt-5'>" +
                                                "<a href='/admin/board/detail/"+element.boardId+"'><h3 class='heading'>"+element.boardName+"<br></h3></a>" +
                                                "<h5>게시물 수: </h5>" +
                                                "<div class='mt-5'>" +
                                                    "<div class='mt-3'>" +
                                                        "<span class='text1'> <span class='text2'>신규 등록 게시물: 24</span></span>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +
                                        "</div>" +
                                    "</div>";
                        });
                    }else{
                        html += "<tr  class='text-center'><td colspan='7'> 생성된 게시판이 없습니다. </td></tr>"
                    }
                }

                $("#boardListDiv").html(html);
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
                    $("#inputReadRole").find("option[value='"+data.readRole+"']").attr("selected",true);
                    $("#inputWriteRole").find("option[value='"+data.writeRole+"']").attr("selected",true);

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
    },
    findPosts: function (boardId, pageNum, pageSize){
        $.ajax({
            url: "/api/board/"+boardId+"/posts",
            method: "GET",
            data: {
                pageNum: pageNum,
                pageSize: pageSize
            },
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                let html = "";
                if (RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if (result.data.length > 0){
                        let data = result.data;
                        $.each(data, function (index, element){
                            html += "<tr>" +
                                        "<td class='text-center'> "+element.postId+"</td>" +
                                        "<td class='text-center'> "+element.boardName+"</td>" +
                                        "<td class='text-center'> "+element.postTitle+"</td>" +
                                        "<td class='text-center'> "+element.memberRealName+"</td>" +
                                        "<td class='text-center'> "+element.recommendCount+"</td>" +
                                        "<td class='text-center'> "+element.readCount+"</td>" +
                                        "<td class='text-center'> "+dayjs(element.appendDate).format('YYYY.MM.DD')+"</td>" +
                                        "<td class='text-center'> "+dayjs(element.updateDate).format('YYYY.MM.DD')+"</td>" +
                                        "<td class='text-center'><button class='btn btn-success move-post-detail-button' data-post-id='"+element.postId+"'> 상세 보기 </button></td>" +
                                    "</tr>"
                        });
                    }else{
                        html += "<tr><td colspan='8' class='text-center'> 등록된 게시글이 존재하지 않습니다. </td></tr>";
                    }
                }

                $("#postListBody").html(html);
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    },
    findPostById: function (postId){
        $.ajax({
            url: "/api/board/post/"+postId,
            method: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    let data = result.data;
                    $("#disableAuthorInformation").val(data.memberRealName + " ("+ data.memberNickName + ")");
                    $("#memberId").val(data.memberId);
                    $("#disableBoardName").val(data.boardName);
                    $("#boardId").val(data.boardId);
                    $("#disablePostHead").val(data.postHead);
                    $("#disablePostTitle").val(data.postTitle);
                    $("#disablePostContent").val(data.postContent);
                    $("#disableAppendDate").val(dayjs(data.appendDate).format('YYYY.MM.DD. HH:mm:ss'));
                    $("#disableUpdateDate").val(dayjs(data.updateDate).format('YYYY.MM.DD. HH:mm:ss'));
                    $("input[name='inputUseYn'][value='"+data.useYn+"']").attr("checked", true);
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    }
}

main.init();