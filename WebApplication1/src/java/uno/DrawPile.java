/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Little Rabit
 */
public class DrawPile {
     LinkedList<Card>  UnoCard =new LinkedList<Card> () ;
    public DrawPile(){
    
    }
      public LinkedList<Card> getUnoCard() {
        return  UnoCard;
        
    }

    /**
     * @param card_name the card_name to set
     */
    public void setUnoCard(LinkedList UnoCard) {
        this.UnoCard = UnoCard;
    }
    //public Card drawACard(){
     // return  UnoCard.getFirst();
   // }
}
