let index = {
    init: function (){

    },
    getIndexData: function (){
        $.ajax({
            url: "/api/common/index-data",
            method: "GET",
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                console.log(result)
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    let data = result.data;
                    if(data.recentNotice){
                        $("#recentNoticeTitle").html(data.recentNotice.postTitle)
                        $("#recentNoticePostDate").html(dayjs(data.recentNotice.appendDate).format('YYYY.MM.DD'))
                        $("#recentNoticeDesc").html(index.textEllipsis(data.recentNotice.postContent, 25))
                        $("#goToRecentNotice").attr("href", "/board/"+data.recentNotice.boardId+"/post/detail/"+data.recentNotice.postId);
                    }

                    if(data.popularPost){
                        $("#popularPostTitle").html(data.popularPost.postTitle)
                        $("#popularPostDate").html(dayjs(data.popularPost.appendDate).format('YYYY.MM.DD'))
                        $("#popularPostDesc").html(index.textEllipsis(data.popularPost.postContent, 25))
                        $("#goToPopularPost").attr("href", "/board/"+data.popularPost.boardId+"/post/detail/"+data.popularPost.postId)
                    }
                }
            },
            error: function (x, h, r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
            }
        })
    },
    textEllipsis: function (text, maxlength) {
        if(text.length > maxlength){
            return text.substring(0, maxlength) + "...";
        }else{
            return text;
        }
    }

}

index.init()