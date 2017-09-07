<%-- 
    Document   : addnote
    Created on : 24.12.2011, 1:17:32
    Author     : Конарх
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Добавить заметку"/>
<%@include file="WEB-INF/jspf/secondary_header.jspf" %>
<%-- TODO: denis:(проверь окончательно) БАГ: некорректно отображается диалог авторизации, в IE8 у диалога везде неправильный фон --%>
<%-- TODO: denis:(проверь окончательно) проверить диалог авторизации на всех страницах и под всеми браузерами (IE 8,9 FifeFox, Opera). Если не работает - забить, и без этого дел по горло --%>
<form id="addnote" action="addnote" method="post">
    <input type="hidden" name="sid" value="<c:out value="${param.sid}"/>">
<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="10">
    <tr>
        <td>
            <table border="0" style="position:relative; left:120px; top:10px;">
                <tr>
                    <td style="padding-top: 10px; padding-left: 5px;">
                        
                            <table >

                                <tr>
                                    <td class="add_site_text" align="left">
                                            	Укажите Ваше имя
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <input type="text" style="width:250px;" name="name">
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
                                            	Текст заметки
                                </td>
                            </tr>
                            <tr>
                                <td align="left">
                                    <textarea rows="10" cols="70" name="note"></textarea>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="left" style="padding-top: 10px; padding-bottom:10px; padding-left: 10px;">
                        <!-- Добавление заметки -->
                        <a href="#"><img src="images/btn_add_note_sel.png" border="0" onclick="document.forms['addnote'].submit();return true;"></a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</form>

<%@include file="WEB-INF/jspf/footer.jspf" %>