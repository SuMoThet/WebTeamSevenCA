/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import uno.Card;
import uno.GameList;
import uno.player;

/**
 *
 * @author Little Rabit
 */
@RequestScoped
@ServerEndpoint("/endPoint")
public class gameSocket {

    @Inject GameList gameList;
    private Session session;
      
    @OnMessage
    public String onMessage(String msg) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         int number=0;
         String a=null;
        System.out.println("OnMessage");
        JsonReader reader = Json.createReader(
                new ByteArrayInputStream(msg.getBytes()));
        JsonObject json = reader.readObject();
        //System.out.println(">>>gameId = " + json.getString("gameId"));
        //System.out.println(">>> cmd = " + json.getString("cmd"));
        if(json.getString("cmd").equals("startAGame")){
           
            gameList.getAGame(json.getString("gameId")).setSession(session);
            System.out.println("aaaaaaaaaaaaaa"+Integer.parseInt(json.getString("number")));
           gameList.getAGame(json.getString("gameId")).setNumber(Integer.parseInt(json.getString("number")));
           
           
        }
        if (json.getString("cmd").equals("joinAGame")) {
             number= gameList.getAGame(json.getString("gameId")).getNumber();
             String photo=json.getString("photo");
            player p = new player(json.getString("name"));
            p.setPhoto(photo);
            p.setSession(session);
            gameList.getAGame(json.getString("gameId")).getPlayers().add(p);
            System.out.println("join a player successfully"+gameList.getAGame(json.getString("gameId")).getNumber());
           
                try {
                     if( gameList.getAGame(json.getString("gameId")).getPlayers().size()==number)
                     {     a = builder.add("cmd", "addplayer").add("symbol", false).build().toString();
              gameList.getAGame(json.getString("gameId")).getSession().getBasicRemote().sendText(a); 
                     }
                  // if(gameList.getAGame(json.getString("gameId")).getPlayers().size() > number){
                      //   a = builder.add("cmd", "unable").build().toString();
                      // p.getSession().getBasicRemote().sendText(a);
                   //  }
                 //  else
                   //  {  a= builder.add("cmd", "addplayer").add("symbol", true).build().toString();
                     //gameList.getAGame(json.getString("gameId")).getSession().getBasicRemote().sendText(a); 
                    //  } 
                      
               } 
                    catch (Exception ex) {
               ex.printStackTrace();
           }
               
            
           
           
            //String cardName=  gameList.getAGame(json.getString("gameId")).getDiscardPile().showTheTopCard().getCardName();
            //int count= gameList.getAGame(json.getString("gameId")).getDrawPile().getUnoCard().size();

            // session.getBasicRemote().sendText(msg);
        }
        if (json.getString("cmd").equals("initTable")) {
            System.out.println("click start a game");
            String gam=json.getString("gameId");
            gameList.getAGame(json.getString("gameId")).setSession(session);
            String cardName = gameList.getAGame(json.getString("gameId")).getDiscardPile().showTheTopCard().getCardName();
            int count = gameList.getAGame(json.getString("gameId")).getDrawPile().getUnoCard().size();
             ArrayList<player>players1=gameList.getAGame(json.getString("gameId")).getPlayers();
             for(player player:players1){
                 String playerName=player.getUserName();
                 String photo1=player.getPhoto();
                 System.out.println("table websocket photo"+playerName+photo1);
                builder= builder.add("playername", playerName).add("photo", photo1);
                 arrayBuilder.add(builder);
             }
            String j = builder.add("cmd", "openACard").add("players", arrayBuilder).add("cardName", cardName).add("count", count).build().toString();
                     
            try {
                session.getBasicRemote().sendText(j);
                ArrayList<player> players = gameList.getAGame(json.getString("gameId")).getPlayers();
                for (player p1 : players) {
                    String name = p1.getUserName();
                    String ph2=p1.getPhoto();
                    System.out.println("player websocket photo"+name+ph2);
                                System.out.println(name);
                    ArrayList<Card> cardOnHand = p1.getCardOnHand();
                    for (Card c : cardOnHand) {
                        String card = c.getCardName();
                        builder = builder.add("name", card);
                        arrayBuilder.add(builder);
                    }

                    String q = builder.add("cmd", "PlayerGetCard").add("photo", ph2).add("user", name).add("gameId", gam).add("cardOnHand", arrayBuilder).build().toString();
                    p1.getSession().getBasicRemote().sendText(q);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if(json.getString("cmd").equals("discardACard")){
            String discardname=json.getString("discardcard");
            Card c=new Card();
            c.setCardName(discardname);
            String playerName=json.getString("name");
              System.out.println( gameList.getAGame(json.getString("gameId")).getPlayerFromPlayers(playerName));
            gameList.getAGame(json.getString("gameId")).getPlayerFromPlayers(playerName).discardACard(discardname);
            gameList.getAGame(json.getString("gameId")).getDiscardPile().addCard(c);
             System.out.println(discardname);
               System.out.println(playerName);
              int noOfCard=gameList.getAGame(json.getString("gameId")).getPlayerFromPlayers(playerName).getCardOnHand().size();
                 System.out.println("justify UnoButton "+noOfCard);
              String msg3;
              if(noOfCard==0){
             ArrayList<player> players = gameList.getAGame(json.getString("gameId")).getPlayers();
                      int numberOfPlayers =players.size();
                       int[] value=new int[numberOfPlayers];
                    String[] player=new String[numberOfPlayers];
                    int i=0;
              for (player p1 : players) {
                    String name = p1.getUserName();
                                System.out.println(name);
                    ArrayList<Card> cardOnHand = p1.getCardOnHand();
                    int val =0;
                    
                    for (Card c2 : cardOnHand) {
                        String card = c2.getCardName();
                       String c3= card.substring(card.indexOf("$")+1, card.indexOf("."));
                        System.out.println("!!!!!card value!!!!!"+c3);
                             val =val+ Integer.parseInt(c3);

                    }
                    player[i]=name;
                    value[i]=val;
                    i++;
                   // builder = builder.add("playername", name).add("value", val);
                    //    arrayBuilder.add(builder);
              }
             
              int min=0;
              int tmp;
              String tmp1;
             
                
                  for(int h=0;h<player.length;h++){
                      min=h;
                      for(int p=h;p<player.length;p++){
                      if(value[p] < value[min]){
                          min=p;}
                          tmp=value[h];
                          tmp1=player[h];
                          value[h]=value[min];
                          player[h]=player[min];
                          value[min]=tmp;
                          player[min]=tmp1;
                      }
                      }
                  
              
              for(int w=0;w< player.length;w++){
                    builder = builder.add("playername", player[w]).add("value", value[w]);
                       arrayBuilder.add(builder);
              }
              msg3= builder.add("cmd","gameOver").add("grade", arrayBuilder).add("cardName",discardname).add("who",playerName ).build().toString();
             
                   
                   try{
                        gameList.getAGame(json.getString("gameId")).getSession().getBasicRemote().sendText(msg3);
                   
                        for (player p2 : players) {
                        p2.getSession().getBasicRemote().sendText(msg3);
                        }
                   }
           // gameList.getAGame(json.getString("gameId")).getPlayers().getSession().getBasicRemote().sendText(msg3);}
           catch (Exception ex) {
                ex.printStackTrace();
            }
              
              }
              if(noOfCard==1)
              {  msg3=builder.add("cmd", "CanSayUNO").add("cardName", discardname).add("who", playerName).build().toString();
                      try{
                      gameList.getAGame(json.getString("gameId")).getPlayerFromPlayers(playerName).getSession().getBasicRemote().sendText(msg3);
                      }catch (Exception ex) {
                ex.printStackTrace();}
            }
              else{
            msg3= builder.add("cmd","addADiscardCard").add("cardName",discardname).add("who",playerName ).build().toString();
              try{
            gameList.getAGame(json.getString("gameId")).getSession().getBasicRemote().sendText(msg3);}
           catch (Exception ex) {
                ex.printStackTrace();
            }
              }
          
        
    }
        if(json.getString("cmd").equals("drawACardforPlayer")){
            String playername=json.getString("playername");
            String game=json.getString("gameId");
            Card forplayer=gameList.getAGame(game).getCard();
            gameList.getAGame(game).getPlayerFromPlayers(playername).getCardOnHand().add(forplayer);
             System.out.println("!!!!!!!!draw a card zhihou "+gameList.getAGame(game).getPlayerFromPlayers(playername).getCardOnHand().size());
             String getCardForPlayer= forplayer.getCardName();
            int numberOfCard=  gameList.getAGame(game).getPlayerFromPlayers(playername).getCardOnHand().size();
            System.out.println("draw a card justify unoButton !!numberOfCard"+numberOfCard);
             String msg0;
             
             if(numberOfCard!=1){
                  msg0= builder.add("cmd", "giveACardForPlayer").add("unoButton", true).add("gameId", game).add("player",playername ).add("cardforplayer",getCardForPlayer ).build().toString();
             }
             else{
           msg0= builder.add("cmd", "giveACardForPlayer").add("unoButton", false).add("gameId", game).add("player",playername ).add("cardforplayer",getCardForPlayer ).build().toString();
          
             }
             try{
            gameList.getAGame(json.getString("gameId")).getPlayerFromPlayers(json.getString("playername")).getSession().getBasicRemote().sendText(msg0);
               System.out.println("give a card to me!!!!!!!! first time");
             }
           
           catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        if(json.getString("cmd").equals("IwantSayUno")){
            String player=json.getString("pname");
             System.out.println("WHO SAY UNO"+player);
            String g1=json.getString("gameId");
            String message=builder.add("cmd", "someOneSayUno").add("who",player).add("gameId",g1 ).build().toString();
            try{
            gameList.getAGame(g1).getSession().getBasicRemote().sendText(message);
            }
             catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(json.getString("cmd").equals("returnACardToPlayer")){
             String player1=json.getString("playername");
            String c2= json.getString("cardName");
             String g1=json.getString("gameId");
              Card forplayer=new Card();
              forplayer.setCardName(c2);
              gameList.getAGame(g1).getDiscardPile().getDiscardCard().removeLast();
              String lastCard=gameList.getAGame(g1).getDiscardPile().getDiscardCard().getLast().getCardName();
              System.out.println("websocket"+lastCard);
              gameList.getAGame(g1).getPlayerFromPlayers(player1).getCardOnHand().add(forplayer);
                String message=builder.add("cmd", "giveACardForPlayer").add("lastCard", lastCard).add("unoButton", true).add("cardforplayer", c2).add("player",player1).add("gameId",g1 ).build().toString();
               try{
                   gameList.getAGame(g1).getSession().getBasicRemote().sendText(message);
            gameList.getAGame(g1).getPlayerFromPlayers(player1).getSession().getBasicRemote().sendText(message);}
           catch (Exception ex) {
                ex.printStackTrace();
            } 
        }
        //if(json.getString("cmd").equals("restartAGame")){
           // gameList.getAGame(json.getString("gameId")).initialGame();
           
       // }
        return null;
    }

    @OnOpen
    public void open(Session s) {
        System.out.println("@OnOpen=" + s.getId());
        session = s;
    }

    @OnClose
    public void close() {
        System.out.println("@OnClose=" + session.getId());

    }

}
