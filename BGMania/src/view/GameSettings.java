/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import classes.Color;
import classes.PlayerMode;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Adrian
 */
public class GameSettings extends javax.swing.JFrame {

  /**
   * Creates new form GameSettings
   */

    
  public GameSettings() {
    initComponents();

  }
    private String game_name;
    public GameSettings(String game_name) {
        initComponents();
        this.game_name = game_name;
        ChangeListener aChangeListener = new SliderListener();
        jSliderPlayerMode.addChangeListener(aChangeListener);
    }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jSliderPlayerMode = new javax.swing.JSlider();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jButtonPlay = new javax.swing.JButton();
    jButtonInstruction = new javax.swing.JButton();
    jButtonBack = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jComboBoxPlayer1 = new javax.swing.JComboBox();
    jComboBoxPlayer2 = new javax.swing.JComboBox();
    jLabel5 = new javax.swing.JLabel();
    jLabelPlayer2 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
    jLabel1.setText("BG Mania");

    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tu_graz_icon.png"))); // NOI18N

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Settings"));

    jSliderPlayerMode.setMaximum(1);
    jSliderPlayerMode.setToolTipText("");
    jSliderPlayerMode.setValue(0);

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel3.setText("Single Player");

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel4.setText("Two Players");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jSliderPlayerMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(jLabel4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jSliderPlayerMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButtonPlay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonPlay.setText("Play");
    jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonPlayActionPerformed(evt);
      }
    });

    jButtonInstruction.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonInstruction.setText("Instruction");
    jButtonInstruction.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonInstructionActionPerformed(evt);
      }
    });

    jButtonBack.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonBack.setText("Back");
    jButtonBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonBackActionPerformed(evt);
      }
    });

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose Color:"));

    jComboBoxPlayer1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jComboBoxPlayer1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BLUE", "GREEN", "YELLOW", "WHITE", "BROWN" }));
    jComboBoxPlayer1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxPlayer1ActionPerformed(evt);
      }
    });

    jComboBoxPlayer2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jComboBoxPlayer2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RED", "GREEN", "YELLOW", "WHITE", "BROWN" }));
    jComboBoxPlayer2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxPlayer2ActionPerformed(evt);
      }
    });

    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel5.setText("PLAYER1");

    jLabelPlayer2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabelPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelPlayer2.setText("COMPUTER");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jComboBoxPlayer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jComboBoxPlayer2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabelPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(jLabelPlayer2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jComboBoxPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jComboBoxPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jButtonBack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jButtonInstruction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jButtonPlay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButtonInstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.setVisible(false);
        Home home_frame = new Home();
        home_frame.setBounds(this.getBounds());
        home_frame.setVisible(true);
    }//GEN-LAST:event_jButtonBackActionPerformed

    class SliderListener implements ChangeListener {
      public void stateChanged(ChangeEvent e) {
      JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) 
        {
            int fps = (int)source.getValue();
            if (fps == 0) 
            {
                jLabelPlayer2.setText("COMPUTER");
            } 
            else 
            {
                jLabelPlayer2.setText("PLAYER2");
            }
        }
      }
    }
    
    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        this.setVisible(false);
        String name = this.game_name;
        Game game_frame = null;
    try {
      game_frame = new Game(name, PlayerMode.fromOrdinal(jSliderPlayerMode.getValue()), Color.convertToEnum(jComboBoxPlayer1.getSelectedItem().toString()), Color.convertToEnum(jComboBoxPlayer2.getSelectedItem().toString()));
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchMethodException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
      Logger.getLogger(GameSettings.class.getName()).log(Level.SEVERE, null, ex);
    }
        //game_frame.setBounds(this.getBounds());
        game_frame.setLocationRelativeTo(null); // center the position of this jFrame
        game_frame.setVisible(true);
    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jButtonInstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInstructionActionPerformed
      JOptionPane.showMessageDialog(this,"Snake and Ladders \n\n"
             + "The game is played as the actual boardgame; stepping on a snakehead"
             + " will slide you down to its tail, \non the other hand "
             + "stepping onto a ladder will let you climb up (only up) to the field it points "
             + "to. \nGenerally, the goal of the game is to get to the final field, whereas "
             + "you have to EXACTLY throw the \nnumber needed to step onto the last field.", "Instruction", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonInstructionActionPerformed

  private void jComboBoxPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlayer1ActionPerformed
    if(jComboBoxPlayer1.getSelectedItem().equals(jComboBoxPlayer2.getSelectedItem()))
    {
      jComboBoxPlayer1.setSelectedIndex(0);
    }
    else
    {
      if(jComboBoxPlayer1.getSelectedItem().equals(jComboBoxPlayer1.getItemAt(0)))
      {
        
      }
      else
      {
        Object selected_item_player1 = jComboBoxPlayer1.getSelectedItem();
        int selected_item_player1_int = jComboBoxPlayer1.getSelectedIndex();
        Object first_position1 = jComboBoxPlayer1.getItemAt(0);
        Object selected_item_player2 = jComboBoxPlayer2.getSelectedItem();
        int selected_item_player2_int = jComboBoxPlayer2.getSelectedIndex();
        Object first_position2 = jComboBoxPlayer2.getItemAt(0);
//        DefaultComboBoxModel combobox_model = new DefaultComboBoxModel(new String[] {"BLUE", "RED", "GREEN", "YELLOW", "WHITE", "BROWN"});

        List<String> strlist1 = new ArrayList<String>();
        for(int i = 0; i < jComboBoxPlayer1.getModel().getSize(); i++)
        {
          strlist1.add(jComboBoxPlayer1.getModel().getElementAt(i).toString());
        }
  
        strlist1.remove(selected_item_player1);
        strlist1.remove(selected_item_player2);
        strlist1.set(0, selected_item_player1.toString());
        strlist1.add(first_position1.toString());
        
        String[] strarray = new String[strlist1.size()];
        strlist1.toArray(strarray);
         
        jComboBoxPlayer1.setModel(new DefaultComboBoxModel(strarray));
        
        List<String> strlist2 = new ArrayList<String>();
        for(int i = 0; i < jComboBoxPlayer2.getModel().getSize(); i++)
        {
          strlist2.add(jComboBoxPlayer2.getModel().getElementAt(i).toString());
        }

        strlist2.remove(selected_item_player1);
//        strlist2.remove(selected_item_player2);
        strlist2.set(0, selected_item_player2.toString());
        strlist2.add(first_position1.toString());
        
        String[] strarray2 = new String[strlist2.size()];
        strlist2.toArray(strarray2);
         
        jComboBoxPlayer2.setModel(new DefaultComboBoxModel(strarray2));
        
      }
      
    }
    
  }//GEN-LAST:event_jComboBoxPlayer1ActionPerformed

  private void jComboBoxPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlayer2ActionPerformed

    if(jComboBoxPlayer2.getSelectedItem().equals(jComboBoxPlayer1.getSelectedItem()))
    {
      jComboBoxPlayer2.setSelectedIndex(0);
    }
    else
    {
      if(jComboBoxPlayer2.getSelectedItem().equals(jComboBoxPlayer2.getItemAt(0)))
      {
        
      }
      else
      {
        Object selected_item_player1 = jComboBoxPlayer1.getSelectedItem();
        int selected_item_player1_int = jComboBoxPlayer1.getSelectedIndex();
        Object first_position1 = jComboBoxPlayer1.getItemAt(0);
        Object selected_item_player2 = jComboBoxPlayer2.getSelectedItem();
        int selected_item_player2_int = jComboBoxPlayer2.getSelectedIndex();
        Object first_position2 = jComboBoxPlayer2.getItemAt(0);

        List<String> strlist2 = new ArrayList<String>();
        for(int i = 0; i < jComboBoxPlayer2.getModel().getSize(); i++)
        {
          strlist2.add(jComboBoxPlayer2.getModel().getElementAt(i).toString());
        }

        strlist2.remove(selected_item_player1);
        strlist2.remove(selected_item_player2);
        strlist2.set(0, selected_item_player2.toString());
        strlist2.add(first_position2.toString());
 
        String[] strarray2 = new String[strlist2.size()];
        strlist2.toArray(strarray2);
         
        jComboBoxPlayer2.setModel(new DefaultComboBoxModel(strarray2));
        
        List<String> strlist1 = new ArrayList<String>();
        for(int i = 0; i < jComboBoxPlayer1.getModel().getSize(); i++)
        {
          strlist1.add(jComboBoxPlayer1.getModel().getElementAt(i).toString());
        }

        strlist1.remove(selected_item_player2);
        strlist1.add(first_position2.toString());
        
        String[] strarray1 = new String[strlist1.size()];
        strlist1.toArray(strarray1);
         
        jComboBoxPlayer1.setModel(new DefaultComboBoxModel(strarray1));
      }
    }
  }//GEN-LAST:event_jComboBoxPlayer2ActionPerformed

    
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(GameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(GameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(GameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(GameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
          new GameSettings().setVisible(true);
      }
    });
    
    
    
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonBack;
  private javax.swing.JButton jButtonInstruction;
  private javax.swing.JButton jButtonPlay;
  private javax.swing.JComboBox jComboBoxPlayer1;
  private javax.swing.JComboBox jComboBoxPlayer2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabelPlayer2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSlider jSliderPlayerMode;
  // End of variables declaration//GEN-END:variables


}
