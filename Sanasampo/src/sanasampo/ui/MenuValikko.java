package sanasampo.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import sanasampo.Sampo;

public class MenuValikko extends JMenuBar{
    private JMenu fileMenu, editMenu, viewMenu;
    private Sampo s;
    private Kayttoliittyma ui;
        
     public MenuValikko(Sampo sa, Kayttoliittyma u){
         s = sa;
         ui = u;
         
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
         menu.add(valinta).addActionListener(new ValikkoKuuntelija(s, ui));
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
