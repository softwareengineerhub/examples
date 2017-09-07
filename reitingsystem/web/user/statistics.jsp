<%-- 
    Document   : statistics
    Created on : 14.12.2011, 23:05:39
    Author     : Rolan
--%>


<%-- TODO: denis: непонятно о каком сайте отображается статистика. нужно добавить описание сайта (можно взять пример из поиска или моих сайтов).--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page  import="com.conarhco.rating.*,java.util.*,java.text.*" %>
<c:set var="title" value="статистика"/>
<%@include file="../WEB-INF/jspf/user.jspf" %>

<%DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = Calendar.getInstance();
%>
<style>
    .td2{
        width: 90px;
        text-align: center;
        border-left-style: none;
    }
    .td1{width: 210px;}
</style>
<table width="1024px"  align="center"  cellpadding="0" cellspacing="10" >
    <tr>
        <td align="center" valign="top">
            <table width="570px">
                <tr>
                    <td style="font-weight:bold;text-align:left;">
                            	Основные показатели рекоммендаций
                    </td>
                </tr>
                <tr>
                    <td>
                        <table bgcolor="EEEEEE" style="border-color: black;border-width: 2px;border-style: solid">
                            <tr>
                                <td colspan="2" class="td1"><%=format.format(new Date())%></td>
                                <c:choose>
                                    <c:when test="${param.period==4}">
                                        <td bgcolor="silver" class="td2" ><a href="statistics.jsp">по дням</a></td>
                                        <td class="td2">по неделям</td>
                                        <td bgcolor="silver" class="td2"><a href="statistics.jsp?period=12">по месяцам</a></td>
                                    </c:when>
                                    <c:when test="${param.period==12}">
                                        <td bgcolor="silver" class="td2" ><a href="statistics.jsp">по дням</a></td>
                                        <td bgcolor="silver" class="td2" ><a href="statistics.jsp?period=4">по неделям</a></td>
                                        <td class="td2">по месяцам</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="td2" >по дням</td>
                                        <td bgcolor="silver" class="td2"><a href="statistics.jsp?period=4">по неделям</a></td>
                                        <td bgcolor="silver" class="td2"><a href="statistics.jsp?period=12">по месяцам</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr bgcolor="silver">
                                <td  class="td1"></td>

                                <c:choose>
                                    <c:when test="${param.period==4}">
                                        <td  class="td2" valign="bottom">текущая неделя</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.WEEK_OF_MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>прошлая неделя</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.WEEK_OF_MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>позапрошлая неделя</td>
                                        </c:when>
                                        <c:when test="${param.period==12}">
                                        <td  class="td2" valign="bottom">текущий месяц</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>прошлый месяц</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>позапрошлый месяц</td>
                                        </c:when>
                                        <c:otherwise>
                                        <td  class="td2" valign="bottom">сегодня</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.DAY_OF_MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>вчера</td>
                                        <td  class="td2" valign="bottom">
                                            <%calendar.add(Calendar.DAY_OF_MONTH, -1);%>
                                            <%=format.format(calendar.getTime())%>
                                            <br>позавчера</td>
                                        </c:otherwise>
                                    </c:choose>
                                <td  class="td2" valign="bottom">всего</td>
                            </tr>
                            <tr id="totalLine"></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="font-weight:bold; padding-top: 20px;text-align:left;">
                            	Критерии рекоммендаций
                    </td>
                </tr>
                <tr>
                    <td>
                        <!--img src="images/cabinet_table_2.png"-->
                        <table style="border-color: black;border-width: 2px;border-style: solid">
                            <%int today = 0;
                                        int yesterday = 0;
                                        int beforeyesterday = 0;
                                        int total = 0;
                                        int sid=Integer.parseInt(request.getParameter("sid"));
                                        for (String s : DataModule.getInstance().getCounterNamesForSite(sid)) {
                                            StatisticsPeriod period = StatisticsPeriod.DAY;
                                            if (request.getParameter("period") != null) {
                                                if (Integer.parseInt(request.getParameter("period")) == 4) {
                                                    period = StatisticsPeriod.WEEK;
                                                }
                                                if (Integer.parseInt(request.getParameter("period")) == 12) {
                                                    period = StatisticsPeriod.MONTH;
                                                }
                                            }
                                            StatisticsAmountBean sb = DataModule.getInstance().getCountByDay(1, period, s);
                            %>

                            <tr style="background-color: white;width: 570px" >
                                <td class="td1"><%=s%></td>
                                <td class="td2"><%=sb.getToday()%></td>
                                <td class="td2"><%=sb.getYesterday()%></td>
                                <td class="td2"><%=sb.getBeforYesterday()%></td>
                                <td class="td2"><%=sb.getTotal()%></td>
                            </tr>
                            <%
                                            today += sb.getToday();
                                            yesterday += sb.getYesterday();
                                            beforeyesterday += sb.getBeforYesterday();
                                            total += sb.getTotal();
                                        }%>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="padding-top: 30px;">
                        <c:choose>
                            <c:when test="${param.period==4}">
                                <c:set var="drawPeriod" value="4"/>
                            </c:when>
                            <c:when test="${param.period==12}">
                                <c:set var="drawPeriod" value="12"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="drawPeriod" value="30"/>
                            </c:otherwise>
                        </c:choose>
                        <img src='drawServlet?sid=<c:out value="${param.sid}"/>&drawPeriod=<c:out value="${drawPeriod}"/>'/>
                    </td>
                </tr>
            </table>
        </td>

    </tr>
</table>
<script type="text/javascript">
    $("#totalLine").append(" <td  class='td1'>Рекомендации</td><td  class='td2'> <%=today%></td><td class='td2'><%=yesterday%></td><td class='td2'><%=beforeyesterday%></td><td class='td2'><%=total%></td>");
</script>

<%@include file="../WEB-INF/jspf/user_footer.jspf" %>