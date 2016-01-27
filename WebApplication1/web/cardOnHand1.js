
$(function() {
   
    //var gameId=$("body").attr("data-gameId");
     var gameId;
     var photo;
      var playerName;
    var mess=window.location.hash.substr(1);
      var term=mess.split(',');
      gameId=term[0];
       photo=term[1];
       playerName=term[2];
    //  var i;
     /// for(i=0;i<3;i++){
        //  if(i===0)
         // gameId=term[];
     // if(i===1)
      //    photo=term[];
      //if(i===2)
      //    playerName=term;
    //  }
    //var gameId = window.location.hash.substr(1,8);
       console.log("jquery card on hand"+gameId);
   // var photo = window.location.hash.substr(9,13);
    console.log("jquery card on hand"+photo);
  //  var playerName=window.location.hash.substr(14);
    console.log("jquery card on hand player"+playerName);
    console.log(">> " + gameId);
   // var playerName=$("div").attr("data-userName");
   // $("#gaa").text(payerName);
   
	var socket  = new WebSocket("ws://localhost:8080/uno/endPoint");
	socket.onopen = function() {
		console.log("player-onOpen");
             
                var msg = {
                      cmd:"joinAGame",
			gameId: gameId,
                        name: playerName  , 
                         photo:photo
		}
		socket.send(JSON.stringify(msg));
	}
         $('div').on('click', 'img', function(){
               var dc=$(this).attr("alt");
               console.log(dc);

                var msg2 = {
                      cmd:"discardACard",
			gameId: gameId,
                      name: playerName,
                      discardcard: dc
		}
                socket.send(JSON.stringify(msg2));
                 this.style.display='none';
             });
	socket.onmessage = function(evt) {
           
            
		var msg1 = JSON.parse(evt.data);
                var flag1=msg1.cmd;
               var cardname;
                 if(flag1=="PlayerGetCard"){
                     $('#c >img').remove();
                      $('#d >#unoButton').remove();
                      $("#ph > img").remove();
                   console.log("player-receivemessage");
                var cardOnHand=msg1.cardOnHand;
                var user=msg1.user;
                $("#user").text(user);
                var ph1=msg1.photo;
                 var img;
                img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/"+ph1);
                    img.attr('alt',ph1);
                    img.appendTo( $("#ph"));
               
                var unoButton;
                $("#mes").text("");
                $.each(cardOnHand,function(){
                    cardname= this.name;
                    console.log("every card on hand "+cardname);
                  // $('<div>').text(cardname).appendTo("#c");
                  //$("div").append('<img id="card" src="http://localhost:8080/WebApplication1/Images/cards/"+cardname>');
                  img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/"+cardname);
                    img.attr('alt',cardname);
                    
                    img.appendTo($("#c"));});
                   unoButton= $('<input/>');
                   unoButton.attr('type','button');
                   unoButton.attr('id',"unoButton");
                   unoButton.attr('disabled',"disabled");
                   unoButton.attr('value',"UNO");
                   unoButton.appendTo($('#d'));
                    $("#unoButton").click(function(){
            console.log("unoButton CLICK");
             var msg2 = {
                      cmd:"IwantSayUno",
			gameId: gameId,
                      pname: playerName
                      
		}
                socket.send(JSON.stringify(msg2));
        });
                 }
                 if(flag1=="giveACardForPlayer"){
                    cardname=msg1.cardforplayer;
                    console.log("player-draw which card"+cardname);
                    var uno=msg1.unoButton;
                     img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/"+cardname);
                    img.attr('alt',cardname);
                    img.appendTo($("#c"));
                     $("#d #unoButton").prop('disabled',uno);
                     console.log("drawACard shide unoButton"+uno);
                }
                if(flag1=="CanSayUNO"){
                    $('#d #unoButton').prop('disabled',false);
                }
	};
     //   $("#unoButton").click(function(){
         //   console.log("unoButton CLICK");
          //   var msg2 = {
                  //    cmd:"IwantSayUno",
		//	gameId: gameId,
                 //     pname: playerName
            //          
	//	}
           //     socket.send(JSON.stringify(msg2));
    //    });
        
        
       });


