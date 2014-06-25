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
    public Object[] move(Cell old_position, Game game) {
        
        Object[] return_statement = new Object[4];  
        String playermode = game.getPlayerList().get(game.getCurrentPlayerIndex()).getClass().toString();
        PlayerMode playermode_enum = PlayerMode.convertToEnum(playermode);
        int roll_numer = dice.roll();
        SpecialField sf = null;
        // refresh the game view
        // System.out.println(playermode_enum.name());

        int return_number = 0; // false
        switch(playermode_enum)
        {
          case COMPUTER : return_number = 1; // default mode : true
                          //game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().position.
                          break;
          case PLAYER1  : return_number = 2; // make additional automatic move for the computer
                          break;
        }
        
        sf = game.getBoard().getBoard()[game.getBoard().getBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition()).getX()][game.getBoard().getBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition()).getY()];
          
        if(sf != null)
        {
          System.out.println("distance: "+sf.getDistance());
          game.getCurrentPlayerObject().getSinglePiece().setPosition(sf.getNew_position());
          if(sf.getClass().getName().toUpperCase().split("\\.")[1].equals("SNAKE"))
          {
            game.getCurrentPlayerObject().getSinglePiece().setBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition() - sf.getDistance());
          }
          else
          {
            game.getCurrentPlayerObject().getSinglePiece().setBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition() + sf.getDistance());
          }
        }
        else
        {
          int new_position = game.getCurrentPlayerObject().getSinglePiece().getBoardPosition() + roll_numer;
          int board_size = game.getBoard().getX() * game.getBoard().getY();
          String special_field_name = "";
          if(new_position > board_size)
          {
            return_number = 0;
          }
          else
          {
            // set position
            game.getCurrentPlayerObject().getSinglePiece().setBoardPosition(new_position);
            // set cell position
            game.getCurrentPlayerObject().getSinglePiece().setPosition(game.getBoard().getBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition()));

            sf = game.getBoard().getBoard()[game.getBoard().getBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition()).getX()][game.getBoard().getBoardPosition(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition()).getY()];

            if(sf != null)
            {
  //            System.out.println(sf.getClass().getName().toUpperCase().split("\\.")[1]);
  //            special_field_name = sf.getClass().getName().toUpperCase().split("\\.")[1];
            }
          }
        }
        
//        if(return_number == 0)
//        {
//          roll_numer = 0;
//        }
        
        return_statement[0] = return_number;
        return_statement[1] = roll_numer;
        return_statement[2] = game;
        return_statement[3] = sf;
        
        return return_statement;
    }
    
}
