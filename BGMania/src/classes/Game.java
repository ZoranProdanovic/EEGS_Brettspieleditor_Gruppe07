/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.List;

/**
 *
 * @author Adrian
 */
public class Game {
    
    private int current_player;
    private Board board;
    private int round;
    private List<Player> player_list;
    
    public Game(Board board, List<Player> player_list) {
        this.board = board;
        this.round = 0;
        this.player_list = player_list;
    }

    /**
     * @return the current_player
     */
    public int getCurrentPlayerIndex() {
        return this.current_player;
    }
    
    public void addPlayer(Player player) {
      player_list.add(player);
    }
    
    public List<Player> getPlayerList() {
      return player_list;
    }

    /**
     * @param current_player the current_player to set
     */
//    public void setCurrentPlayerIndex(int current_player) {
//        this.current_player = current_player;
//    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }

    /**
     * @param round the round to set
     */
    public int nextRound() {
        return this.round+=1;
    }
    
    public void resetRound(int reset) {
        this.round = reset;
    }
    
    public void nextPlayer()
    {
      if(this.current_player == 0)
      {
        this.current_player = 1;
      }
      else 
      {
        this.current_player = 0;
      }
    }
    
//    public void run()
//    {
//        while(true)
//        {
//            
//        }
//    }
    
}
