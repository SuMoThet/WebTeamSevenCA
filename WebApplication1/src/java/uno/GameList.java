/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Little Rabit
 */

@ApplicationScoped
@Named
public class GameList {
    Map<String,Game>gameList=new HashMap<String,Game>();
    public void addGame(Game g){
        gameList.put(g.getGameId(), g);
    }

    public void setGameList(Map<String, Game> gameList) {
        this.gameList = gameList;
    }

    public Map<String, Game> getGameList() {
        return gameList;
    }
    public Game getAGame(String gameId){
       return  gameList.get(gameId);
        
    }
    public Set<String> getListOfGameId(){
         return gameList.keySet();
    }
      public Collection<Game> getListOfGameValue(){
         return gameList.values();
    }
    
}
