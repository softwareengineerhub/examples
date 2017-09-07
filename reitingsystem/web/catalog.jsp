<%-- 
    Document   : catalog
    Created on : 14.12.2011, 22:36:28
    Author     : Rolan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.conarhco.rating.*,java.util.Collection, java.util.Map"%>

<jsp:useBean id="catalog" class="com.conarhco.rating.Catalog" scope="page">
    <c:choose>
        <c:when test="${empty param.cat}">
            <c:set target="${catalog}" property="categoryId" value="0"/>
        </c:when>
        <c:otherwise>
            <c:set target="${catalog}" property="categoryId" value="${param.cat}"/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty param.subCat}">
            <c:set target="${catalog}" property="subCategoryId" value="0"/>
        </c:when>
        <c:otherwise>
            <c:set target="${catalog}" property="subCategoryId" value="${param.subCat}"/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty param.type}">
            <c:set target="${catalog}" property="catalogType" value="<%=Catalog.CatalogType.CATALOG_ALL.getType()%>"/>
        </c:when>
        <c:otherwise>
            <c:set target="${catalog}" property="catalogType" value="${param.type}"/>
        </c:otherwise>
    </c:choose>
</jsp:useBean>

<c:choose>
    <c:when test="${param.type == 1}">
        <c:set var="title" value="сайты"/>
    </c:when>
    <c:when test="${param.type == 2}">
        <c:set var="title" value="магазины"/>
    </c:when>
    <c:when test="${param.type == 99}">
        <c:set var="title" value="каталог"/>
    </c:when>
</c:choose>
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

<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="30" >
    <c:if test="${empty sessionScope.searchResultBean || not sessionScope.searchResultBean.searchMode}">
    <tr>
        <td align="center" style="position:relative; top:-20px;">            
            <!-- Radiobuttons block -->
            <table>
                <tr>
                    <c:forEach var="catParts" items="${catalog.categories}" varStatus="ind">
                        <c:if test="${ind.index > 0}">
                            <td style="padding-left:20px;padding-right:20px;">
                                <img src="images/vertical_delimeter_line_1.png" />
                            </td>
                        </c:if>
                        <td valign="top">
                            <table cellpadding="5">
                                <c:forEach var="category" items="${catParts}">
                                    <tr>
                                        <td>                                            
                                            <c:choose>
                                                <c:when test="${category.key == catalog.categoryId}">
                                                    <img src="images/radio_button_sel.png" />
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="catalog.jsp<c:forEach var="v" items="${param}" varStatus="attrNameCounter"><c:choose><c:when test="${attrNameCounter.index == 0}">?cat=<c:out value="${category.key}"/></c:when></c:choose><c:if test="${v.key != 'cat' && v.key != 'subCat'}">&<c:out value="${v.key}"/>=<c:out value="${v.value}"/></c:if></c:forEach>" onfocus="this.blur();"><img src="images/radio_button_def.png" border="0"/></a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>                                        
                                            <c:choose>
                                                <c:when test="${category.key == catalog.categoryId}">
                                                    <td class="catalog_rb_text_sel" align="left">
                                                        <c:out value="${category.value}"/>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td align="left">
                                                        <a href="catalog.jsp<c:forEach var="v" items="${param}" varStatus="attrNameCounter"><c:choose><c:when test="${attrNameCounter.index == 0}">?cat=<c:out value="${category.key}"/></c:when></c:choose><c:if test="${v.key != 'cat' && v.key != 'subCat'}">&<c:out value="${v.key}"/>=<c:out value="${v.value}"/></c:if></c:forEach>" class="catalog_rb_text" onfocus="this.blur();"><c:out value="${category.value}"/></a>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="images/horizontal_delimeter_line_site_1.png">
        </td>
    </tr>
    <tr>
        <!-- subitems block -->
        <td align="center">
            <table border="0" width="750">
                <tr>
                    <td valign="top" align="center">
                        <c:forEach var="subCat" items="${catalog.subCategories}" varStatus="ind">                            
                            <c:choose>
                                <c:when test="${subCat.key == param.subCat || (ind.index == 0 && empty param.subCat)}">
                                    <font class="catalog_rb_suitems_text_sel">
                                        <c:out value="${subCat.value}"/>
                                    </font>
                                </c:when>
                                <c:otherwise>
                                    <a href="catalog.jsp<c:forEach var="v" items="${param}" varStatus="attrNameCounter"><c:choose><c:when test="${attrNameCounter.index == 0}">?subCat=<c:out value="${subCat.key}"/></c:when></c:choose><c:if test="${v.key != 'subCat'}">&<c:out value="${v.key}"/>=<c:out value="${v.value}"/></c:if></c:forEach>" class="catalog_rb_suitems_text" onfocus="this.blur();"><c:out value="${subCat.value}"/></a>
                                </c:otherwise>                                
                            </c:choose>
                            <c:if test="${ind.index + 1 < catalog.subCategoriesSize}">
                                <img src="images/vertical_delimeter_line_catalog_1.png" class="catalog_subitems_delim">
                            </c:if>
                        </c:forEach>                        
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    </c:if>
    <tr>
        <!-- resources list -->
        <td align="center">
            <table border="0" cellspacing="5" cellpadding="5">                
                <c:choose>
                    <c:when test="${not empty sessionScope.searchResultBean && sessionScope.searchResultBean.searchMode}">
                        <c:set var="sites" value="${sessionScope.searchResultBean.sites}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="sites" value="${catalog.sites}"/>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="site" items="${sites}" varStatus="siteInd">
                    <c:set var="profile" value="${site.siteProfile}"/>
                    <tr>
                        <td style="font-weight:bold;" valign="top">
                            <c:out value="${siteInd.index + 1}"/>.
                        </td>
                        <td valign="top">
                            <a href="sites.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();">
                                <img src="<c:out value='${profile.screenshot}' />" width="200" height="150" border="0">
                            </a>
                        </td>
                        <td valign="top" width="350">
                            <table>
                                <tr>
                                    <td style="font-weight:bold; text-align:left; font-size:larger;">
                                        <a href="sites.jsp?sid=<c:out value="${profile.id}"/>" onfocus="this.blur();" style="color: black;">
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
                            <img src="images/vertical_delimeter_line_2.png">
                        </td>
                        <td valign="top">
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
                </c:forEach>                
            </table>
            <c:if test="${not empty sessionScope.searchResultBean}">
                <c:set target="${sessionScope.searchResultBean}" property="searchText" value="${null}"/>
            </c:if>            
        </td>
    </tr>
</table>

<%@include file="WEB-INF/jspf/footer.jspf" %>