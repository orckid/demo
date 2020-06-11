$(function () {

    
});

$(document).keydown(function (event) {
    if (event.keyCode === 13) {
        userLogin();
    }
});

function userLogin() {
    let username = $('#username').val();
    let password = $("#password").val();

    if (!username || !password) {
        alert("用户名或密码不能为空");
        return;
    }
    alert("username:" + username + " ,password:" + password);
}