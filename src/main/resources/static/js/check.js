function check(){
    if(document.getElementById("title").value.trim().length == 0){
        alert("제목이 입력되지 않았습니다.")
        document.getElementById("title").focus();
        return false
    }
    if(document.getElementById("content").value.length == 0){
        alert("내용이 입력되지 않았습니다.")
        document.getElementById("content").focus();
        return false
    }
    alert("입력이 완료되었습니다.")
    document.getElementById("frm").submit()
    return true
}

function res(){
    alert("처음부터 다시 입력합니다.")
    document.getElementById("frm").reset()
    document.getElementById("title").focus();

}