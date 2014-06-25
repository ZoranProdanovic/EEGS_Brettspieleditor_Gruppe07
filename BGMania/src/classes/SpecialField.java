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
public class SpecialField {
    
    protected Cell activation_position;
    protected Cell new_position;
    private int distance;
//    protected int board_position = 1;
    
    public SpecialField(Cell activation_position, Cell new_position, int distance) {
        this.activation_position = activation_position;
        this.new_position = new_position;
        this.distance = distance;
    }

    /**
     * @return the activation_position
     */
    public Cell getActivation_position() {
        return activation_position;
    }

    /**
     * @param activation_position the activation_position to set
     */
    public void setActivation_position(Cell activation_position) {
        this.activation_position = activation_position;
    }

    /**
     * @return the new_position
     */
    public Cell getNew_position() {
        return new_position;
    }

    /**
     * @param new_position the new_position to set
     */
    public void setNew_position(Cell new_position) {
        this.new_position = new_position;
    }

  /**
   * @return the distance
   */
  public int getDistance() {
    return distance;
  }

  /**
   * @param distance the distance to set
   */
  public void setDistance(int distance) {
    this.distance = distance;
  }

    
}
