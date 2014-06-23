/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Adrian
 */
public enum PlayerMode {
    COMPUTER, PLAYER1, MULTIPLAYER;
    private static PlayerMode[] allValues = values();
    public static PlayerMode fromOrdinal(int n) {return allValues[n];}
    
    public static PlayerMode convertToEnum(String player_mode)
    {
      player_mode = player_mode.split("\\.")[1];
      switch(player_mode.toUpperCase())
      {
        case "COMPUTER" : return PlayerMode.COMPUTER;
        case "PLAYER1" : return PlayerMode.PLAYER1;
        case "MULTIPLAYER" : return PlayerMode.MULTIPLAYER;
      }
      return null;
    }
    
}
