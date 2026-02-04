<%-- 
    Document   : search
    Created on : 28.01.2011, 20:53:44
    Author     : RSDENIS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="www.DataModel,www.News, java.util.ArrayList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%!int index=0; int size=0;%>
<%@include file="WEB-INF/jspf/denisHeader.jspf" %>
<%@include file="WEB-INF/jspf/left.jspf" %>
<%ArrayList<News> searchRes = DataModel.instance().getSearchValues(request.getParameter("search"));%>
                <td width="75%">
                    <% if(searchRes.isEmpty()){%>
                    <h1>NO RESULTS</h1>
                    <%}else {
                        for(News n: searchRes){%>
                     <h1><a href="SearchServlet?title=<%=n.getTitle()%>&author=<%=n.getAuthor()%>"><%=n.getTitle()%></a><br></h1>
                     <%=n.getDescription()%><br>
                     <span style="color:red">Author: <%=n.getAuthor()%>&nbsp;&nbsp;&nbsp;<%=n.getDate()%></span><br><p>
                    <% }
                    } %>
                </td>
            </tr>
        </table>


    </body>
</html>

