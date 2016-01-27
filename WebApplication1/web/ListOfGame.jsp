<%-- 
    Document   : ListOfGame
    Created on : Jan 19, 2016, 7:40:44 PM
    Author     : Little Rabit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
  #title{
    text-align: center;
     color:black;
     font-family:fantasy;
     font-weight: 900;
}
body{
                 font-family:Verdana;
                 font-size:18px;
                 margin:0 auto;
             }
        #container{
             margin:0 auto;
             width:1060px;
             }
   .button1{
  border:1px solid #a8c1d5; -webkit-border-radius: 3px; -moz-border-radius: 3px;border-radius: 3px;font-size:12px;font-family:arial, helvetica, sans-serif; padding: 10px 10px 10px 10px; text-decoration:none; display:inline-block;text-shadow: -1px -1px 0 rgba(0,0,0,0.3);font-weight:bold; color: #FFFFFF;
 background-color: #CEDCE7; background-image: -webkit-gradient(linear, left top, left bottom, from(#CEDCE7), to(#596a72));
 background-image: -webkit-linear-gradient(top, #CEDCE7, #596a72);
 background-image: -moz-linear-gradient(top, #CEDCE7, #596a72);
 background-image: -ms-linear-gradient(top, #CEDCE7, #596a72);
 background-image: -o-linear-gradient(top, #CEDCE7, #596a72);
 background-image: linear-gradient(to bottom, #CEDCE7, #596a72);filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#CEDCE7, endColorstr=#596a72);
}
.noOfplayer{
     
     color:greenyellow;
     font-size: 30px;
     font-family: cursive;
     font-weight: 600;   
}
</style>
    </head>
    <body background=./Images/Background.jpg>
        <div id="container">
  
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <h1 id="title">Game List</h1>
        
        <table>
         <c:forEach items="${requestScope.gameList}"var="Game">
             <c:choose>
                 <c:when test="${Game.number != Game.joinedPlayers}">
                      <hr> 
             <tr> <form action="createAGame/JoinAGame" method="post">
                   <td></td>
                 <td height="30px">GameId: <input type="text" name="gameId" value="${Game.gameId}" ></input>Description:<input type="text" name="description"  value="${Game.description}"> </input></td>
           <td>   <input type="submit"  value="select" class="button1"/></td>
           <td><input type="hidden" name="userName" value="${requestScope.userName}" /></td>
           <td><input type="hidden" name="photo" value="${requestScope.photo}" /></td> 
         <tr>  <lable class="noOfplayer"> Number of Players: ${Game.number}</lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <lable class="noOfplayer"> Number of Players Joint:  ${Game.joinedPlayers}</lable>
		</tr>
	
        </form>
                </tr> 
                        </hr>
                 </c:when>
                 <c:otherwise>
                  
                       </c:otherwise>      
             </c:choose>
                    </c:forEach>
        </table>
        </div>
    </body>
</html>
