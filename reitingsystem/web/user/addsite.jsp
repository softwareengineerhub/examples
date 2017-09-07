<%-- 
    Document   : addsite
    Created on : 23.12.2011, 23:02:04
    Author     : Rolan
    TODO: +. rol: перенести поля со страницы регистрации сюда - для незаригистрированного пользователя
    TODO: +. rol: перенести сюда данные (город и тд, включая, блядь, емейл) из профиля
    TODO: +. rol: добавить опции (радио) платные-бесплатные

--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.conarhco.rating.DataModule, java.util.Map,java.util.List, com.conarhco.rating.CounterTypesBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>

<c:set var="title" value="добавить сайт"/>
<%@include file="../WEB-INF/jspf/user.jspf" %>
<jsp:useBean class="com.conarhco.rating.CounterTypesBean" id="counterNames" scope="page" />
<%-- TODO: conarh: Сделать добавление-удаление скриншота сайта --%>

<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="10">
    <form action="addsiteimage" enctype="multipart/form-data" method="post">
        <c:if test="${param['sid']!=null}">
            <input type="hidden" name="sid" value="<c:out value="${param['sid']}"/>">
            <jsp:useBean class="com.conarhco.rating.SiteManager" id="siteManager" scope="page">
                <c:set target="${siteManager}" property="siteId" value="${param['sid']}"/>
            </jsp:useBean>
            <%-- Выставление параметров для редактирования --%>
            <c:set var="regtype" value="${siteManager.siteProfile.registrationType}"/>
        </c:if>
        <tr>
            <td>
                <table border="0" style="position:relative; left:120px">
                    <tr>
                        <td align="left" class="profile_header">
                                	Изображение
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left">
                            <img src="../images/image_template.png">
                        </td>
                        <td valign="top" align="left" style="padding-left: 10px;">
                            <input type="file" style="width:295px;" />
                        </td>
                        <!--<td valign="top" align="left" style="padding-left: 10px;">
                            <a href="#"><img src="../images/btn_browse_sel.png" border="0"></a>
                        </td>-->
                        <td valign="top" align="left" style="position:relative; left: -5px;">
                            <a href="#"><img src="../images/btn_load_sel.png" border="0" onclick="this.submit();return true;"></a>
                        </td>
                    </tr>
                    <!--<tr>
                        <td colspan="4" align="right">
                            <a href="#" class="add_site_link">Удалить изображение</a>
                        </td>
                    </tr>-->
                </table>
            </td>
        </tr>
    </form>
    <tr>
        <td >
            <img src="../images/horizontal_delimeter_line_profile.png" style="padding-left:60px; padding-top:10px; padding-bottom: 10px;">
        </td>
    </tr>
    <tr>
        <td>
            <table cellpadding="3" cellspacing="0" style="position:relative; left:120px;" >
                <tr>
                    <td class="add_site_header">
                        Тип регистрации сайта
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="paytype" value="1" checked/>Бесплатная
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="paytype" value="2"/>Платная
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <img src="../images/horizontal_delimeter_line_profile.png" style="padding-left:60px; padding-top:10px; padding-bottom: 10px;">
        </td>
    </tr>
    <%-- TODO: conarh: Сделать редактирование данных сайта --%>
    <%-- TODO: conarh: При добавленном сайте сделать отображение кода вывода на страницу сайта (текстария, в нем в виде <а хреф>)
         учесть что при переносе с сервера на сервер  --%>
    <form id="addsite" action="addsite" method="post">
        <c:if test="${param['sid']!=null}">
            <input type="hidden" name="sid" value="<c:out value="${param['sid']}"/>">
        </c:if>
        <tr>
            <td>
                <table border="0" style="position:relative; left:120px; top:10px;">
                    <tr>
                        <td class="add_site_header" style="padding-left: 5px;">
                            Описание сайта
                        </td>
                    </tr>
                    <tr>
                        <td align="left">
                            <table>
                                <tr>
                                    <td valign="top">
                                        <table border="0" cellspacing="5">
                                            <c:set target="${counterNames}" property="type" value="Магазин"/>
                                            <tr>
                                                <td colspan="2" align="left">
                                                    <%-- TODO: Возможно сделать блоки счетчиков убирающимися (через джаваскрипт) --%>
                                                    <%-- TODO: Возможно сделать один из типов регистрации выбранным по умолчанию при добавлении сайта --%>
                                                    <input type="radio" name="regtype" value="<c:out value="${counterNames.type}"/>" <c:if test="${counterNames.type == regtype}">checked</c:if>><c:out value="${counterNames.type}"/>
                                                </td>
                                            </tr>
                                            <!--<tr>
                                                <td colspan="2" align="left" class="add_site_text">
                                                    <input type="radio" name="sitetype" value="shop" checked>Магазин<br>
                                                    Счетчики
                                                </td>
                                            </tr>-->
                                            <c:forEach var="counter" items="${counterNames.counters}" varStatus="count">
                                                <tr>
                                                    <td>
                                                        <%--<input type="checkbox" name="shopcounter<c:out value="${count.count}"/>" value="<c:out value="${counter.value}"/>">--%>
                                                        <input type="checkbox" name="<c:out value="${counterNames.type}"/>counter" value="<c:out value="${counter.value}"/>">
                                                    </td>
                                                    <td style="font-weight: bold;">
                                                        <c:out value="${counter.key}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <td valign="top">
                                        <table border="0" cellspacing="5">
                                            <c:set target="${counterNames}" property="type" value="Сайт"/>
                                            <tr>
                                                <td colspan="2" align="left">
                                                    <input type="radio" name="regtype" value="<c:out value="${counterNames.type}"/>" <c:if test="${counterNames.type == regtype}">checked</c:if>><c:out value="${counterNames.type}"/>
                                                </td>
                                            </tr>
                                            <c:forEach var="counter" items="${counterNames.counters}" varStatus="count">
                                                <tr>
                                                    <td>
                                                        <%--<input type="checkbox" name="sitecounter<c:out value="${count.count}"/>" value="<c:out value="${counter.value}"/>">--%>
                                                        <input type="checkbox" name="<c:out value="${counterNames.type}"/>counter" value="<c:out value="${counter.value}"/>">
                                                    </td>
                                                    <td style="font-weight: bold;">
                                                        <c:out value="${counter.key}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>

                                </tr>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td height="10">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <table cellspacing="5">
                                            <tr>
                                                <td class="add_site_text" align="left">
                                            	Категория
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left">
                                                    <c:set var="cats" value="${db.allCategories}" scope="page"/>
                                                    <%-- <c:set var="subCats" value="${db.allSubcategories}" scope="page"/>--%>

                                                    <select name="category" id="category" style="width:250px;">
                                                        <c:forEach var="cat" items="${cats}">
                                                            <option value="<c:out value="${cat.key}"/>"><c:out value="${cat.value}"/></option>
                                                        </c:forEach>
                                                    </select>

                                                    <%--   <select name="category" id="category" style="width:250px;">
                                                         <option value="0">Test1</option>
                                                         <option value="1">Test2</option>
                                                     </select>--%>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="20">

                                    </td>
                                    <td>
                                        <table cellspacing="5">
                                            <tr>
                                                <td class="add_site_text" align="left">
                                            	Подкатегория
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left">
                                                    <div>
                                                        <%-- <select name="subcategory" style="width:250px;">
                                                           <c:forEach var="cat" items="${cats}">
                                                                <c:forEach var="sub" items="${subCats[cat.value]}">
                                                                    <option value="<c:out value="${sub.value}"/>"><c:out value="${sub.key}"/></option>
                                                                </c:forEach>
                                                            </c:forEach>

                                                    </select>--%>

                                                        <%int index = 0;
                                                                    Map<Integer, Map<Integer, String>> map = DataModule.getInstance().getCategoryAndSubcategory();
                                                                    for (int category : map.keySet()) {%>
                                                        <select name="subcategory" id="sub<%=category%>" style="width:250px;">
                                                            <%Map<Integer, String> submap = map.get(category);
                                                                                                                                    for (int subcategory : submap.keySet()) {%>
                                                            <option value="<%=subcategory%>"><%=submap.get(subcategory)%></option>
                                                            <%}%>
                                                        </select>
                                                        <%index++;
                                                                    }%>


                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="20">

                                    </td>
                                    <td>
                                        <%-- TODO: conarh: Сделать добавление новой подкатегории --%>
                                        <table cellspacing="5">
                                            <tr>
                                                <td class="add_site_text" nowrap="nowrap" align="left">
                                            	Добавить новую категорию
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left">
                                                    <input name="newSubcategory" type="text" style="width: 250px;"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px; padding-left: 5px;" align="left">
                            <table >
                                <tr>
                                    <td class="add_site_text" align="left">
                                            	Название сайта
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <input name="siteName" type="text" style="width:250px;">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px; padding-left: 5px;">
                            <table >
                                <tr>
                                    <td class="add_site_text" align="left">
                                            	Ссылка
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <input name="siteLink" type="text" style="width:250px;">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px; padding-left: 5px;">
                            <table >
                                <tr>
                                    <td class="add_site_text" align="left">
                                            	Краткое описание
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <textarea name="siteDesc" rows="10" cols="70"></textarea>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>                    
                </table>
            </td>
        </tr>    
        <tr>
            <td>
                <img src="../images/horizontal_delimeter_line_profile.png" style="padding-left:60px; padding-top:10px; padding-bottom: 10px;">
            </td>
        </tr>
        <tr>
            <td valign="top" align="left">
                <!--<form name="registration" method="post" action="registration"  id="form">-->
                <table border="0" style="position:relative;left:120px;" cellpadding="5">
                    <tr>
                        <td class="add_site_header" align="left" colspan="2"  nowrap="nowrap">
                            Данные для регистрации пользователя
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
                                    <td class="add_site_text" align="left" nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" nowrap="nowrap" width="200">
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
                                    <td></td><td colspan="3"><div id="errorMessage" style="color:red"></div></td>
                                </tr>
                                <tr>
                                    <td class="add_site_text" align="left" width="100">
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
                                    <td class="add_site_text" align="left" width="100"  nowrap="nowrap">
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
                                        <div id="addButton"><a href="javascript:void(0);" onfocus="this.blur();"><img src="../images/btn_add_city.png" id="button" border="0"/></a></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="add_site_text" align="left" width="100"  nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" width="100"  nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" width="100"  nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" width="100"  nowrap="nowrap" >
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
                                    <td class="add_site_text" align="left" width="100" nowrap="nowrap">
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
                                    <td class="add_site_text" align="left" width="100" nowrap="nowrap">
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
                                    <td align="left" style="padding-top: 10px; padding-bottom:10px;">
                                        <a href="#"><img src="../images/btn_add_site.png" border="0" onclick="document.forms['addsite'].submit();return true;"></a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <!--</form>-->

            </td>
        </tr>
    </form>
</table>

<script type="text/javascript">
    $(document).ready(function() {

    <%for (int i : map.keySet()) {%>
            $("#sub<%=i%>").hide();
    <%}%>
            var current=$("#sub"+$("#category").attr("value").valueOf());
            current.show();

            $("#category").change(function(){
                var actual=$("#category").attr("value").valueOf();
                current.hide();
                current=$("#sub"+actual);
                current.show();
            });
        })

</script>
<script type="text/javascript" src="../js/validate_profile_change.js"></script>
<%@include file="../WEB-INF/jspf/user_footer.jspf" %>