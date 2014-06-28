/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import classes.Properties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import view.GameSettings;

/**
 *
 * @author Adrian
 */
public class ActionJListProperties extends MouseAdapter{
  protected JList list;
  protected JFrame frame;
  protected JFileChooser file_chooser;
    
  public ActionJListProperties(JList list, JFrame frame, JFileChooser file_chooser) {
   this.list = list;
   this.file_chooser = file_chooser;
   this.frame = frame;
   }
    
  @Override
  public void mouseClicked(MouseEvent e) {
    if(e.getClickCount() == 1)
    { // you can comment this line and it also will be work 
      int index = list.locationToIndex(e.getPoint());
      ListModel dlm = list.getModel();
      Object item = dlm.getElementAt(index);
      list.ensureIndexIsVisible(index);

      String choosen_propertie = item.toString().toUpperCase().replaceAll("\\s+","");

      switch(Properties.valueOf(choosen_propertie))
      {
          case ADDGAME : File f = new File(System.getProperty("user.dir"));
                         file_chooser.setCurrentDirectory(f);
                         file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                         file_chooser.showOpenDialog(frame);
                         File selFile = file_chooser.getSelectedFile();
                         break;

          case CREDITS : JOptionPane.showMessageDialog(frame,"Developer:\n*Adrian Guzikowski\n*Zoran Prodanovic\n*Zlatan Zaric\n*Kevin Zofall", "Credits", JOptionPane.INFORMATION_MESSAGE);
                         break;
      }
    }
  }
  
  private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
  
}
