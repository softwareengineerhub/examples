<%--
    Document   : sites
    Created on : 14.12.2011, 23:09:22
    Author     : Rolan
    TODO: rol: система сообщений отображается для всех, для владельца ресурса кнопка добавить
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.conarhco.rating.*,java.util.Collection"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="сайты"/>
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

<%--! String login="Luis"; --%>
<jsp:useBean id="siteManager" class="com.conarhco.rating.SiteManager" scope="page" >
    <c:set target="${siteManager}" property="siteId" value="${param.sid}"/>
</jsp:useBean>
<jsp:useBean id="userManager" class="com.conarhco.rating.UserManager" scope="page" >
    <c:set target="${userManager}" property="siteId" value="${param.sid}"/>
</jsp:useBean>
<jsp:useBean id="notesManager" class="com.conarhco.rating.VisitorNotesManager" scope="page" >
    <c:set target="${notesManager}" property="siteId" value="${param.sid}"/>
    <c:set target="${notesManager}" property="max" value="2"/><!-- настраивает максимальное количетсво заметок на странице -->
</jsp:useBean>
<table width="1024px"  align="center" border="0" cellpadding="0" cellspacing="10" >
    <tr>
        <td align="right" valign="top">
            <table>
                <tr>
                    <td>
                        <!-- Название ресурса -->
                        <table border="0" cellpadding="0" cellspacing="0" width="650">
                            <tr>
                                <td background="images/sites_background_top_1.png" height="9">
                                </td>
                            </tr>
                            <tr>
                                <td background="images/sites_background_center_1.png">
                                    <table border="0" width="100%" cellspacing="10" cellpadding="0">
                                        <tr>
                                            <td valign="top" align="left">
                                                <%--img src="<%=sp.getScreenshot()%>" border="0"--%>

                                                <img src="<c:out value='${siteManager.siteProfile.screenshot}' />">

                                            </td>
                                            <td width="100%">
                                                <table height="160" >
                                                    <tr>
                                                        <%--td align="left" class="site_text_bold"><%=sp.getName()%></td--%>
                                                        <td align="left" class="site_text_bold"><c:out value="${siteManager.siteProfile.name}" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td height="100%" align="left" class="site_text" style="vertical-align:top;">
                                                            <%--=sp.getDesc()--%>
                                                            <c:out value="${siteManager.siteProfile.desc}" />
                                                            <%--<c:out value="${siteManager.countersTotal}" />--%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                            <%--a href="#" style="font-family:Arial; font-weight:bold; font-size:12; color:#00abab"><%=sp.getDirectLink()%></a--%>
                                                            <a href="<c:out value="${siteManager.siteProfile.directLink}"/>" style="font-family:Arial; font-weight:bold; font-size:12; color:#00abab" onfocus="this.blur();" target="_blank"><c:out value="${siteManager.siteProfile.directLink}"/></a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>                                        
                                        <tr>
                                            <td colspan="2" >
                                                <table border="0" width="100%">
                                                    <tr>
                                                        <td width="100%">
                                                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                                <c:forEach var="countersPart" items="${siteManager.splittedSiteCounters}" varStatus="partInd">
                                                                    <tr>
                                                                        <c:set var="counts" value="4"/>
                                                                        <c:forEach var="counter" items="${countersPart}" varStatus="counterInd">
                                                                            <td align="right">
                                                                                <img src="images/sites_res_stat_bg_left.png" border="0" style="position:relative; top:0px; left:0px;"/>
                                                                            </td>
                                                                            <td background="images/sites_res_stat_bg_center.png" height="30" align="center" width="24%">
                                                                                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                                                    <tr>
                                                                                        <td valign="middle" align="right">
                                                                                            <%--Рекомендаций--%>
                                                                                            <a href="vote<c:forEach var="v" items="${param}" varStatus="attrNameCounter"><c:choose><c:when test="${attrNameCounter.index == 0}">?cid=<c:out value="${counter.id}"/></c:when></c:choose><c:if test="${v.key != 'cid'}">&<c:out value="${v.key}"/>=<c:out value="${v.value}"/></c:if></c:forEach>" class="vote_link_counter" onfocus="this.blur();"><c:out value="${counter.count}"/></a>
                                                                                        </td>
                                                                                        <td valign="middle" align="left" style="padding-left: 5px; padding-bottom: 2px;">
                                                                                            <%--=DataModule.getInstance().getTotalAmountOfCounters(sp)--%>
                                                                                            <a href="vote<c:forEach var="v" items="${param}" varStatus="attrNameCounter"><c:choose><c:when test="${attrNameCounter.index == 0}">?cid=<c:out value="${counter.id}"/></c:when></c:choose><c:if test="${v.key != 'cid'}">&<c:out value="${v.key}"/>=<c:out value="${v.value}"/></c:if></c:forEach>" class="vote_link_text" onfocus="this.blur();"><c:out value="${counter.name}"/></a>
                                                                                        </td>
                                                                                    </tr>
                                                                                </table>
                                                                            </td>
                                                                            <td align="left">
                                                                                <img src="images/sites_res_stat_bg_right.png" border="0" style="position:relative; top:0px; left:0px;"/>
                                                                            </td>
                                                                            <c:if test="${counterInd.index < 3}">
                                                                                <td width="1%">&nbsp;</td>
                                                                            </c:if>
                                                                            <c:set var="counts" value="${counterInd.index + 1}"/>
                                                                            </c:forEach>
                                                                            <%--<c:if test="${counts < 3}">
                                                                             <td colspan="<c:out value="15"/>" width="100%">
                                                                                &nbsp;
                                                                            </td>
                                                                            </c:if>--%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td height="5" width="100%" colspan="15"></td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </table>
                                                            </td>
                                                        </tr>

                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                    </td>
                                </tr>
                                <tr>
                                    <td background="images/sites_background_bottom_1.png" height="9"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <!-- Заметки пользоватлея -->
                        <td style="padding-top:20px;">
                            <table width="100%" height="42" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td  background="images/sites_note_backgroups_left.png" width="7">
                                    </td>
                                    <td background="images/sites_note_backgroups_center.png" height="42">
                                        <table border="0" width="100%" style="padding-left: 3px;padding-right:3px;">
                                            <tr>
                                                <td class="site_text_bold" align="left">
                                            	Заметки пользователя
                                                </td>
                                                <td align="right" valign="middle">
                                                    <!--Активация добавления заметки пользователя -->
                                                    <a href="addnote.jsp?sid=<c:out value="${param.sid}"/>" onfocus="this.blur();"><img src="images/btn_add_note_sel.png" border="0" height="29" style="vertical-align:middle;"/></a>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="7" background="images/sites_note_backgroups_right.png">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top:20px;">
                            <table border="0" width="650">
                                <!-- Блок для вывода заметок -->
                                <c:forEach var="note" items="${notesManager.notes}">
                                    <tr>
                                        <td align="left">
                                            <b><c:out value="${note.name}"/></b>
                                        </td>
                                        <td rowspan="2" style="padding-left:10px;" align="left">
                                            <%-- TODO: denis: БАГ: Растягивает страницу длительной последовательностью без пробелов --%>
                                            <c:out value="${note.note}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                            <b>Дата: <c:out value="${note.dateString}"/></b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="padding-top:5px;padding-bottom:5px;">
                                            <img src="images/horizontal_delimeter_line_site_2.png"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
            <td align="left" valign="top">
                <table align="left" border="0" cellpadding="0" cellspacing="0" style="padding-top:3px;">
                    <tr>
                        <!-- КОНТАКТНАЯ ИНФОРМАЦИЯ -->
                        <td>
                            <table border="0" width="285" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td background="images/sites_background_top.png" height="9">
                                    </td>
                                </tr>
                                <tr>
                                    <td background="images/sites_background_center.png">
                                        <table align="center" style="padding-left: 10px; padding-right: 10px;">
                                            <tr>
                                                <td class="site_text_bold" align="center">
                                                    КОНТАКТНАЯ ИНФОРМАЦИЯ
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="site_text" align="left">
                                                    <%--UserProfile userProfile=DataModule.getInstance().getProfile(login);--%>
                                                    Страна: <c:out value="${userManager.userProfile.country}"/>, город <c:out value="${userManager.userProfile.city}"/><br>
                                                    Адрес: <c:out value="${userManager.userProfile.street}"/>, <c:out value="${userManager.userProfile.house}"/><br>
                                                    Тел./Факс: <c:out value="${userManager.userProfile.homeTel}"/>, <c:out value="${userManager.userProfile.mobTel}"/><br>
                                                    e-mail: <c:out value="${userManager.userProfile.email}"/><br>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td background="images/sites_background_bottom.png" height="9">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <!-- СОБЫТИЯ -->
                        <td style="padding-top:15px;">
                            <c:if test="${not empty user}">
                                <table border="0" width="285" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td background="images/sites_background_top.png" height="9"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td background="images/sites_background_center.png">
                                            <table border="0">
                                                <tr>
                                                    <td height="100%">
                                                        <table align="center" style="padding-left: 10px; padding-right: 10px;">
                                                            <tr>
                                                                <td class="site_text_bold" align="center">
                                                                    СОБЫТИЯ
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="site_text" align="center">
                                                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="site_text" align="center">
                                                                    <img src="images/horizontal_delimeter_line_site_3.png" border="0"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="site_text" align="center">
                                                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="site_text" align="center">
                                                                    <img src="images/horizontal_delimeter_line_site_3.png" border="0"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="site_text" align="center">
                                                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right">
                                                        <a href="#" onfocus="this.blur();"><img src="images/btn_all.png" border="0" style="padding-bottom: 5px;padding-right: 5px;"/></a>                                            </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td background="images/sites_background_bottom.png" height="9"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <%@include file="WEB-INF/jspf/footer.jspf" %>