/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.List;
import java.lang.Math.*;
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
    
    public Cell getBoardPosition(int board_position) {
      int x = 0, y = 0;
      float y_rounded =  ((float) board_position / (float) this.x);
      y = (board_position / this.x);
      y_rounded -= y;
      
      if(y_rounded == 0.0)
      {
        y -= 1;
      }
      
      x = (board_position % this.x);
      
      if ( y % 2 == 0 )
      {
        // Even - Gerade
        if( x == 0)
        {
          x = this.x - 1;
        }
        else
        {
          x -= 1;
        }
      }
      else
      {
        // Odd - Ungerade
        if( x != 0)
        {
          x = this.x - x;
        }
      }
      
      Cell cell_position = new Cell(x, y);
      return cell_position;
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
