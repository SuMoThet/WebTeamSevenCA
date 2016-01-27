/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import javax.websocket.Session;

/**
 *
 * @author Little Rabit
 */
public class Game {

    private String gameId = UUID.randomUUID().toString().substring(0, 8);
    private String description;
    private ArrayList<player> players = new ArrayList<player>();
    private DrawPile drawPile = new DrawPile();
    private DiscardPile discardPile = new DiscardPile();
    private Session session;
    private int joinedPlayers;
    private int number;

    public int getJoinedPlayers() {
        return this.getPlayers().size();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
    static final int TotalUnoCard = 108;

    public Game() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayers(ArrayList<player> players) {
        this.players = players;
    }

    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void addPlayer(player p) {
        players.add(p);
    }

    public ArrayList<player> getPlayers() {
        return (players);
    }

    public void initialGame() {
        if(drawPile.getUnoCard().size()!=0||discardPile.getDiscardCard().size()!=0){
        drawPile.getUnoCard().clear();
        discardPile.getDiscardCard().clear();
    }
       //for(player p:players)
     //  {
         //  if(p.getCardOnHand().size()!=0)
         //  {
          //     p.getCardOnHand().clear();
         //  }
     //  }
         for(int i=0; i<TotalUnoCard; i++)
         {
          drawPile.getUnoCard().add(i,new Card());
          drawPile.getUnoCard().get(i).setCardName(cardName[i]+"$"+cardValue[i]+".png");  
          }   

//        ArrayList<Card> NumberCard;
//        ArrayList<Card> FunctionCard = new ArrayList<Card>();
//        String[] color = new String[]{"r1", "y1", "g1", "p1", "r2", "y2", "g2", "p2"};
//        String[] value = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        String name = null;
//        String cardcolor;
//        for (int i = 0; i < 8; i++) {
//            NumberCard = new ArrayList<Card>();
//            cardcolor = color[i];
//            for (int j = 0; j < value.length; j++) {
//                String v = value[j];
//                name = cardcolor + value[j];
//                Card c = new Card();
//                NumberCard.add(c);
//                NumberCard.get(j).setCardName("N" + name + ".png");
//                drawPile.getUnoCard().add(c);
//
//            }
//
//        }
//        for (int k = 0; k < 4; k++) {
//            NumberCard = new ArrayList<Card>();
//            cardcolor = color[k];
//            name = cardcolor + 0;
//            Card c = new Card();
//            NumberCard.add(c);
//            NumberCard.get(k).setCardName("N" + name + ".png");
//            drawPile.getUnoCard().add(c);
//
//        }
//        for (int i = 0; i < 32; i++) {
//            if (i < 8) {
//                name = "B" + i;
//            } else {
//                name = "F" + i;
//            }
//            Card c = new Card();
//            FunctionCard.add(c);
//            FunctionCard.get(i).setCardName(name + ".png");
//            drawPile.getUnoCard().add(c);
//        }
//        System.out.println(drawPile.getUnoCard().toString());

        this.shuffle();
        for (player p : players) {
            p.setCardOnHand(this.getCards(7));
        }
        discardPile.addCard(this.getCard());

    }

    public void shuffle() {
        LinkedList<Card> temp = new LinkedList<Card>();
        while (!drawPile.getUnoCard().isEmpty()) {
            int loc = (int) (Math.random() * drawPile.getUnoCard().size());
            temp.add(drawPile.getUnoCard().get(loc));
            drawPile.getUnoCard().remove(loc);
        }

        this.drawPile.setUnoCard(temp);
    }

    public ArrayList<Card> getCards(int noOfCard) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < noOfCard; i++) {
            cards.add(i, this.getCard());
            System.out.println("final 7 cards >> " + cards.get(i).getCardName());
        }
        return cards;
    }

    public Card getCard() {
        if (drawPile.getUnoCard().size() < 0) {
            return null;
        } else {
            Card card = drawPile.getUnoCard().getFirst();
            drawPile.getUnoCard().remove(card);
            System.out.println("Retrieve Card >>" + card.getCardName());
            return card;
        }
    }

    public player getPlayerFromPlayers(String playerName) {
        for (player p1 : this.players) {
            if (p1.getUserName().equals(playerName)) {
                return p1;
            }
        }
        return null;
    }
      public static final String[] cardName={
      "c0_00","c0_01","c0_02","c0_03","c0_04",
      "c0_05","c0_06","c0_07","c0_08","c0_09",
      "c0_01_1","c0_02_1","c0_03_1","c0_04_1",
      "c0_05_1","c0_06_1","c0_07_1","c0_08_1","c0_09_1",
      "c0_10","c0_11","c0_12","c0_10_1","c0_11_1","c0_12_1",
      
      "c1_00","c1_01","c1_02","c1_03","c1_04",
      "c1_05","c1_06","c1_07","c1_08","c1_09",
      "c1_01_1","c1_02_1","c1_03_1","c1_04_1",
      "c1_05_1","c1_06_1","c1_07_1","c1_08_1","c1_09_1",
      "c1_10","c1_11","c1_12","c1_10_1","c1_11_1","c1_12_1",
      
      "c2_00","c2_01","c2_02","c2_03","c2_04",
      "c2_05","c2_06","c2_07","c2_08","c2_09",
      "c2_01_1","c2_02_1","c2_03_1","c2_04_1",
      "c2_05_1","c2_06_1","c2_07_1","c2_08_1","c2_09_1",
      "c2_10","c2_11","c2_12","c2_10_1","c2_11_1","c2_12_1",
      
      "c3_00","c3_01","c3_02","c3_03","c3_04",
      "c3_05","c3_06","c3_07","c3_08","c3_09",
      "c3_01_1","c3_02_1","c3_03_1","c3_04_1",
      "c3_05_1","c3_06_1","c3_07_1","c3_08_1","c3_09_1",
      "c3_10","c3_11","c3_12","c2_10_1","c2_11_1","c2_12_1",
      "c4_00", "c4_00_1", "c4_00_2", "c4_00_3",
      "c4_01", "c4_01_1", "c4_01_2", "c4_01_3"
    };
    
    
    
    
    public static final int[] cardValue={
      0,1,2,3,4,
      5,6,7,8,9,
      1,2,3,4,5,
      6,7,8,9,
      20,20,20,20,20,20,
            
      0,1,2,3,4,
      5,6,7,8,9,
      1,2,3,4,5,
      6,7,8,9,
      20,20,20,20,20,20,
      
      0,1,2,3,4,
      5,6,7,8,9,
      1,2,3,4,5,
      6,7,8,9,
      20,20,20,20,20,20,
      
      0,1,2,3,4,
      5,6,7,8,9,
      1,2,3,4,5,
      6,7,8,9,
      20,20,20,20,20,20,
      
      50,50,50,50,
      50,50,50,50
    };
}
