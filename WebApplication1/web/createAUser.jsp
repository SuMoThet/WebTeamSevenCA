<%-- 
    Document   : createAUser
    Created on : Jan 22, 2016, 9:43:20 AM
    Author     : sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="Styles/index.css">
        <link rel="stylesheet" href="Styles/bootstrap.css">
        <link href="Styles/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        
        <script src="JS/jquery-1.11.1.js" type="text/javascript"></script>
        <script src="JS/jquery.js" type="text/javascript"></script>
        <script src="JS/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/jquery-ui.min.js" type="text/javascript"></script>
        <script src="JS/jquery-ui.js" type="text/javascript"></script>
        <script src="JS/Login.js" type="text/javascript"></script>
        <script src="JS/handlebars-v1.3.0.js" type="text/javascript"></script>
        
    </head>
    <body>
       <h1 align="center">Create A New User</h1>
<form action="createAUser" method="post">

    <div class="wrapper">
            
                <center>
                   
              
                  
                    
                        <table class="tableStyle1" id="showData" align="center">
                            <tr>
                                <td colspan="2" align="center" style="padding-top:30px; padding-bottom: 20px;"><h4><b>REGISTRATION</b></h4></td>
                            </tr>
    
    
    
    <tr>
			<td width="25%">User Name:</td>
			<td><input type="text" name="newname" size=25 maxlength=30>
			</td>
		</tr>
                
                
		<br/>
		<br/>
		<tr>
			<td width="25%">Password:</td>
			<td><input type="password" name="newpassword" size=25 maxlength=30>
			</td>
		</tr>
		<br/>
		<br/>
		<tr>
                    <td colspan="2" style="padding-bottom: 30px;">
			
			<input type="submit" value="Create"/>&nbsp;
				<input type="reset" value="RESET"/>
			</td>
		</tr>
	<br />	
	<br />
	<br />
	<p><font color="#FF0000">${requestScope.createMessage}</font></p>
            </table>
                   
                    <div id="DropArea">
                        
                    </div>
               
                </center>
            
                     </div>
</form>
</body>
