<%-- 
    Document   : conditions
    Created on : 14.12.2011, 22:53:37
    Author     : Rolan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="условия"/>
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
            <img src="images/text_conditions.png">
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="images/horizontal_delimeter_line_1.png">
        </td>
    </tr>

    <tr>
        <td>
            <table align="center">
                <tr>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                            Если прямо не указано иное, выводы, толкования и заключения, содержащиеся в материалах на настоящем сайте, принадлежат различным сотрудниками xxx, консультантам и советникам xxx, подготовившим соответствующие материалы
                    </td>
                    <td class="rules_img" >
                        <img src="images/vertical_delimeter_line.png">
                    </td>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                             xxx обеспечивает бесплатный доступ к настоящему веб-сайту («сайту») для всех желающих («пользователей»). Представленная на нем информация носит исключительно ознакомительный характер. xxx разрешает пользователям посещать сайт и сгружать и копировать информацию, документы и материалы (обобщенно именуемые «материалы») с сайта для личных некоммерческих нужд пользователя без каких бы то ни было прав перепродавать или распространять их или создавать подборки или обобщающие документы на их основе
                    </td>
                    <td class="rules_img">
                        <img src="images/vertical_delimeter_line.png"  >
                    </td>
                    <td style="width: 250px; padding-left: 10px;padding-right: 10px; vertical-align: top; font-weight:bold; text-align: justify;">
                            xxx осуществляет администрирование настоящего сайта. На все материалы xxx, размещенные на настоящем сайте, распространяются настоящие условия
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