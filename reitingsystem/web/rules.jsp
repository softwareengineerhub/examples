<%-- 
    Document   : rules
    Created on : 14.12.2011, 22:57:53
    Author     : Rolan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="правила"/>
<%@include file="WEB-INF/jspf/header.jspf" %>
 <script type="text/javascript">
            $(document).ready(function() {
                $( "#auth_form").hide(0);
                $( "#auth_img").hide(0);


                $("#simple_dialog").click(function(){
                    $( "#auth_form").show();
                    $( "#auth_img").show();
                });

                $("#cancel").click(function(){
                    $("#auth_form").hide();
                    $( "#auth_img").hide();

                });

                 $("#form_login_field_value").hide();
                  $("#form_login_field").show();

                $("#form_login_field").focus(function(){
                        $("#form_login_field").hide();
                        $("#form_login_field_value").show();
                         $('#form_login_field_value').focus();
                });
                 $('#form_login_field_value').blur(function() {
                    if($('#form_login_field_value').val() == '') {
                        $('#form_login_field').show();
                        $('#form_login_field_value').hide();
                    }
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
        </script>

<table width="1024"  align="center" border="0" cellpadding="0" cellspacing="30" >
    <tr>
        <td align="center">
            <img src="images/text_rules.png">
        </td>

    </tr>
    <tr>
        <td align="center">
            <img src="images/horizontal_delimeter_line_1.png">
        </td>
    </tr>

    <tr>
        <td>
            <table align="center" border="0" width="100%">
                <tr>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                            Данные правила распространяются на всех посетителей и пользователей Web-сайта xxx Используя материалы сайта, пользователь выражает свое согласие с данными правилами. Компания xxx сохраняет за собой право изменять существующие правила и инструкции по своему усмотрению. При нарушении правил xxx оставляет за собой право применять все допустимые законом средства по отношению к нарушителям. Данные правила распространяются на настоящих и будущих посетителей сайта xxx
                    </td>
                    <td class="rules_img" >
                        <img src="images/vertical_delimeter_line.png">
                    </td>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                            xxx не берет на себя никакой ответственности за точность информации, предоставляемой на сайте, и весь риск использования такой информации возлагается на пользователя. На этом Web-сайте могут содержаться другие замечания о правах собственности и информация об авторских правах; содержащиеся в них положения также должны быть учтены и соблюдены. В представленной на этом Web-сайте информации могут встретиться технические неточности или типографские опечатки.
                    </td>
                    <td class="rules_img">
                        <img src="images/vertical_delimeter_line.png"  >
                    </td>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                            xxx оставляет за собой право изменять предоставляемые данные, услуги и продукцию в любое время без предварительного уведомления. Ссылка на продукцию и услуги третьих лиц делается в информационных целях и не подразумевает, что xxx их поддерживает или рекомендует.
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="images/horizontal_delimeter_line_1.png" />
        </td>
    </tr>
</table>

<%@include file="WEB-INF/jspf/footer.jspf" %>
