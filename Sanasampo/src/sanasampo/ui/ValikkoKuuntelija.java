package sanasampo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sanasampo.Sampo;

public class ValikkoKuuntelija implements ActionListener {

    private Sampo s;
    private Kayttoliittyma ui;

    
    public ValikkoKuuntelija(Sampo sa, Kayttoliittyma u) {
        s = sa;
        ui = u;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Exit")){
            System.exit(0);
        }
        else if(action.equals("New grid")){
            try{s.kaynnista();}
            catch(Exception x){}
            ui.getFrame().setVisible(false);
        }

    }
}
