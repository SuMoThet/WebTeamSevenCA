<%-- 
    Document   : LogIn
    Created on : Jan 21, 2016, 3:15:11 PM
    Author     : sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogIn</title>
             <meta charset="UTF-8">
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
      <body>
<h1 align="center">Please Login</h1>
<form action="logIn" method="post">

	    
     <div class="wrapper">
            
                <center>
                   
              
                  
                    
                        <table class="tableStyle1" id="showData" align="center">
                            <tr>
                                <td colspan="2" align="center" style="padding-top:30px; padding-bottom: 20px;"><h4><b>Log In</b></h4></td>
                            </tr>
		
    
    
    
	
		<tr>
			<td width="35%">User Name:</td>
			<td><input type="text" name="username" size=60 maxlength=80>
			</td>
		</tr>
		<br/>
		<br/>
		<tr>
			<td width="35%">Password:</td>
			<td><input type="password" name="password" size=60 maxlength=80>
			</td>
		</tr>
                
                <tr>
			<td width="35%">Role:</td>
			<td class="image-radio">
        <input name="photo" style="display:none" type="radio" value="2.jpg"/>
        <img src="http://localhost:8080/uno/Images/cards/2.jpg"/>
			</td>
                        <td class="image-radio">
        <input name="photo" style="display:none" type="radio"value="3.jpg"/>
        <img src="http://localhost:8080/uno/Images/cards/3.jpg"/>
			</td>
                             <td class="image-radio">
        <input name="photo" style="display:none" type="radio"value="4.jpg"/>
        <img src="http://localhost:8080/uno/Images/cards/4.jpg"/>
			</td>
                             <td class="image-radio">
        <input name="photo" style="display:none" type="radio"value="5.jpg"/>
        <img src="http://localhost:8080/uno/Images/cards/5.jpg"/>
			</td>
		</tr>
                
                
		<br/>
                <br/><td></td><td></td><td></td>
		<tr>
			<td colspan="2" style="padding-bottom: 30px;"> 
			<input type="submit"  value="SUBMIT"/>
                       <input type="reset" value="RESET"/>
				
			</td>
		</tr>
                <tr></tr>
	<br />	
	<br />
	<br />
	<p><font color="#FF0000">${requestScope.loginMessage}</font></p>
        </table>
                   
                    <div id="DropArea">
                        
                    </div>
               
                </center>
            
                     </div>
</form>
</body>
