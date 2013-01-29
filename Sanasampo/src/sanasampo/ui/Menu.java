package sanasampo.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar{
    private JMenu fileMenu, editMenu, viewMenu;
        
     public Menu(){
         fileMenu = new JMenu("File");
         editMenu = new JMenu("Edit");
         viewMenu = new JMenu("View");
         
         lisaaValikot();
         lisaaValinnat();
    }
     
     private void lisaaValikot(){
          this.add(fileMenu);
          this.add(editMenu);
          this.add(viewMenu);
     }
     
     private void lisaaValinta(JMenu menu, String valinta){
         menu.add(valinta);
     }
     
     private void lisaaValinnat(){
         //File
         lisaaValinta(fileMenu, "New grid");
         lisaaValinta(fileMenu, "Exit");
         
         //Edit
         lisaaValinta(editMenu, "Edit dictionaries..");
         
         //View
         lisaaValinta(viewMenu, "About");
         lisaaValinta(viewMenu, "Help");
     }
}
