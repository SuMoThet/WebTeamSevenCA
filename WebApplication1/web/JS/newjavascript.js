var newjs = {};
$(function(){
    $("#BtnDropCard").on("click",function(){
        newjs.drawCard = "c3.png";
        newjs.playerid = "syh";
        newjs.event = new EventSource("api/NewClassEvent/?CardName=" + newjs.drawCard + "&playerid=" + newjs.playerid);
        
        $(newjs.event).on(login.gameid, handleGAME);
    });
});