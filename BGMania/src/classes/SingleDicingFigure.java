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
public class SingleDicingFigure extends Piece {

    public SingleDicingFigure(Cell position) {
        super(position);
    }
    
    // message as parameter reference
    @Override 
    public int move(Cell old_position, Game game) {
        String playermode = game.getPlayerList().get(game.getCurrentPlayerIndex()).getClass().toString();
        PlayerMode playermode_enum = PlayerMode.convertToEnum(playermode);
        int roll_numer = dice.roll();
        // refresh the game view
        System.out.println(playermode_enum.name());
        
        int return_number = 0; // false
        switch(playermode_enum)
        {
          case COMPUTER     : return_number = 1; // default mode : true
                              //game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().position.
                              break;
          case PLAYER1 : return_number = 2; // make additional automatic move for the computer
                              
                              break;
        }

        return return_number;
    }
    
}
