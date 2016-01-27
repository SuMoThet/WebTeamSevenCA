var UNOApp = { };

$(function(){
    $("#btnStart").on("click",function(){
        $.getJSON("StartNewGame",function(){
        }).done(function(result){
            UNOApp.gameID = result.UNOID;
            UNOApp.pCount = document.getElementById("noOfPlayers").value;
            UNOApp.pStarter = document.getElementById("starterid").value;//$("#starterid").val();
            console.log(UNOApp);
            $("#TableDiv").empty().append($("<center><h2>").text("Game ID = " + UNOApp.gameID))
                    .append($("<center><h1>").text("Waiting for another players...."));
            
            UNOApp.event = new EventSource("api/play/"+ UNOApp.gameID +"?playerName=UNOtable" 
                    + "&pStarter=" + UNOApp.pStarter + "&pCount=" + UNOApp.pCount);
            $(UNOApp.event).on(UNOApp.gameID, handleUNO);
            
    });
   }) ;
});

function handleUNO(evt) {
    
    var UNOMsg = JSON.parse(evt.originalEvent.data); 
    
    console.log("UNO First card : " + UNOMsg.first_card);
    
     switch (UNOMsg.cmd) {
        case "start_game":
            console.log("UNO First card : " + UNOMsg.first_card);
            $("#TableDiv").empty().append("<button><img src='Images/cards/" + UNOMsg.first_card +"' alt='UNO'></button>")
                .append($("<center><h5>").text("UNO Card"));
                console.log("UNO Card for Common View");
                
            $("#TableDiv button").on("dblclick",function(){
                console.log("Common Page Button Double Click");
            });
            break;
            
        case "playGame_DropCard" :
            UNOApp.drop_card = UNOMsg.DropCard;
            console.log("Drop a card" + UNOApp.drop_card);
            $("#TableDiv").empty().append("<button><img src='Images/cards/" + UNOApp.drop_card +"' alt='UNO'></button>")
                .append($("<center><h5>").text("UNO Card"));
                console.log("UNO Card for Common View");
            
            break;
     }
}