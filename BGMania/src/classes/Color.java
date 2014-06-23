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
public enum Color {
  YELLOW, WHITE, BROWN, BLUE, RED, GREEN;
  public static Color convertToEnum(String color_name)
  {
    switch(color_name.toUpperCase())
    {
      case "YELLOW" : return Color.YELLOW;
      case "WHITE" : return Color.WHITE;
      case "BROWN" : return Color.BROWN;
      case "BLUE" : return Color.BLUE;
      case "RED" : return Color.RED;
      case "GREEN" : return Color.GREEN;
    }
    return null;
  }
}
