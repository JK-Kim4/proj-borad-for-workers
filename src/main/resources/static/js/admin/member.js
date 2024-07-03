let main = {
    init: function (){
        //관리자 계정 등록 버튼
        $("#adminRegistrationButton").on("click", function (){
            let memberSaveDto = {}

            memberSaveDto.memberEmail = $("#inputMail").val()
            memberSaveDto.memberRealName = $("#inputRealName").val();
            memberSaveDto.memberPassword = $("#inputPassword").val();
            memberSaveDto.memberNickName = $("#inputNickName").val();
            memberSaveDto.company = $("#inputCompany").val();
            memberSaveDto.department = $("#inputDepartment").val();
            memberSaveDto.role = $("#inputRole").val()

            main.save(JSON.stringify(memberSaveDto));
        });

    },
    save: function (jsonData){

        $.ajax({
            url: "/api/member/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                console.log(result);
                if(result.resultCode === "0000"){
                    alert(result.resultMessage);
                    location.href = "/";
                }else{
                    alert(result.resultMessage);
                    location.reload();
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert("시스템 오류 발생. 관리자에게 문의해주세요.")
            }
        });
    }

}

main.init();