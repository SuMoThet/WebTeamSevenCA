
$(function() {
   // $('#continue').style.display='none';
     var img;
      var t;
       var which;
       var who;
          var count;
    // var gameId=location.hash.substring(1);
     var gameId = window.location.hash.substr(1);
     console.log(gameId);
     $('#gameId1').val(gameId);
   // var gameId=$("body").attr("data-gameId");
	var socket  = new WebSocket("ws://localhost:8080/uno/endPoint");
	socket.onopen = function() {
		console.log("table-onopen");
             
                var msg = {
                       cmd:"initTable",
			gameId: gameId
                        
		}
		socket.send(JSON.stringify(msg));
	}
	socket.onmessage = function(evt) {
            console.log("table-receivemessage"); 
		var msg1 = JSON.parse(evt.data);
                var cmd=msg1.cmd;
                console.log("cmd"+cmd);
                if(cmd=="openACard"){
                      console.log("tableOpenACard");
                      count=msg1.count;
                var c=msg1.cardName;
                var players=msg1.players;
             $.each(players,function(){
                   var player= this.playername;
                   var ph=this.photo;
                  // $('<div>').text(cardname).appendTo("#c");
                  //$("div").append('<img id="card" src="http://localhost:8080/WebApplication1/Images/cards/"+cardname>');
                 var d=$('<div>');
                 d.addClass("droppable");
                  d.appendTo("#ta");
                // td.addClass("name");
                 img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/"+ph);
                    img.attr('alt',player);
                    img.addClass("p");
                    d.attr('id',player);
                   //  img.addClass("droppable");
                   // img.appendTo($("#players"));  
                    img.appendTo(d); 
                    var br=$('<br/>');
                    br.appendTo(d);
                    t=$('<label>');
                 t.text(player);
                  t.appendTo(d); 
                  
             });
                 img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/"+c);
                    img.attr('id',c);
                    img.appendTo("#discardPlace");
                    $("#drawPilePlace").text(count);
                    img=$('<img>');
                  img.attr('src',"http://localhost:8080/uno/Images/cards/back.png");
                    img.attr('id',"drawPileCard");
                     img.appendTo($("#drawPilePlace"));
                }
                if(cmd=="addADiscardCard"){
                      console.log("tablereceiveacard");
                    which= msg1.cardName;
                   console.log("tablereceiveacard"+which);
                    who=msg1.who;
                    $("#discardPlace  img").attr('src',"http://localhost:8080/uno/Images/cards/"+which);
                    $("#discardPlace  img").attr('alt',which);
                      $("#returnButton").prop('disabled',false);
	}
          if(cmd=="gameOver") {
                 console.log("gameOver");
             var ca=msg1.cardName;
             var grade=msg1.grade;
             var result=[];
             $("#discardPlace  img").attr('src',"http://localhost:8080/uno/Images/cards/"+ca);
                    $("#discardPlace  img").attr('alt',which);
                      $("#returnButton").prop('disabled',false);
                     
                  
                       $.each(grade,function(i){
                            var na=this.playername;
                           var gr=this.value;
                           var r=(i+1)+".      "+na+":      "+gr;
                           result.push(r);
                         //  points.push((na,gr));
                          // if(gr>=rank){
                            //   rank=gr;
                             console.log("grade rank "+(i+1)+na+gr);
                          // }
                           
                         
                       });
                      $.each(result,function(i){
                          var li=$('<li>');
                          var lable=$('<lable>');
                          if(i==0){ lable.text("Winner:                         "+this);}
                          else
                          {lable.text(this);}
                          lable.appendTo(li);
                          li.appendTo("#result");
                      });
                      $("#result").dialog({
                          autoOpen:true,
                            width:800,
                            height:600,
                             resizable: false,
                              modal: true,
                               buttons: {
                           "Start A New Round": function() {
                              $( this ).dialog( "close" );
                            $( "#continue" ).trigger( "click" );
                                },
                           Cancel: function() {
                         $( this ).dialog( "close" );
                             }
                                 }
                           });
                       
        }
        if(cmd=== "someOneSayUno")  {
          var name=msg1.who;
         // $("#dialog").dialog('open');
         console.log("SAYUNO"+name);
          console.log(name+"say UNO!!!!!!!!!!!");
         $("#say").dialog({
              autoOpen:true,
                width:600,
                 height:400,
              open: function(event,ui){
                  $("#name").text(name+"  say  UNO !!!");
                  setTimeout("$('#say').dialog('close')",3000);
              }
          })
      }
         $( "#draggable").draggable({      
          helper : "clone",
});
if(cmd== "giveACardForPlayer"){
    var lastcard=msg1.lastCard;
     $("#discardPlace  img").attr('src',"http://localhost:8080/uno/Images/cards/"+lastcard);
    
}
$('.droppable').droppable({
      //accept:"#draggable",
       drop: function(event,ui){
            var dc=$(this).attr("id");
           console.log(dc);
            alert("Draw A Card For"+dc+"?");
           var co= $("#drawPilePlace").text();
           $("#drawPilePlace").text(co-1);
           var msg2 = {
                   cmd:"drawACardforPlayer",
			gameId: gameId,
                     playername: dc
                    
		}
              socket.send(JSON.stringify(msg2));
      
            }
        });
      
       
    };
     $("#returnButton").click(function(){
          var msg2 = {
                   cmd:"returnACardToPlayer",
			gameId: gameId,
                     playername: who,
                      cardName:which
		}
                console.log("return what to who"+who+which);
              socket.send(JSON.stringify(msg2));
             $("#returnButton").prop('disabled',true);
     });
      $("#continue").click(function(){
          var msg2 = {
                   cmd:"restartAGame",
			gameId: gameId
		}
                
              socket.send(JSON.stringify(msg2));
              
      
     });
 //   $( "#draggable").draggable({      
     //     helper : "clone",
//});
 //$("#droppable" ).droppable({
   //     drop:function(event,ui){
    //        alert("has drop!--from ifxoxo.com");
    //  }
      
//});
//$('.droppable').droppable({
      //accept:"#draggable",
     //  drop: function(event,ui){
        //    var dc=$(this).attr("id");
        //   console.log(dc);
         //   alert("has drop!--from ifxoxo.com");
         //  var msg2 = {
               //    cmd:"drawACardforPlayer",
		//	gameId: gameId,
                   //  playername: dc
	//	}
           //   socket.send(JSON.stringify(msg2));
      
          //  }
      //  });
      
$("#say").dialog({
          autoOpen:false,
           width:400,
          height:300
          
      });
      $("#result").dialog({
          autoOpen:false,
          width:600,
          height:400
          
      });
});


 


