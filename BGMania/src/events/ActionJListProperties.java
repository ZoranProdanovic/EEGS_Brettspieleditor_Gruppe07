/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import classes.Properties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
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
import view.Home;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
                         file_chooser.
                                 setFileSelectionMode(JFileChooser.FILES_ONLY);
                         file_chooser.showOpenDialog(frame);
                         File selFile = file_chooser.getSelectedFile();
                         //System.out.println();
                         try{
                         if(!enterIntoGamesList(selFile.getName().
                                 substring(0, selFile.getName().length() - 4)))
                             break;
                         
                         unzip(selFile.getAbsolutePath(), 
                                 System.getProperty("user.dir") + 
                                 File.separator + "src");
                         }catch(IOException ioe){
                           ioe.printStackTrace();
                         }
                         frame.setVisible(false);
                         Home home_frame = new Home();
                         home_frame.setBounds(frame.getBounds());
                         home_frame.setVisible(true);
                         break;

          case CREDITS : JOptionPane.showMessageDialog(frame,"Developer:"
                  + "\n*Adrian Guzikowski"
                  + "\n*Zoran Prodanovic"
                  + "\n*Zlatan Zaric"
                  + "\n*Kevin Zofall", "Credits", 
                  JOptionPane.INFORMATION_MESSAGE);
                         break;
      }
    }
  }
  
   public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[1024];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
    public boolean enterIntoGamesList(String elem) throws IOException{
    FileInputStream in = new FileInputStream(System.getProperty("user.dir") + 
            File.separator + "src" + File.separator + "view" + File.separator +
            "LoadedGames.txt");
    FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + 
            File.separator + "src" + File.separator + "view" + File.separator +
            "LoadedGames.txt");
    BufferedReader reader;
    String line;

    reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
    while ((line = reader.readLine()) != null) {
        if(line.substring(0,line.length()-1).compareTo(elem) == 0)
        {
         reader.close();
         out.close();
         reader = null;
         out = null;
         return false;
        }
        
        out.write(line.getBytes());
    }
    out.write((elem + "\n").getBytes(Charset.forName("UTF-8")));
    reader.close();
    out.close();
    reader = null;
    out = null;
    return true;
    }
  
}
