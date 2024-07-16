let main = {
    init: function (){
        //회원 가입 버튼
        $("#requestRegistrationButton").on("click", function (){
            //TODO data validation
            let data = {}
            let emailValidationResult = $("#emailValidationResult").val();
            let passwordValidationResult = $("#passwordValidationResult").val();

            if(emailValidationResult && passwordValidationResult){
                data.memberEmail = $("#inputMail").val();
                data.memberRealName = $("#inputRealName").val();
                data.memberNickName = $("#inputNickName").val();
                data.memberPassword = $("#inputPassword").val();
                data.company = $("#inputCompany").val();
                data.department = $("#inputDepartment").val();

                main.save(JSON.stringify(data));
            }else{
                alert(RESULT_MESSAGE.FAIL_VALIDATION)
                return;
            }
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

        $("#checkPassword").on("focus", function (){
            let inputPassword = $("#inputPassword").val();

            if(!main.isValidPassword(inputPassword)){
                alert("유효하지 않은 비밀번호입니다. (최소 8자 이상 / 최소 하나의 문자, 하나의 숫자 반드시 포함)");
                $("#passwordValidationResult").val(false);
                $("#inputPassword").focus();
                return
            }

            $("#checkPassword").attr("disabled", false);
        });

        $("#checkPassword").on("blur", function (){
            let validResult = $("#passwordValidationResult");
            let errorMessage = $("#passwordErrorMessage");
            validResult.val(false);
            let inputPassword = $("#inputPassword").val();
            let checkPassword = $("#checkPassword").val();

            if(inputPassword === checkPassword){
                errorMessage.empty();
                errorMessage.hide();
                validResult.val(true);
                return;
            }else{
                errorMessage.text("입력하신 비밀번호가 일치하지 않습니다.");
                errorMessage.show();
                return;
            }
        })
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
    isValidPassword: function (password){
        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
        
        return passwordRegex.test(password);

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