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
            },
            error: function (x, h, r){
                console.error(x);
                alert(RESULT_MESSAGE.FAIL_SYSTEM)
            }

        })
    }
}

index.init()