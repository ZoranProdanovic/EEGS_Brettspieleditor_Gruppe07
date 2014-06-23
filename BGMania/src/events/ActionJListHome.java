/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import view.GameSettings;

/**
 *
 * @author Adrian
 */
public class ActionJListHome extends MouseAdapter{
  protected JList list;
  protected JFrame home;
    
  public ActionJListHome(JList list, JFrame home){
   this.list = list;
   this.home = home;
   }
    
  @Override
  public void mouseClicked(MouseEvent e){
   if(e.getClickCount() == 1){ // you can comment this line and it also will be work 
     int index = list.locationToIndex(e.getPoint());
     ListModel dlm = list.getModel();
     Object item = dlm.getElementAt(index);
     list.ensureIndexIsVisible(index);
     System.out.println("Double clicked on " + item);
     home.setVisible(false);
     GameSettings game_settings_frame = new GameSettings(item.toString());
     game_settings_frame.setBounds(home.getBounds());
     game_settings_frame.setVisible(true);
   }
  }
}
