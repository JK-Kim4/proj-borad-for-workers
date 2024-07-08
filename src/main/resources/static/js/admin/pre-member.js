let main = {
    init: function (){

        //가입 승인 버튼
        $(document).on("click", ".accept-button",function (){
            let preMemberId = $(this).data("pre-member-id");

            main.preMemberSignUpProcess("accept", preMemberId)
        });

        //가입 거절 버튼 클릭
        $(document).on("click", ".decline-button",function (){
            let preMemberId = $(this).data("pre-member-id");
            main.preMemberSignUpProcess("decline", preMemberId)
        });
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
                                        "<td><button class='btn btn-success accept-button' data-pre-member-id='"+element.preMemberId+"'> 승인 </button></td>" +
                                        "<td><button class='btn btn-danger decline-button' data-pre-member-id='"+element.preMemberId+"'> 거절 </button></td>" +
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
    preMemberSignUpProcess: function (processType, preMemberId){

        $.ajax({
           url: "/api/member/pre-member/"+processType+"/"+preMemberId,
           method: "PUT",
           contentType: "application/json; charset=utf-8",
           success: function (result){
               if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                   alert(result.resultCode);
                   location.href = "/admin/pre-member/list";
               } else {
                   alert(result.resultMessage);
                   location.reload();
               }
           },
            error: function (x,h,r){
               console.error(x);
               alert(RESULT_MESSAGE.FAIL_SYSTEM);
               location.reload();
            }
        });
    }
}


main.init();