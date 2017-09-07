$(document).ready(function() {
     $("#auth_img").hide();
    $("#auth_form").hide();
    


    $("#simple_dialog").click(function(){
        $( "#auth_form").show();
         $("#auth_img").show();
    });

    $("#cancel").click(function(){
        $("#auth_form").hide();
         $("#auth_img").hide();
    });

    $("#form_login_field").click(function(){
        var val=$(this).attr('value')
        if(val==""||val=="логин"){
            $(this).attr('value', '');
        }
        $(this).css('color', 'black');
    });


    $("#form_password_field").click(function(){
        $(this).attr('value', '');
    });

    $('#password-clear').show();
    $('#password-password').hide();

    $('#password-clear').focus(function() {
        $('#password-clear').hide();
        $('#password-password').show();
        $('#password-password').focus();
    });
    $('#password-password').blur(function() {
        if($('#password-password').val() == '') {
            $('#password-clear').show();
            $('#password-password').hide();
        }
    });
});
 $("#auth_img").hide();
$( "#auth_form").hide();
