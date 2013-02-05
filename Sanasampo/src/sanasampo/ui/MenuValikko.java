package sanasampo.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import sanasampo.Sampo;

/** Ylävalikko, josta pääsee käsiksi perustoiminnallisuuksiin kuten 
 * uudelleenajoon, sanakirjojen ja hakemiston muokkaamiseen jne. 
 */
public class MenuValikko extends JMenuBar{
    
    /** Valikon elementit: File, Edit ja View. */
    private JMenu fileMenu, editMenu, viewMenu;
    
    /** Yläluokka uudelleenajoa varten */
    private Sampo s;
    
    /** Käyttöliittymä kuuntelijoiden lisäykseen */
    private Kayttoliittyma gui;
        
    /** Luo valikkoelementit sekä niille valikot ja lisää päänäkymään
     * @param sa Yläluokka valikkoelementin kuuntelijalle, joka käsittelee uudelleenajoa
     * @param u Käyttöliittymän perustoiminnallisuudet kuuntelijalle 
     */
     public MenuValikko(Sampo sa, Kayttoliittyma u){
         s = sa;
         gui = u;
         
         fileMenu = new JMenu("File");
         editMenu = new JMenu("Edit");
         viewMenu = new JMenu("View");
         
         lisaaValikot();
         lisaaValinnat();
    }
     
     /** Lisää konstruktorissa alustetut valikot JMenuBar-elementtiin (this)*/
     private void lisaaValikot(){
          this.add(fileMenu);
          this.add(editMenu);
          this.add(viewMenu);
     }
     
     /** Lisää valikkoelementille pudotusvalikossa näkyvän valinnan 
      * ja sille kuuntelijan käyttäjän syötteen perusteella
      * @param menu Valikkoelementti, jolle valinta lisätään
      * @param valinta Valintaa kuvaava teksti
      */
     private void lisaaValinta(JMenu menu, String valinta){
         menu.add(valinta).addActionListener(new ValikkoKuuntelija(s, gui));
     }
     
     
     /** Lisää valinnat ylävalikon elementeille */
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
