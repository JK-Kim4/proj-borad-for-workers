let main = {
    init: function (){
        //회원 가입 버튼
        $("#requestRegistrationButton").on("click", function (){
            //TODO data validation
            let data = {}
            data.memberEmail = $("#inputMail").val();
            data.memberRealName = $("#inputRealName").val();
            data.memberNickName = $("#inputNickName").val();
            data.memberPassword = $("#inputPassword").val();
            data.company = $("#inputCompany").val();
            data.department = $("#inputDepartment").val();

            main.save(JSON.stringify(data));
        });
    },
    save: function (jsonData){

        $.ajax({
            url: "/api/member/pre-member/save",
            method: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(RESULT_MESSAGE.SUCCESS_MEMBER_REGISTRATION_REQUEST);
                    location.href = "/";
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