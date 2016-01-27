/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uno;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;


 
public class player {
   
   
 
    private String userName;
    private String password;
  private ArrayList<Card> cardOnHand=new ArrayList<Card>();
  private Session  session;
private String photo;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
    public player(){
    }

    public player(String userName) {
        this.userName = userName;
    }
   
    
        /** public player(int UID, String UName, String Uemail, String Upassword , ArrayList<Card> cardOnHand){
        this.userId = UID;
        this.userName = UName;
     this.cardOnHand = cardOnHand;
        this.password = Upassword;
        }
        /**
         * @return the userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName the userName to set
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * @return the email
         */
        public  ArrayList<Card> getCardOnHand() {
            return cardOnHand;
        }

        /**
         * @param email the email to set
         */
        public void setCardOnHand( ArrayList<Card> cardOnHand) {
             this.cardOnHand = cardOnHand;
        }

        /**
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password the password to set
         */
        public void setPassword(String password) {
            this.password = password;
        }
        public void discardACard(String card){
            for(Card c:this.getCardOnHand())
            {
                if(c.getCardName().equals(card))
                {  this.getCardOnHand().remove(c);
                break;
                }
            }
            System.out.println("!!!player discard a card zhi hou de shuliang "+this.getCardOnHand().size());
        }
}

