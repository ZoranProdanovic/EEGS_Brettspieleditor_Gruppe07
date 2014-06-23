/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Adrian
 */
public class TimerListener implements ActionListener{
	
    private int count = 0;
    private JTextField label;

    public TimerListener(JTextField label) {
      this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
      count++;
      int seconds = count;
      int hours = seconds / 3600;
      int minutes = (seconds % 3600) / 60;
      seconds = seconds % 60;
      
      label.setText(twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds));
    }
    private String twoDigitString(int number) {

      if (number == 0) {
          return "00";
      }

      if (number / 10 == 0) {
          return "0" + number;
      }

      return String.valueOf(number);
}
}