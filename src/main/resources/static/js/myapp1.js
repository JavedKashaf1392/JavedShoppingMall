function validatePwds(){
    var newPwd = $('#newPwd').val();
    var confirmPwd = $('#confirmPwd').val();
    if(newPwd!=confirmPwd){
    $('#errId').text('New Password And Confirm Password Should Be Same');
    return false;
    }
    return true;
}

