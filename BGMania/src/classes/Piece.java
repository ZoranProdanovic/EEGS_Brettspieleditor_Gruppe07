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
            
    public Piece(Cell position) {
        this.position = position;
    }
    
    public int move(Cell old_position, Game game) {
        return 0;
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
    
}
