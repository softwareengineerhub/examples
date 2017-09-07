<%-- 
    Document   : catalog
    Created on : 14.12.2011, 22:36:28
    Author     : Rolan
--%>
<%-- TODO: + rol: убрать кнопку статистики в меню, и добавить кнопку статистика к каждому сайту --%>
<%-- TODO: + rol: (нужно передавать параметр sid, например statistics.jsp?sid=1) привязать статистику к конкретным сайтам --%>
<%-- TODO: conarh: Проверить удаление сайтов --%>
<%-- TODO: denis: добавить фразу сайт просрочен для сайтов, уже не отображаемых --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.conarhco.rating.*,java.util.Collection, java.util.Map"%>

<jsp:useBean id="catalog" class="com.conarhco.rating.Catalog" scope="page">    
    <c:set target="${catalog}" property="userId" value="${sessionScope.user}"/>
</jsp:useBean>

<%@include file="../WEB-INF/jspf/user.jspf" %>

<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="30" >
    <c:if test="${not empty param.deletedName}">
    <tr>
        <td class="deleted_site_msg">
            <c:out value="${param.deletedName}"/> успешно удалён
        </td>
    </tr>
    </c:if>
    <tr>
        <!-- resources list -->
        <td align="center">
            <table border="0" cellspacing="5" cellpadding="5">
                <c:forEach var="site" items="${catalog.userSites}" varStatus="siteInd">
                    <c:set var="profile" value="${site.siteProfile}"/>
                    <tr>
                        <td style="font-weight:bold;" valign="top">
                            <c:out value="${siteInd.index + 1}"/>.
                        </td>
                        <td valign="top">
                            <a href="../sites.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();">
                                <img src="../<c:out value='${profile.screenshot}' />" width="200" height="150" border="0">
                            </a>
                        </td>
                        <td valign="top" width="350">
                            <table>
                                <tr>
                                    <td style="font-weight:bold; text-align:left; font-size:larger;">
                                        <a href="../sites.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();" style="color: black;">
                                            	<c:out value="${profile.name}" />
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-weight:bold; text-align:left;">
                                            	<c:out value="${profile.desc}" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td valign="top" style="padding-left:10px;padding-right:10px;">
                            <img src="../images/vertical_delimeter_line_2.png">
                        </td>
                        <td valign="top" rowspan="2">
                            <table width="250" cellpadding="0" cellpadding="0">
                                <tr>
                                    <td style="font-weight:bold; font-size:large; text-align:left;">
                                            	Рекомендаций: <c:out value="${site.totalAmountOfCounters}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-weight:bold; padding-top:5px; font-size:large; text-align:left;">
                                            	Критерий рекомендаций:
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-weight:bold; font-size:small; padding-top:5px; padding-left:10px; text-align:left;" nowrap>
                                        <c:forEach var="counter" items="${site.siteCounters}">
                                            <c:out value="${counter.name}"/>:&nbsp;<c:out value="${counter.count}"/><br>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td colspan="3" nowrap align="left" valign="top">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td>
                                        <a href="statistics.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();">
                                            <img src="../images/btn_statistics.png" border="0">
                                        </a>
                                    </td>
                                    <td style="padding-left: 10px;">
                                        <a href="addsite.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();">
                                            <img src="../images/btn_edit_site.png" border="0">
                                        </a>
                                    </td>                                    
                                    <td style="padding-left: 10px;">
                                        <form name="deletesite<c:out value="${siteInd.index}"/>" method="post" action="deletesite">
                                            <a href="javascript: document.deletesite<c:out value="${siteInd.index}"/>.submit();">
                                                <img src="../images/btn_delete_site.png" border="0">
                                            </a>
                                            <input name="sid" type="hidden" value="<c:out value="${profile.id}"/>">
                                            <input name="siteName" type="hidden" value="<c:out value="${profile.name}" />">
                                        </form>
                                    </td>
                                </tr>
                            </table>                            
                        </td>
                    </tr>
                </c:forEach>                
            </table>
        </td>
    </tr>
</table>

<%@include file="../WEB-INF/jspf/user_footer.jspf" %>