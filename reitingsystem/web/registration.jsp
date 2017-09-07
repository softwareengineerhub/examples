<%-- 
    Document   : registration
    Created on : 21.12.2011, 22:36:17
    Author     : Rolan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="правила"/>
<%@include file="WEB-INF/jspf/secondary_header.jspf" %>
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

<script type="text/javascript" src="js/validate_registration.js"></script>
<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="10" >
    <tr>
        <td valign="top" align="left">
            <form name="registration" method="post" action="registration"  id="form">
                <table border="0" style="position:relative;left:100px;" cellpadding="5">
                    <tr>
                        <td class="profile_header" align="left" colspan="2"  nowrap="nowrap">
                            Данные для регистрации                            
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="errorMessage" style="color: red">
                                <%if (request.getParameter("errorRegister") != null) {%>
                                Вы не зарегистрировались (такой логин уже существеут)
                                <%}%>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td class="profile_text" align="left" nowrap="nowrap">
                                	логин
                                    </td>
                                    <td align="left">
                                        <input id="login" type="text" style="width:195px;" name="login" />
                                    </td>
                                    <td align="left" valign="top" class="required_symbol">
                                        *
                                    </td>
                                </tr>
                                <tr>
                                    <td class="profile_text" align="left" nowrap="nowrap">
                                	пароль
                                    </td>
                                    <td align="left">
                                        <input id="password" type="password"  style="width:195px;" name="password" />
                                    </td>
                                    <td align="left" valign="top" class="required_symbol">
                                        *
                                    </td>
                                </tr>
                                <tr>
                                    <td class="profile_text" align="left" nowrap="nowrap" width="200">
                                	подтверждение пароля
                                    </td>
                                    <td align="left">
                                        <input id="password1" type="password" style="width:195px;" />
                                    </td>
                                    <td align="left" valign="top" class="required_symbol">
                                        *
                                    </td>
                                </tr>
                                <tr>
                                    <td class="profile_text" align="left" width="100" nowrap="nowrap">
                                	e-mail
                                    </td>
                                    <td align="left">
                                        <input id="email" type="text" style="width:195px;" name="email" />
                                    </td>
                                    <td align="left" valign="top" class="required_symbol">
                                        *
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" colspan="3">
                                        <table>
                                              <tr>
                                                  <td class="required_symbol">
                                                     *
                                                  </td>
                                                  <td class="required_text">
                                                      помечены поля, обязательные для заполнения
                                                  </td>
                                              </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="3" style="padding-top: 10px;">
                                        <a href="#" onfocus="this.blur();">
                                            <input type="image" id="create-user" src="images/btn_register.png" border="0">
                                        </a>
                                    </td>                                    
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </form>

        </td>
    </tr>
</table>

<%@include file="WEB-INF/jspf/footer.jspf" %>

