let main = {
    init: function (){

        $("#insertComplaintButton").on("click", function (){
            let data = {};

            data.complaintDescription = $("#inputDescription").val();
            data.anonymous = $("#isAnonymous").val();
            data.memberId = $("#sessionMemberId").val();

            main.save(JSON.stringify(data));
        });
    },
    save: function (jsonData){
        console.log(jsonData)

        $.ajax({
            url: "/api/complaint/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage);
                }else{
                    alert(result.resultMessage)
                }
                location.reload();
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
            }
        });
    },
    findComplaintsByMemberId: function (memberId){
        $.ajax({
            url: "/api/complaint/complaints",
            method: "GET",
            data: {
                memberId : memberId
            },
            success: function (result){
                let html = "";
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    if(result.data.length > 0){
                        console.log(result.data)
                        $.each(result.data, function (index,element){
                            html += "<tr>" +
                                        "<td class='text-center'>"+element.complaintId+"</td>" +
                                        "<td class='text-center'>"+element.complaintDescription+"</td>" +
                                        "<td class='text-center'>"+element.anonymous+"</td>" +
                                        "<td class='text-center'>"+dayjs(element.appendDate).format('YYYY.MM.DD')+"</td>" +
                                        "<td class='text-center'>"+element.complaintStatus+"</td>" +
                                    "</tr>"
                        });
                    }else{
                        html = "<tr><td colspan='5'> 등록된 게시글이 없습니다. </td></tr>"
                    }

                    $("#complaintListDiv").html(html);
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