<%-- 
    Document   : addNews
    Created on : 19.01.2011, 0:13:28
    Author     : RSDENIS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="www.DataModel,www.News,java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% if (request.getParameter("submitNews")!=null){%>
<jsp:useBean id="newNews" class="www.News" scope="page">
    <%--<jsp:setProperty name="newNews" property="title" param="title" />
    <%System.out.println(newNews.getTitle());%>--%>
    <jsp:setProperty name="newNews" property="*"/>
    <%DataModel.instance().addNews(newNews); %>
</jsp:useBean>
<%}
%>
<%@include file="WEB-INF/jspf/denisHeader.jspf" %>
<%@include file="WEB-INF/jspf/left.jspf" %>
                <td width="75%">
                    <form action="#" method="get">
                        <p>
                        Input title<br>
                        <input type="text" name="title"><br>
                        </p>
                        <p>
                        Input description<br>
                        <textarea cols="45" rows="5" name="description"></textarea></p>
                        <p>
                        Input text<br>
                        <textarea cols="45" rows="5" name="text"></textarea></p>
                            <input type="hidden" name="author" value="<%=request.getRemoteUser() %>">
                            <input type="hidden" name="date" value="<%=System.currentTimeMillis() %>">
                            <p>
                                <input type="submit" name="submitNews" value="GO!!!">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>

