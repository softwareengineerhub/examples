<%-- 
    Document   : profile
    Created on : 21.12.2011, 22:43:09
    Author     : Rolan
    TODO: rol: оставить здесь только смену пароля. (закомментить возможную смену емейла). вообще всё кроме смены пароля ? и как тогда менять инфу о себе ?
--%>

<%-- TODO: conarh: приделать вывод сообщений о изменении профиля и изменении пароля --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.16.custom.min.js"></script>
<style>
    .error_input{
        border-color: red;
    }

</style>

<jsp:useBean id="userManager" class="com.conarhco.rating.UserManager" scope="page" >
    <c:set target="${userManager}" property="login" value="${sessionScope.user}"/>
</jsp:useBean>



<c:set var="title" value="правила"/>
<%@include file="/WEB-INF/jspf/user.jspf"%>


<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="10" >
    <tr>
        <td valign="top" align="left">
            <form name="changeprofile" id="changeprofile" method="post" action="changeprofile">
                <table border="0" style="position:relative;left:100px;" cellpadding="5" cellspacing="0">
                    <tr>
                        <td class="profile_header" align="left" colspan="4"  nowrap="nowrap">
                                	Контактная информация
                        </td>
                    </tr>
                    <tr>
                        <td></td><td colspan="3"><div id="errorMessage" style="color:red"></div></td></td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100">
                                	страна
                        </td>
                        <td align="left">
                            <select style="width:200px;" name="country">
                                <sql:query dataSource="jdbc/rating" sql="SELECT country FROM countries" var="countries"></sql:query>
                                <c:forEach var="countriesRow" items="${countries.rows}">
                                    <c:choose>
                                        <c:when test="${countriesRow.country==userManager.userProfile.country}">
                                            <option selected><c:out value="${countriesRow.country}"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option><c:out value="${countriesRow.country}"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100"  nowrap="nowrap">
                                	город
                        </td>
                        <td align="left">
                            <div id="city">
                                <sql:query dataSource="jdbc/rating" sql="SELECT city FROM cities" var="cities"></sql:query>
                                <select style="width:200px" name="city" id="cityList" >
                                    <c:forEach var="citiesRow" items="${cities.rows}">
                                        <c:choose>
                                            <c:when test="${citiesRow.city==userManager.userProfile.city}">
                                                <option selected><c:out value="${citiesRow.city}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option><c:out value="${citiesRow.city}"/></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div id="cityRow" >
                                <input style="width:195px" type='text' name='addNewCity' id="cityField"/>
                                <input type="hidden" name="cityToStoreInDB" id="cityToStoreInDB" />
                            </div>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                        <td>
                            <div id="addButton"><a href="#" onfocus="this.blur();"><img src="../images/btn_add_city.png" id="button" border="0"/></a></div>
                        </td>                        
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100"  nowrap="nowrap">
                                	улица
                        </td>
                        <td align="left">
                            <input type="text" style="width:195px;" name="street" id="street" value="<c:out value='${userManager.userProfile.street}'/>"/>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100"  nowrap="nowrap">
                                	дом
                        </td>
                        <td align="left">
                            <input type="text" style="width:195px;" name="house" id="house" value="<c:out value='${userManager.userProfile.house}'/>"/>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100"  nowrap="nowrap">
                                	телефон
                        </td>
                        <td align="left">
                            <input type="text" style="width:195px;" name="phone" id="phone" value="<c:out value='${userManager.userProfile.homeTel}'/>"/>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100"  nowrap="nowrap" >
                                	раб. тел.
                        </td>
                        <td align="left">
                            <input type="text" style="width:195px;" name="work_phone" id="work_phone" value="<c:out value='${userManager.userProfile.workTel}'/>"/>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" width="100" nowrap="nowrap">
                                	моб. тел.
                        </td>
                        <td align="left">
                            <input type="text" style="width:195px;" name="mobile_phone" id="mobile_phone" value="<c:out value='${userManager.userProfile.mobTel}'/>"/>
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
                            <input type="text" style="width:195px;" name="email" id="email" value="<c:out value='${userManager.userProfile.email}'/>"/>
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
                        <td align="right" colspan="3">
                            <a href="#" onfocus="this.blur();">
                            <input type="image" src="../images/btn_save.png" border="0"/>
                            </a>
                        </td>

                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../images/horizontal_delimeter_line_profile.png" style="padding-left:60px; padding-top:-10px;">
        </td>
    </tr>
    <tr>
        <td valign="top" align="left">
            <form id="form" method="post" action="../changepass">
                <table border="0" style="position:relative;left:100px;" cellpadding="5">
                    <tr>
                        <td class="profile_header" align="left" colspan="3" nowrap="nowrap">
                                	Изменить пароль аккаунта
                        </td>
                    </tr>
                    <tr><td  colspan="2" align="left" nowrap="nowrap"><div id="errorMessagePassword" style="color: red">
                                 <%if (request.getParameter("errorChangePassword") != null) {%>
                                Пароль не был изменен (неправильный старый пароль)
                                <%}%>

                            </div></td></tr>
                    <tr>
                        <td class="profile_text" align="left" nowrap="nowrap">
                                	текущий пароль
                        </td>
                        <td align="left">
                            <input  type="password" style="width:195px;color:black" name="oldPassword" <%--value="<c:out value='${userManager.userProfile.password}'/>"  disabled--%>/>
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" nowrap="nowrap">
                                	введите новый пароль
                        </td>
                        <td align="left">
                            <input type="password" style="width:195px;" name="password" id="password" />
                        </td>
                        <td align="left" valign="top" class="required_symbol">
                            *
                        </td>
                    </tr>
                    <tr>
                        <td class="profile_text" align="left" nowrap="nowrap">
                                	подтвердите новый пароль
                        </td>
                        <td align="left">
                            <input type="password" style="width:195px;"  name="password1" id="password1"/>
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
                        <td align="right" colspan="3">
                           
                            <input type="image" src="../images/btn_change_pass.png" border="0"/>
                           
                        </td>
                    </tr>
                </table>
            </form>
            <script type="text/javascript" src="../js/validation_changepassword.js"></script>
            <script type="text/javascript" src="../js/validate_profile_change.js"></script>
        </td>
    </tr>
</table>

<%@include file="/WEB-INF/jspf/user_footer.jspf" %>
