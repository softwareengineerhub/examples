<%-- 
    Document   : index
    Created on : 25.10.2011, 23:19:39
    Author     : Конарх
    TODO: rol: сделать вместо регистрации + добавить сайт
 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page  import="com.conarhco.rating.*,java.util.Collection" %>



<%--
<jsp:useBean class="com.conarhco.rating.DataModule" id="dataModel" scope="page"/>
<c:set var="user" value="Denis" scope="session" />

Session user: <c:out value="${sessionScope.user}"/>
--%>

<c:set var="title" value="главная"/>

<%--<c:choose>
        <c:when test="${secPath == 1}"><c:set property="secPath" value="${pageContext.secPath}"/></c:when>
        <c:otherwise><c:set property="secPath" value="1"/></c:otherwise>
</c:choose>--%>

<%@include file="WEB-INF/jspf/header.jspf" %>
<!-- main part -->
<%-- TODO: denis(наоборот, желательно ровно 20, если меньше, то она гадостно отображается) - значит сделать отображения только четного количетсва: Возможно, на крутилке сделать меньше картинок --%>
<%-- TODO: conarh:сделать фукцию восстановления пароля (скидывать пароль в случайный набор цифр и букв размером символов в 12 и отправлять на зарегенную почту по логину) --%>
<%-- TODO: +conarh: сделать систему событий (база). События - это новости владельца сайта --%>
<%-- TODO: rol: сделать систему событий (страница). События - это новости владельца сайта (максимум их может быть 10, пока не учитывать) --%>
<%-- TODO: попробовать сделать удаление в базе событий для пользвателя триггером, что не получится имхо --%>
<%-- TODO: попробовать сделать уникальность вопросов в факью триггером --%>
<%-- TODO: +conarh: в базе сделать связь между страной и городом --%>

<!-- Needed for rotation -->
<link rel="stylesheet" type="text/css" href="js/autoPlay/imageRound.css">
<script type="text/javascript" src="js/autoPlay/jquery.js"></script>
<script type="text/javascript" src="js/autoPlay/jquery.roundabout.js"></script>
<script type="text/javascript" src="js/autoPlay/jquery.easing.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $( "#auth_form").hide(0);
         $( "#auth_img").hide(0);

        var interval,
        delay=3000,
        clickCount=0;
        $('.holder').roundabout({
            childSelector: '.item'
        })
        .hover(
        function() {
            // oh no, it's the cops!
            clearInterval(interval);
        },
        function() {
            interval = startAutoPlay(delay);
        }
    );
        // let's get this party started
        interval = startAutoPlay(delay);



        function startAutoPlay(delay) {
            return setInterval(function() {
                if(clickCount==0){
                    $('.holder').roundabout_animateToNextChild();
                }

            }, delay);

        }

        $('input[name*="searchText"]').focus(function(){
            clickCount++;
        });


        $('input[name*="searchText"]').focusout(function(){
            clickCount=0;
        });

        $("#simple_dialog").click(function(){
            clickCount++;
            $( "#auth_form").show();
            $( "#auth_img").show();
        });

        $("#cancel").click(function(){
            clickCount=0;
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
<!-- Needed for rotation -->
<table width="1024px"  align="center" border="0" cellpadding="0" cellspacing="30" >
    <tr>
        <td align="center">
            <img src="images/text_top20.png">
        </td>
    </tr>
    <tr>
        <td align="center">
            <div id="contentFlowWrapper" class="holder">
                <%
                            Collection<SiteProfile> collection = DataModule.getInstance().getTopSites();
                            int i=0;
                            for (SiteProfile sp : collection) {                                
                %>

                <div class="item">
                    <a href="sites.jsp?sid=<%=sp.getId()%>">
                        <img title="<%=sp.getName()%>" class="box one" id="pic<%=i++%>" alt="<%=sp.getName()%>" src="<%=sp.getScreenshot()%>"/>
                    </a>
                </div>
                <%}%>
            </div>

            <!-- ROTATION -->
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="images/horizontal_delimeter_line.png" />
        </td>
    </tr>
</table>
<%@include file="WEB-INF/jspf/footer.jspf" %>
