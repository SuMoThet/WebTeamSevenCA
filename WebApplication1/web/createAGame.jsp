<%-- 
    Document   : createAGame
    Created on : Jan 18, 2016, 7:18:32 PM
    Author     : Little Rabit
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
         <script> function validateForm() {
    var x = document.forms["create"]["description"].value;
    var y=document.forms["create"]["number"].value;
    if (x == null || x == ""||y == null ||y == "") {
        alert("Please Fill All Field");
        return false;
    }
}</script>
        
    </head>
    <body>
        <h1> create a game</h1>
        <form  name="create" onsubmit="return validateForm()"  action="createAGame/create" method="post">
           <div class="wrapper">
            
                <center>
              
                    
                        <table class="tableStyle1" id="showData" align="center">
                            <tr>
                                <td colspan="2" align="center" style="padding-top:30px; padding-bottom: 20px;"><h3><b>Create Game</b></h3></td>
                            </tr>
            
            
            <tr>
            <td>description:</td>
			<td><input id="description" type="text" name="description" size=25 maxlength=30>
			</td>
            </tr>
            
            <tr>
                <td> number of players:</td>
			<td><input id="num" type="text" name="number" size=25 maxlength=30>
			</td>
            </tr>
            <tr><td colspan="2" style="padding-bottom: 30px;">
                        <input type="submit"  value="creat a game"/>
                        </td>
		</tr>
                
                
                    <div id="DropArea">
                        
                    </div>
               
                </center>
            
                     </div>
        </form>
    </body>
</html>
