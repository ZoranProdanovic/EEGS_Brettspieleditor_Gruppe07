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
public class Board {
    
    private SpecialField[][] board;
    private int x;
    private int y;
    
    public Board(int x, int y) {
        this.board = new SpecialField[x][y];
        this.x = x;
        this.y = y;
    }

    /**
     * @return the board
     */
    public SpecialField[][] getBoard() {
        return board;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param special_field the special_field to set
     */
    public void addSpecialFields(List<SpecialField> special_fields_list) {
        
        for(SpecialField single_sf : special_fields_list)
        {
            board[single_sf.getActivation_position().getX()][single_sf.getActivation_position().getY()] = single_sf;
        }
        
    }
    
}
