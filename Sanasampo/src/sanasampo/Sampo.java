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
    
    public void kaynnista() throws FileNotFoundException, IOException {
        kysyKirjaimet();
        while (!r.alusta(input)) {
            JOptionPane.showMessageDialog(null, 
                    "Grid size should be equilateral (3x3, 4x4..) and contain no special characters!", 
                    "Grid Initialization Error!", JOptionPane.INFORMATION_MESSAGE);
            kysyKirjaimet();
        }
        hae();
        nayta();
    }

    public void asenna() throws FileNotFoundException, IOException {
        h = new Hakemisto(new Sanakirja());
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
        ui = new Kayttoliittyma(r, haku.getTulos(), haku.getHitlist(), this);
        SwingUtilities.invokeLater(ui);
    }
}
