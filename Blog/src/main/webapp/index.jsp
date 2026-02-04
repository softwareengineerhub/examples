<%-- 
    Document   : index
    Created on : 04.12.2010, 2:13:58
    Author     : GETCRUNK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="www.DataModel,www.News,java.util.*, www.beans.NewsCollection" %>
<%@ page import="www.MyDao" %>
<%@include file="WEB-INF/jspf/denisHeader.jspf" %>
<jsp:useBean id="mostView" class="www.beans.NewsCollection" scope="application">
    <jsp:setProperty name="mostView" property="sort" value="view"/>
</jsp:useBean>
<jsp:useBean id="mostPopuler" class="www.beans.NewsCollection" scope="application">
    <jsp:setProperty name="mostPopuler" property="sort" value="decisions"/>
</jsp:useBean>
<jsp:useBean id="mostDate" class="www.beans.NewsCollection" scope="application">
    <jsp:setProperty name="mostDate" property="sort" value="date"/>
</jsp:useBean>
<%
    MyDao myDao = new MyDao();
    String login = myDao.getLogin();
    System.out.println("###############login="+login);
%>


    <% Collection<News> viewC = mostView.getNews();
       Iterator<News> viewI = viewC.iterator();
       Collection<News> populerC = mostPopuler.getNews();
       Iterator<News> populerI= populerC.iterator();
       Collection<News> dateC = mostDate.getNews();
       Iterator<News> dateI = dateC.iterator();
    %>
   <% for(int i=0;i<viewC.size();i++){
       News viewN = viewI.next();
       News populerN=populerI.next();
       News dateN = dateI.next();
    %>
<tr>

<a href="SearchServlet?title=<%=viewN.getTitle()%>&author=<%=viewN.getAuthor()%>">
    <td class="news"><br>Title: <%=viewN.getTitle()%><p><%=viewN.getDescription()%></p></td>
</a>
<a href="SearchServlet?title=<%=populerN.getTitle()%>&author=<%=populerN.getAuthor()%>"><td class="news"><br>Title: <%=populerN.getTitle()%><p><%=populerN.getDescription()%></p></td></a>
<a href="SearchServlet?title=<%=dateN.getTitle()%>&author=<%=dateN.getAuthor()%>"><td class="news"><br>Title: <%=dateN.getTitle()%><p><%=dateN.getDescription()%></p></td></a>
</tr>
<%}%>

<%@include file="WEB-INF/jspf/footer.jspf"%>