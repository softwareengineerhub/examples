<%@include file="WEB-INF/jspf/headerFROL.jspf" %>


<form name="mainform" method="POST" action="j_security_check" style="width: 450px; position: relative; background-color: #e8def3;">

    <table style="margin: 20px;">
        <tr>
            <td>L</td>
            <td><input type="text" name="j_username" style="width: 100px;"/></td>
        </tr>
        <tr>
            <td>P</td>
            <td><input type="password" name="j_password" style="width: 100px;"/></td>
        </tr>
        <tr>
            <td>P</td>
            <td><input type="submit"/></td>
        </tr>

        <tr>
            <td align="left"><a href="#" class="lrn" onclick="document.mainform.submit()">Login</a></td>
            <td align="right"><a href="reg.jsp" class="lrn">Registration</a></td>
        </tr>
    </table>
</form>

<%@include file="WEB-INF/jspf/footer.jspf"%>