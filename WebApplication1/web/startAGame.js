/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    var gameId=window.location.hash.substr(1,8);
    console.log("startAGame js"+gameId);
   $("#game").val(gameId);
    var number=window.location.hash.substr(9);
   
   // var gameId=$("body").attr("data-gameId");
    //var number=$("h1").attr("data-number");
	var socket  = new WebSocket("ws://localhost:8080/uno/endPoint");
	socket.onopen = function() {
		console.log("startgame-onopen");
             
                var msg = {
                       cmd:"startAGame",
			gameId: gameId,
                        number:number
		}
		socket.send(JSON.stringify(msg));
	}
	socket.onmessage = function(evt) {
            console.log("allPlayerJoin666666666666666");
		//var flag = evt.data;
                var flag = JSON.parse(evt.data);
                var s=flag.symbol;
                $("#enableButton").prop('disabled',s);
                //$("#discardPlace").append('<img id="discardCard" src="http://localhost:8080/WebApplication1/Images/cards/"+msg1.cardName>');
               // $("#drawPilePlace").text(msg1.count);
              //  $("#drawPilePlace").append('<img id="drawPileCard" src="http://localhost:8080/WebApplication1/Images/cards/back.png">');
	}
    }
    );
    

