<%-- 
    Document   : faq
    Created on : 11.01.2012, 12:09:39
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="FAQ"/>
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

<table border="0" cellpadding="5" cellspacing="5" width="800" align="center">
    <tr>
        <td class="faq_number">
            <ol>
                <li><a href="#1" class="faq_question">Могу ли я изменить логин или мне нужно перерегистрироваться?</a></li>
                <li><a href="#2" class="faq_question">Как я могу изменить название и описание сайта?</a></li>
                <li><a href="#3" class="faq_question">Как переместить мой сайт в другую категорию рейтинга?</a></li>
                <li><a href="#4" class="faq_question">Я не помню E-mail на который регистрировал счетчик и забыл пароль доступа. Как его узнать?</a></li>
                <li><a href="#5" class="faq_question">Я зарегистрировал свой сайт, но у него не показывается превью. Почему его нет и когда оно появится?</a></li>
                <li><a href="#6" class="faq_question">Как проиcходит голосование по рейтингу популярности сайта?</a></li>
            </ol>
        </td>
    </tr>
    <tr>
        <td class="faq_number">
            <ol>
                <li >
                    <a name="1"></a>
                    <font class="faq_question">Могу ли я изменить логин или мне нужно перерегистрироваться?</font><br/>
                        <div class="faq_answer">Нет, логин изменить нельзя, так как он уникален.</div>
                </li>
                <li>
                    <a name="2"></a>
                    <font class="faq_question">Как я могу изменить название и описание сайта?</font><br />
                        <div class="faq_answer">После авторизации (ввода логина и пароля) перейдите в Личный Кабинет -> Мои сайты, далее найдите нужный сайт и нажмите кнопку редактировать, измените данные и сохраните.</div>
                </li>
                <li>
                    <a name="3"></a>
                    <font class="faq_question">Как переместить мой сайт в другую категорию рейтинга?</font><br />
                        <div class="faq_answer">После авторизации (ввода логина и пароля) перейдите в Личный Кабинет -> Мои сайты, далее найдите нужный сайт и нажмите кнопку редактировать, измените данные и сохраните.</div>
                </li>
                <li>
                    <a name="4"></a>
                    <font class="faq_question">Я не помню E-mail на который регистрировал счетчик и забыл пароль доступа. Как его узнать?</font><br />
                        <div class="faq_answer">Вам необходимо перейти на страницу напоминание пароля.</div>
                </li>
                <li>
                    <a name="5"></a>
                    <font class="faq_question">Я зарегистрировал свой сайт, но у него не показывается превью. Почему его нет и когда оно появится?</font><br />
                        <div class="faq_answer">Превью для сайтов загружается как картинка в личном кабинете. </div>
                </li>
                <li>
                    <a name="6"></a>
                    <font class="faq_question">Как проиcходит голосование по рейтингу популярности сайта?</font><br />
                        <div class="faq_answer">Подсчет голосов происходит&nbsp;с защитой по уникальным IP адресам. Это сделано для того чтобы избежать возможной накрутки статистики популярности. Защита уникального IP сбрасывается один раз в несколько дней.</div>
                </li>               
            </ol>
        </td>
    </tr>
    <tr>
        <td align="center">
            <%-- TODO: denis: Почта саппорта должна грузиться из сервлетКонтекста как параметр инициализации (из веб.хмл) --%>
            <p><em><strong>Если вы не нашли ответа на ваш вопрос, пожалуйста, напишите нам письмо <a href="mailto:xxx@xxx.xxx" class="faq_question">xxx@xxx.xxx</a>.</strong></em></p>
        </td>
    </tr>
</table>
<%@include file="WEB-INF/jspf/footer.jspf" %>