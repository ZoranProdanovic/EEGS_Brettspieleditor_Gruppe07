/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.Random;

/**
 *
 * @author Adrian
 */
public class Dice {
    
    private Random dice_roller = new Random();
    
    public Dice() {
        
    }
    
    /**
     * @return a random dice number
     */
    public int roll() {
        return dice_roller.nextInt(6) + 1;
    }

}
