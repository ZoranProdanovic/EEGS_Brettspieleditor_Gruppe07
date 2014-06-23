/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import classes.Color;

/**
 *
 * @author Adrian
 */
public class Player {
    
    protected Color color;
    protected String name;
    private Piece single_piece;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }
    
    public Player() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * @return the icon path
     */
    public String getIconPath() {
        return "/img/figure_" + color.name().toLowerCase() + ".png";
    }

    /**
     * @return the piece
     */
    public Piece getSinglePiece() {
        return single_piece;
    }

    /**
     * @param piece the piece to set
     */
    public void setSinglePiece(Piece single_piece) {
        this.single_piece = single_piece;
    }
    
}
