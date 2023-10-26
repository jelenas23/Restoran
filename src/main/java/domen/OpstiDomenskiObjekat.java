package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * OpstiDomenskiObjekat je apstraktna domenska klasa koju nasledjuju sve ostale domenske klase.
 * Sastoji se od devet apstraktnih metoda koje svaka od domenskih klasa implementira.
 * 
 * @author Jelena
 *
 */
public abstract class OpstiDomenskiObjekat implements Serializable{

	/**
	 * Vraca String koji sadrzi naziv tabele.
	 * 
	 * String koji sadrzi naziv tabele je neophodan prilikom poziva CRUD operacija nad odredjenom
	 * tabelom u bazi podataka, koja odgovara domenskoj klasi koja implementira ovu metodu.
	 * 
	 * @return naziv tabele kao String.
	 */
	public abstract String nazivTabele();
	
	/**
	 * Vraca String koji sadrzi alijas tabele.
	 * 
	 * String koji sadrzi alijas tabele je neophodan prilikom poziva SELECT upita nad tabelom u bazi podataka,
	 * koja odgovara domenskoj klasi koja implementira ovu metodu.
	 * 
	 * @return alijas tabele kao String.
	 */
    public abstract String alijas();
  
    /**
     * Vraca String koji sadrzi JOIN klauzulu.
	 * 
	 * String koji sadrzi JOIN klauzulu omogucava da se tabela koja odgovara domenskoj klasi koja implementira
	 * ovu metodu poveze sa jednom ili vise drugih tabela u bazi podataka putem primarnog ili spoljnog kljuca.
	 * 
	 * @return JOIN klauzula kao String.
     */
    public abstract String join();
    
    /**
     * Vraca listu objekata koji nasledjuju apstraktnu domensku klasu. U listi ce se 
     * nalaziti objekti iz tabele koja odgovara domenskoj klasi koja implementira ovu metodu, 
     * a oni ce biti preuzeti iz baze u okviru ResultSet-a pomocu SELECT upita.
     * 
     * @param rs kao skup objekata odredjene domenske klase koji je dobijen iz baze pomocu SELECT upita.
     * 
     * @return lista sa objektima koji nasledjuju apstraktnu domensku klasu.
     * 
     * @throws SQLException ako dodje do greske prilikom izvrsavanja SELECT upita.
     */
    public abstract ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;
      
    /**
     * Vraca String koji sazdrzi nazive kolona koji su neophodni prilikom izvrsavanja INSERT upita.
     * 
     * String sa nazivima kolona je potreban prilikom ubacivanja novih objekata tj. redova 
     * u tabeli koja odgovara domenskoj klasi koja implementira ovu metodu.
     * 
     * @return nazivi kolona za izvrsavanje INSERT upita kao String.
     */
    public abstract String koloneZaInsert();
    
    /**
     * Vraca String koji sadrzi vrednost za primarni kljuc datog objekta tj. tabele
     * u bazi podataka koja odgovara domenskoj klasi koja implementira ovu metodu.
     * 
     * Vrednost za primarni kljuc je neophodna prilikom izvrsavanja UPDATE ili DELETE upita.
     * 
     * @return vrednost za primarni kljuc kao String.
     */
    public abstract String vrednostZaPrimarniKljuc();
    
    /**
     * Vraca String koji sadrzi vrednosti kolona koje su neophodne prilikom izvrsavanja INSERT upita.
     * 
     * Vrednosti kolona su potrebne prilikom ubacivanja novih objekata tj. redova u tabelu u bazi podataka,
     * koja odgovara domenskoj klasi koja implementira ovu metodu.
     * 
     * @return vrednosti kolona za izvrsavanje INSERT upita kao String.
     */
    public abstract String vrednostiZaInsert();
  
    /**
     * Vraca String koji sadrzi vrednosti koje su neophodne prilikom izvrsavanja UPDATE upita.
     * 
     * Vrednosti se odnose na nazive kolona i nove vrednosti za izmenu postojecih redova, u tabeli koja
     * odgovara domenskoj klasi koja implementira ovu metodu.
     * 
     * @return vrednosti za izvrsavanje UPDATE upita kao String.
     */
    public abstract String vrednostiZaUpdate();
    
    /**
     * Vraca String koji sadrzi WHERE klauzulu.
     * 
     * Ovaj string sa WHERE klauzulom je neophodan prilikom izvrsavanja SELECT upita, kako bi se
     * iz baze dobili redovi koji zadovoljavaju postavljeni uslov. 
     * 
     * @return WHERE klauzula kao String. 
     */
    public abstract String uslov();
}
