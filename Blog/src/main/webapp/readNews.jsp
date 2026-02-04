<%-- 
    Document   : readNews
    Created on : 28.01.2011, 21:53:23
    Author     : RSDENIS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" www.DataModel,www.News,java.text.DateFormat" %>
<%@include file="WEB-INF/jspf/denisHeader.jspf"%>
<%! DateFormat format=DateFormat.getTimeInstance(DateFormat.MEDIUM);%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<%String title=session.getAttribute("title")+"";
    String author=session.getAttribute("author")+"";
            News n = DataModel.instance().getNews(title, author);
            
            %>
            <br>
            <br>
            <br>

            <center>
                <p>
                <H1><%=n.getTitle()%></H1><P>
                    <br><%=n.getText()%><br>
                    <br><span style="color:red">Author: <%=n.getAuthor()%>&nbsp;&nbsp;&nbsp;<%=format.format(n.getDate())%>&nbsp;&nbsp;&nbsp;Views:<%=n.getView()%></span>
            <form action=" " method="">
            <input type="submit" value="press if you like this article" size="30">
             </form>
            </center>
    </body>
</html>
