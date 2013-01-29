package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.logic.Haku;
import sanasampo.ui.Kayttoliittyma;

public class Sampo {

    private Kayttoliittyma ui;
    private Hakemisto h;
    private Ruudukko r;
    private String input;
    private Haku haku;

    //Konsoliprintit debuggausta varten
    
    public void kaynnista() throws FileNotFoundException, IOException {
        asenna();
        System.out.println("Prompting user for input..");
        kysyKirjaimet();
        while (!r.alusta(input)) {
            System.out.println("Invalid grid!\nDisplaying error message.");
            JOptionPane.showMessageDialog(null, "Grid size should be equilateral (3x3, 4x4..) and contain no special characters!", "Grid Initialization Error!", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Returned user to prompt");
            kysyKirjaimet();
        }
        System.out.println("Grid input passed!\nStarting search..");
        hae();
        System.out.println("Search finished succesfully!\nRendering GUI..");
        nayta();
    }

    private void asenna() throws FileNotFoundException, IOException {
        System.out.println("Loading dictionaries..");
        h = new Hakemisto(new Sanakirja());
        System.out.println("Dictionaries loaded!");
        r = new Ruudukko();
    }

    private void kysyKirjaimet() {
        input = JOptionPane.showInputDialog(null, "Anna kirjaimet: ", "Ruudukon valinta", 1);
    }

    private void hae() {
        haku = new Haku(h, r);
        haku.kaynnista();
    }

    private void nayta() {
        ui = new Kayttoliittyma(r, haku.getTulos());
        SwingUtilities.invokeLater(ui);
        System.out.println("GUI Rendered!");
    }
}
