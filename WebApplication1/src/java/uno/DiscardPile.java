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
public class DiscardPile {
     LinkedList<Card> discardCard=new LinkedList<Card> ();
     
    public DiscardPile(){
  
    }
      public LinkedList<Card> getDiscardCard() {
        return  discardCard;
        
    }

    /**
     * @param card_name the card_name to set
     */
    public void setDiscardCard(LinkedList discardCard) {
        this.discardCard = discardCard;
    }
    public void addCard(Card c){
        discardCard.add(c);
    }
    public Card showTheTopCard(){
        return discardCard.getLast();
    }
     
    
}
