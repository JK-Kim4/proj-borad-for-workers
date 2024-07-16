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

        //이메일 인증번호 발송 버튼
        $("#emailRequestButton").on("click", function (){
            let email = $("#inputMail").val();
            if(!main.isChangbiMember(email)){
                alert("창비 그룹웨어 메일 주소(*@changbi.com)로만 가입할 수 있습니다.")
                return;
            }

            main.sendAuthNumber(email);
        });

        //인증번호 일치 확인 버튼
        $("#authNumberCheckButton").on("click", function (){

            let inputAuthNumber = $("#authNumberChecker").val();
            let sendAuthNumber = window.localStorage.getItem("authNumber")

            if(inputAuthNumber === sendAuthNumber){
                $("#emailValidationResult").val(true);
                alert("인증되었습니다.")
                $("#authCheckDiv").hide();
            }else{
                alert("인증번호가 일치하지않습니다. 확인 후 다시 시도해주세요.")
            }
        });

        //인증번호 재발송 버튼
        $("#reGenerateButton").on("click", function (){
            window.localStorage.removeItem("authNumber");
            let email = $("#inputMail").val();
            main.sendAuthNumber(email);
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
                } else {
                    alert(result.resultMessage)
                    location.reload();
                }
            },
            error: function (x,h,r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM);
            }
        });
    },
    isChangbiMember: function (email){
        const emailRegex = /^[a-zA-Z0-9._-]+@changbi.com$/i;

        return emailRegex.test(email);
    },
    sendAuthNumber: function (email) {
        $("#emailValidationResult").val(false);

        let data = {};
        data.email = email;
        data.authNumber = main.generateAuthNumber();

        $.ajax({
            url: "/api/member/send-auth",
            method: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8;",
            success: function (result){
                if(RESULT_CODE.SUCCESS_DEFAULT === result.resultCode){
                    alert(result.resultMessage);
                    $("#emailRequestButton").hide();
                    $("#authCheckDiv").show();
                }
            },
            error: function (x,h,r){
                console.error(x)
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
                return;
            }
        })
    },
    generateAuthNumber: function (){
        let authNumber = "";
        for(let i = 0; i < 4; i++){
            authNumber += Math.floor(Math.random() * 10).toString();
        }
        window.localStorage.setItem("authNumber", authNumber)

        return authNumber;
    },

}


main.init();