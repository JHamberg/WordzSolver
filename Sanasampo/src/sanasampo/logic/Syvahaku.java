package sanasampo.logic;

import java.util.ArrayList;
import sanasampo.data.Ruudukko;

/** Suorittaa pääpiireittäin monimutkaisen rekursiivisen hakualgoritmin, joka 
 * käy läpi parametrina saadun sanan ensimmäisestä kirjaimesta lähtien kaikki ne 
 * kirjainpolut, joista sana voitaisiin muodostaa. */
public class Syvahaku {

    /** Ruudukko kaksiulotteisena listana iterointia varten */
    private String[][] grid;
    
    /** Ruudukon reunan pituus */
    private int N;
    
    /** Parametrina saatu, ruudukosta haettava sana */
    private String s;
    
    /** Apuluokka koordinaattien yksiselitteisen ja -ulotteisen 
     * esitystavan luomiseen */
    private Helper h;
    
    /** Löytyneen sanan muodostamiseen tarvittavien kirjaimien koordinaatit */
    private ArrayList<String> polku;

    /** Alustaa ruudukon ja sen koon */
    public Syvahaku(Ruudukko ruudukko) {
        grid = ruudukko.getRuudukko();
        N = ruudukko.getKoko();
        h = new Helper();
    }

    /** Iteroi läpi ruudukon etsien parametrina annetun sanan alkukirjainten sijainteja
     * ja suorittaa hakualgoritmin tällaisen löytyessä.
     * @param s Etsittävä sana 
     * @return True jos sanan muodostamiseen tarvittavat kirjaimet löytyvät toisiinsa
     *linkitettyinä ruudukosta. False jos sanaa ei pysty muodostamaan. */
    public boolean suorita(String s) {
        this.s = s;
        ArrayList<String> edelliset = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j].equals(Character.toString(s.charAt(0)))) {
                    //tN = tarkistaNaapurit
                    if (tN(i, j, 0, edelliset)) { 
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Palauttaa annetun listan uutena oliona. Toteutus tarvitaan rekursiopinon
     *hallintaa varten
     *@return Kloonattu lista*/
    public static ArrayList<String> kloonaaLista(ArrayList<String> list) {
        ArrayList<String> clonedList = new ArrayList<String>();
        for (String s : list) {
            clonedList.add(s);
        }
        return clonedList;
    }
 
     /** Käy läpi kaikki solun naapurit ja kelpaavan arvon löytyessä 
     * suorittaa vastaavan tarkistuksen uuden solun naapurisoluille,
     * palauttaa true jos kelpaavia naapurisoluja löytyy samasta 
     * rekursiopolusta sanan pituuden verran. Toisin sanoen, jos 
     * sana voidaaan muodostaa kirjaimilla jotka ovat yhteydessä 
     * toisiinsa, on sana löydettävissä.
     * <br><br>
     * Vastaanottaa parametrina sanan alkukirjaimen koordinaatit, joista
     * naapurisolujen tarkastus aloitetaan ilmansuuntien mukaan, toinen 
     * ehtolause tarkistaa, etteivät tarkistettavat solut ole ruudukon
     * ulkopuolella. Kelpaavien solujen löytyessä niiden koordinaatit lisätään kyseisen
     * polun edelliset-listaan, joka sisältää jo siinä käytettyjen 
     * kirjaimien koordinaatit. Näin vältytään käyttämästä samaa kirjainta
     * uudestaan sanan muodostuksessa. 
     * <br><br>
     * Kaikki ilman- ja väli-ilmansuunnat ovat hyväksyttyjä, eikä 
     * sanojen löytymisen ehtona ole lineaarisuus. 
     * <br>
     * Metodin nimi on lyhenne vanhasta <i>tarkistaNaapurit</i> nimestä. 
     * 
     * @author JHamberg
     * @param x Alkukirjaimen x-koordinaatti
     * @param y Alkukirjaimen y-koordinaatti
     * @param sijainti Kirjain jota solusta etsitään
     * @param edelliset Polun aikaisempien kirjainten koordinaatit
     * @return True jos sana voidaan muodostaa ruudukon kirjaimista em. 
     * ehtojen mukaan. False jos sanalle ei löydy polkua tai jos polku
     * on vajaa.
     */
     private boolean tN(int x, int y, int sijainti, ArrayList<String> edelliset) {
        if(sijainti >= s.length()){
            polku = kloonaaLista(edelliset);
            return true;
        }
        if (x >= N || y >= N || x < 0 || y < 0) return false; 
        if (gridEquals(x, y, sijainti) && notAccessed(x,y,edelliset)){
           ArrayList<String> ed = kloonaaLista(edelliset);
           ed.add(h.yhdista(x, y));
            
        if(tN(x+1, y+1, sijainti+1, ed)||
        tN(x+1, y, sijainti+1, ed)||
        tN(x+1, y-1, sijainti+1, ed)||
        tN(x-1, y+1, sijainti+1, ed)||
        tN(x-1, y, sijainti+1, ed)||
        tN(x-1, y-1, sijainti+1, ed)||
        tN(x, y+1, sijainti+1, ed)||
        tN(x, y-1, sijainti+1, ed)){return true;}
        
        }
        return false;
    } 
   
    /** Tarkistaa onko ruudukon parametrina saadussa kohdassa sanasta 
     * etsittävä kirjain 
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     * @param sijainti Kirjain jota sanasta etsitään
     */
    private boolean gridEquals(int x, int y, int sijainti){
        if(grid[x][y].equals(Character.toString(this.s.charAt(sijainti)))){
            return true;
        }
        else {return false;}
    }
    
    /** Tarkistaa ettei ruudussa ole jo käyty kyseisellä polulla 
     *@param x Ruudun x-koordinaatti
     *@param y Ruudun y-koordinaatti 
     *@param e Lista edellisten ruutujen koordinaateista
     */
    private boolean notAccessed(int x, int y, ArrayList<String> e){
        for(String k : e){
            if(k.equals(h.yhdista(x,y))){
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<String> getPolku(){
        return polku;
    }
    
}
