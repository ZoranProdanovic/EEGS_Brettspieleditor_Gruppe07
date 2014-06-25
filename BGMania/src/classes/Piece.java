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
public class Piece {
    
    protected Cell position;
    protected static Dice dice = new Dice(); // one instance of an object
    private int board_position = 1;
            
    public Piece(Cell position) {
        this.position = position;
    }
    
    public Object[] move(Cell old_position, Game game) {
      Object[] return_statement = new Object[4];
      return_statement[0] = 0; // return integer - false - true - modes
      return_statement[1] = 0; // return rollnumber
      return_statement[2] = game; // return game
      return_statement[3] = ""; // return spezialfield
      return return_statement;
    }

    /**
     * @return the position
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Cell position) {
        this.position = position;
    }

  /**
   * @return the board_position
   */
  public int getBoardPosition() {
    return board_position;
  }

  /**
   * @param board_position the board_position to set
   */
  public void setBoardPosition(int board_position) {
    this.board_position = board_position;
  }
    
}
