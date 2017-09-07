<%-- 
    Document   : register
    Created on : 21.12.2011, 10:18:24
    Author     : dprokopiuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Рейтинг сайтов: регистрация</title>
        <link href="images/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
        <style>
            .error_input{
              border-color: red;
             }
            
        </style>
    </head>
    <body style="background-image:url(images/background.png); background-color:#cae2e2; background-repeat:repeat-x;">
    	<table border=0 align="center" width="100%" height="100%" cellpadding="0" cellspacing="0" style="position:absolute; left:0px; top:0px;"><!--main table-->
        <tr>
        <td width="100%" background="images/header_filler.png">
        	<table width="1024px"  height="44px" align="center" border="0" cellpadding="0" cellspacing="0" >
            	<tr>
                <td background="images/header.png" ><!-- header-->
                	<!--<div style="position:relative; left:310px; top:5px;">-->
                    <div style="position:relative; top:7px; left:310px; width:450px;">
                    </div>
                </td>
                <td>
                <a href="#" style="border:hidden;"><img src="images/lnk_add_site.png" border="0" style="position:relative; top:15px; left:30px;"></a>
                </td>
                <td>
                <a href="#" style="border:hidden;"><img src="images/lnk_authorize.png" border="0" style="position:relative; top:15px; left:0px;"></a>
                </td>
                </tr>
            </table>
		</td>
        </tr>
        <tr>
        <td width="100%">
	        <table width="1024" align="center" border="0" cellpadding="0" cellspacing="0" >
            	<tr>
    				<td align="left" width="210">
	                    <img src="images/logo.png" style="padding-left:30px;">
                    </td>
                    <td align="left" style="width:210px;">
                    	<a href="profile.html" onfocus="this.blur();"><img src="images/btn_profile_sel.png" border="0" style="position:relative; top:-27px; left:60px;"></a>
                        <a href="statistics.html" onfocus="this.blur();"><img src="images/btn_statistics_sel.png" border="0" style="position:relative; top:-27px; left:55px;"></a>
                        <a href="#" onfocus="this.blur();"><img src="images/btn_events_sel.png" border="0" style="position:relative; top:-27px; left:50px;"></a>
                        <a href="sites.html" onfocus="this.blur();"><img src="images/btn_my_sites_sel.png" border="0" style="position:relative; top:-27px; left:45px;"></a>
                    </td>
                </tr>
                <tr>
                	<td colspan="2">
						<img src="images/horizontal_delimeter_line_site_1.png" border="0" style="position:relative; top:-70px; left:129px" />
                     </td>
                </tr>
            </table>
        </td>
        </tr>
        <tr>
		<td>
        <!-- main part -->
       		<table width="1024px" align="center" border="0" cellpadding="0" cellspacing="10" >
            	<tr>
            		<td valign="top" align="left">
                            <form action="registr" method="post" id="form">
						<table border="0" style="position:relative;left:100px;" cellpadding="5">
                        	<tr>
                            	<td class="profile_header" align="left" colspan="2"  nowrap="nowrap">
                                	Данные для регистрации
                                        <div id="errorMessage" style="color: red">
                                            <%if(request.getParameter("errorRegister")!=null){%>
                                            Вы не зарегистрировались (такой логин уже существеут)
                                            <%}%>

                                        </div>
                                </td>
                               
                            </tr>
                            <tr>
                            	<td class="profile_text" align="left" nowrap="nowrap">
                                	логин
                                </td>
                                <td align="left">
                                    <input id="login" type="text" style="width:195px;" name="login" />
                                </td>
                            </tr>
                            <tr>
                            	<td class="profile_text" align="left" nowrap="nowrap">
                                	пароль
                                </td>
                                <td align="left">
                                	<input id="password" type="password"  style="width:195px;" name="password" />
                                </td>
                            </tr>
                            <tr>
                            	<td class="profile_text" align="left" nowrap="nowrap">
                                	подтверждение пароля
                                </td>
                                <td align="left">
                                	<input id="password1" type="password" style="width:195px;" />
                                </td>
                            </tr>
                            <tr>
                            	<td class="profile_text" align="left" width="100" nowrap="nowrap">
                                	e-mail
                                </td>
                                <td align="left">
                                	<input id="email" type="text" style="width:195px;" name="email" />
                                </td>
                            </tr>
                            <tr>
                            	<td align="right" colspan="2">
                                    <input type="image" src="images/btn_register.png" id="create-user"/>                                    
                                </td>
                            </tr>
                        </table>
                        </form>
                               
                    </td>
                </tr>
            </table>
        <!-- footer -->
       	</td>
        </tr>
        <tr>
        	<td height="100%">
            </td>
        </tr>
        <tr>
        <td width="100%" background="images/footer_filler.png">
        	<table width="1024"  height="44" align="center" border="0" cellpadding="0" cellspacing="0" >
            	<tr>
                <td background="images/footer.png" align="center" width="1024"></td>
                </tr>
            </table>
		</td>
        </tr>
        </table>
    </body>
    <script type="text/javascript" src="js/validate_registration.js"></script>
</html>

