let main = {
    init: function (){

    },
    findPreMembers: function (pageNum, pageSize){

        $.ajax({
            url: "/api/member/pre-members",
            method: "GET",
            data: {
                pageNum: pageNum,
                pageSize: pageSize
            },
            success: function (result){
                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        $.each(result.data, function (index, element){
                            html += "<tr>" +
                                        "<td>"+dayjs(element.requestDateTime).format('YYYY.MM.DD')+"</td>" +
                                        "<td>"+element.memberEmail+"</td>" +
                                        "<td>"+element.memberRealName+"</td>" +
                                        "<td>"+element.company+"</td>" +
                                        "<td>"+element.department+"</td>" +
                                        "<td><button class='btn btn-success'> 승인 </button></td>" +
                                        "<td><button class='btn btn-danger'> 거절 </button></td>" +
                                    "</tr>"
                        });
                    } else {
                        html = "<tr><td colspan='7'>"+RESULT_MESSAGE.SUCCESS_NO_REQUEST_MEMBER+"</td></tr>"
                    }

                    $("#preMemberListBody").html(html);
                }else{

                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        })
    },
}


main.init();