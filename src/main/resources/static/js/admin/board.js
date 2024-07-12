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

        $(document).on("click", ".move-post-list-button", function (){
            let boardId = $(this).data("board-id");
            location.href = "/admin/board/"+boardId+"/post/list";
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

                            /*<div class='col-md-4'>
                                <div class='card p-3 mb-2'>
                                    <div class='d-flex justify-content-between'>
                                        <div class='d-flex flex-row align-items-center'>
                                            <div class='ms-2 c-details'>
                                                <h6 class='mb-0'>생성일:</h6>
                                                <span class='badge badge-pill bg-primary'> 권한 </span>
                                            </div>
                                        </div>
                                        <div class=''> <button class='btn btn-outline-dark'>게시물 목록</button> </div>
                                    </div>
                                    <div class='mt-5'>
                                        <h3 class='heading'>자유게시판<br></h3>
                                        <h5>게시물 수: 120</h5>
                                        <div class='mt-5'>
                                            <div class='mt-3'> <span class='text1'> <span class='text2'>신규 등록 게시물: 24</span></span> </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            html += "<tr>" +
                                        "<td class='text-center'>"+element.boardId+"</td>" +
                                        "<td class='text-center'>"+element.boardName+"</td>" +
                                        "<td class='text-center'>"+element.useYn+"</td>" +
                                        "<td class='text-center'>"+element.attachmentAllowYn+"</td>" +
                                        "<td class='text-center'>"+element.appendDate+"</td>" +
                                        "<td class='text-center'>" +
                                            "<button class='btn btn-outline-primary move-detail-button' data-board-id='"+element.boardId+"'>수정</button>" +
                                        "</td>" +
                                        "<td class='text-center'>" +
                                            "<button class='btn btn-outline-success move-post-list-button' data-board-id='"+element.boardId+"'>게시물 관리</button>" +
                                        "</td>" +
                                    "</tr>"*/

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
                                                "<h3 class='heading'>"+element.boardName+"<br></h3>" +
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
    findPosts: function (pageNum, pageSize,boardId){

    }
}

main.init();