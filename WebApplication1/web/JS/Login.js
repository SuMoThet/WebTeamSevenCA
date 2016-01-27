var login = {};
var dataCollection;
$(function(){
    $(".image-radio img").click(function(){
     $(this).prev().attr('checked',true);
 })
    $("#btnLogin").on("click",function(){
        login.username = document.getElementById("username").value;
        login.password = document.getElementById("password").value;
        
        var LogInpromise = $.getJSON("Login",{userName:login.username, passWord:login.password} , function(){
            
        }).done(function(LogInresult){
            console.log(LogInresult);
            if(login.username === "" || login.password === ""){
            alert("Username and Password cannot be blank!");
            console.log("ERROR");
            }
            else if(LogInresult.result === "AUTHENTICATION FAILED"){
                alert("LOGIN AUTHENTICATION FAILED!");
                console.log("LOGIN AUTHENTICATION FAILED!");
            }
            else if(LogInresult.result === "AUTHENTICATION SUCCESSED"){
                login.userid = LogInresult.username;
                var promise = $.getJSON("JoinGame",function(){
                    console.log("enter Jquery");
                }).done(function(result){
                    console.log(result);
                    dataCollection = result;
                    showResult();
                });
            }
        });
    });
});

function showResult()
{
    var template = "{{#each item}} <tr><td>{{this.sessionID}}</td><td>{{this.starter}}</td><td><button name=\"btnJoin\" id=\"btnJoin\" value={{this.sessionID}} type=\"button\" class=\"btn btn-xs\">JOIN</button></td><tr> {{/each}}";
    var generateData = Handlebars.compile(template);
    $("#showData tr").remove();
    $("#showData").append("<tr style='text-align:center;'><td style='text-align:left;'> <u>GAME ID</u> </td><td colspan='2' style='text-align:left;'> <u>STARTER NAME</u> </td></tr>");
    $("#showData").append(generateData({item:dataCollection}));
    $("#showData button").on("click",function(){
        var gameid = $(this).prop("value");
        login.gameid = gameid;
        login.event = new EventSource("api/PlayerPlay/?playerName=" + login.userid
                + "&gameID=" + login.gameid);
        console.log("------->> EVENT DONE <<------");
        
        $(login.event).on(login.gameid, handleGAME);

        console.log("------->> Individual Page <<------");
        $("#showData").empty().append($("<center><h2>").text("Game ID : " + login.gameid + ", " + login.userid + " is waiting for other players...."));
    });
}

/*
 * { cmd: "start_game", cards: [ 2, 3, 4, 5, 6, 7, 8 ]}
 */
function handleGAME(evt) {
    
    console.log("---->> Inside the handleGAME <<-----");
    var result = JSON.parse(evt.originalEvent.data);
    login.userid = result.playerName;
    login.gameid = result.gameId;
    switch (result.cmd) {
        case "start_game":
//When Game is Started >> 7 Cards to each Player
                $("#TableDiv").empty();                
                var template = "<img src = 'Images/cards/{{card}}' class='draggable' id='{{card}}' value='{{card}}' data-cardID='{{card}}' style='display:inline-block; position: absolute;'>";
                var generateData = Handlebars.compile(template);

                for(var i in result.CardList)
                {
                    var style = $(generateData({card:result.CardList[i].Cards}));
                    style.css({left:(300+(i*70))+"px"});
                    $("#TableDiv").append(style);
                    $("#TableDiv").css("{height:600px}");
                }
//Drop Area and Draw Pile Button                
                $("#DropArea").append("<button id='btnDrawPile' value='"+login.userid+"'><img src='Images/cards/back.png' id='draggable'></button>");
                $("#DropArea").append("<div class='DropBox' id='droppable'><h4>Drop Here</h4></div>");
                $("#DropArea").css({"margin-top":"300px"});
                
//Click on DrawPile >>>  
                $("#DropArea button").on("click",function(){
                    $.post("PlayServlet", {
                        gameID: login.gameid,
                        playerName: login.userid
                    }).done(function() {
                        console.log("I did it.");
                        //$("#message").val("");                   
                    });
                });
//Drag and Drop Part                
                $( ".draggable" ).draggable({revert:true});
                $( "#droppable" ).droppable({
                    drop: function( event, ui ) {
                        var image = ui.draggable;
                        image.remove();
                        $( this ).find( "h4" ).html( "Dropped!" );
                        login.dropID = ui.draggable.attr("id");
                        console.log(login.dropID);
                        
                        $.get("PlayServlet", {
                        gameID: login.gameid,
                        playerName: login.userid,
                        dropCard: login.dropID
                    },function(){
                        console.log("Starting PlayServlet");
                    }).done(function() {
                        
                    });
                    }
                });
            break;
            
        case "playGame_DrawCard":
            console.log(" Inside a Event Funtion : Draw A Card");
            $("#TableDiv").empty();                
                var template = "<img src = 'Images/cards/{{card}}' class='draggable' id='{{card}}' value='{{card}}' data-cardID='{{card}}' style='display:inline-block; position: absolute;'>";
                var generateData = Handlebars.compile(template);

                for(var i in result.CardList)
                {
                    
                    var style = $(generateData({card:result.CardList[i].Cards}));
                    style.css({left:(300+(i*70))+"px"});
                    $("#TableDiv").append(style);
                    $("#TableDiv").css("{height:600px}");
                }

//Drag and Drop Part                
                $( ".draggable" ).draggable({revert:true});
                $( "#droppable" ).droppable({
                    drop: function( event, ui ) {
                        var image = ui.draggable;
                        image.remove();
                        $( this ).find( "h4" ).html( "Dropped!" );
                        var dragID = ui.draggable.attr("id");
                        console.log(dragID);
                    }
                });
            break;
    }
}
