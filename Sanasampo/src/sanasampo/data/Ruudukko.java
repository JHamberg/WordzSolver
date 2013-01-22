package sanasampo.data;

// @author JHamberg
public class Ruudukko {

    private int reuna;
    private String ruudukko[][];
    private String kirjaimet;

    public boolean alusta(String k) {

        if (validate(k)) {
            kirjaimet = k;
            reuna = (int) Math.sqrt(k.length());
            ruudukko = new String[reuna][reuna]; //Luodaan AxA kokoinen ruudukko
            alustaRuudukko();
            return true;

        } else {
            return false;
        }

    }

    public int getKoko() {
        return reuna;
    }

    public String[][] getRuudukko() {
        return ruudukko;
    }

    private static boolean onKahdenPotenssi(long n) {
        if (n < 0) {
            return false;
        }
        long tst = (long) (Math.sqrt(n) + 0.5);
        return tst * tst == n;
    }

    private void alustaRuudukko() {
        int pituus = 0;
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                //Lisätään käyttäjän antamat kirjaimet ruudukkoon
                ruudukko[i][j] = Character.toString(kirjaimet.charAt(pituus));
                pituus++;
            }
        }
    }

    private boolean validate(String k) {
        //Pystyykö muodostamaan AxA -ruudukon
        if (!onKahdenPotenssi(k.length())) {
            return false;
        }

        //Onko käyttäjän syöte tyhjä
        if (k.isEmpty()) {
            return false;
        }

        //Sisältääkö syöte välilyöntejä
        if (k.contains(" ")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean containsChar(char ch) {
        String c = Character.toString(ch); //Käytössä String 
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                if (ruudukko[i][j].equals(c)){
                    return true;
                }
            }
        }
        return false;
    }
}
