let main = {
    init: function (){
        $("#movePostSavePage").on("click", function (){
            let boardId = $("#boardId").val();
            location.href = "/board/"+boardId+"/post/save"
        })
    }
}

main.init();