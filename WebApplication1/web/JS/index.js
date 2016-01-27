$(function(){
   $("#btnRegister").on("click",function(){
        var url = "createAUser.jsp";    
        $(location).attr('href',url);
   });
   $("#btnCreateGame").on("click",function(){
        var url = "createAGame.jsp";
        $(location).attr('href',url); 
//        $.getJson("JoinGame",function(){
//        }).done(function(result){
//            console.log(result);
    });
     $("#btnJoinGame").on("click",function(){
        var url = "LogIn.jsp";    
        $(location).attr('href',url);
   });
  
  $("#btnCreate").on("click",function(){
        var url = "StartGame.html";    
        $(location).attr('href',url);
   });
   $("#btnExit").on("click",function(){
       
     //   window.open('','_parent',''); 
        window.close();
   });
});