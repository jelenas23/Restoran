package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Ova klasa predstavlja restoran u kome se porucuje hrana.
 * Ima svoj id,naziv i adresu na kojoj se nalazi.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Restoran extends OpstiDomenskiObjekat{
	/**
	 * ID restorana kao int.
	 */
	private transient int restoranID;
	/**
	 * Naziv restorana kao String.
	 */
	private String nazivRestorana;
	/**
	 * Adresa restorana kao String.
	 */
	private String adresa;
	
	/**
	 * Neparametrizovani konstruktor koji inicijalizuje novi restoran.
	 */
	public Restoran() {
		
	}
	
	 
    /**
     * Parametrizovani konstrukor koji kreira novi restoran i postavlja mu zadate vrednosti.
     * 
     * @param restoranID      vrednost za ID restorana tipa int.
     * @param nazivRestorana  vrednost za naziv restorana tipa String.
     * @param adresa          vrednost za adresu restorana tipa String.
     */
	public Restoran(int restoranID, String nazivRestorana, String adresa) {
		
		setRestoranID(restoranID);
		setNazivRestorana(nazivRestorana);
		setAdresa(adresa);
	
	}


	
	/**
     * Vraca ID restorana.
     * 
     * @return ID restorana kao int.
     */
	public int getRestoranID() {
		return restoranID;
	}


	/**
	 * Postavlja ID restorana.
	 * 
	 * @param restoranID kao vrednost za ID restorana.
	 */
	public void setRestoranID(int restoranID) {
		this.restoranID = restoranID;
	}


	/**
     * Vraca naziv restorana.
     * 
     * @return nazivRestorana  kao String.
     */
	public String getNazivRestorana() {
		return nazivRestorana;
	}


	/**
	 * Postavlja naziv restorana.
	 * 
	 * @param nazivRestorana kao vrednost za naziv restorana.
	 * 
	 * @throws NullPointerException ako je uneti naziv null.
	 * @throws IllegalArgumentException ako je naziv restorana prazan string.
	 */
	public void setNazivRestorana(String nazivRestorana) {
		if(nazivRestorana==null) throw new NullPointerException();
		if(nazivRestorana.equals("")) throw new IllegalArgumentException();
		this.nazivRestorana=nazivRestorana;
	}


	/**
     * Vraca adresu restorana.
     * 
     * @return adresu kao String.
     */
	public String getAdresa() {
		return adresa;
	}


	/**
	 * Postavlja adresu restorana.
	 * 
	 * @param adresa kao vrednost za adresu restorana.
	 * 
	 * @throws NullPointerException ako je uneta adresa null.
	 * @throws IllegalArgumentException ako je adresa restorana prazan string ili je duzina adrese manja od osam karaktera.
	 */
	public void setAdresa(String adresa) {
		if(adresa==null) throw new NullPointerException();
		if(adresa.length()<8 || adresa.equals("")) throw new IllegalArgumentException();
		this.adresa = adresa;
	}



	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " restoran ";
	}

	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " r ";
	}

	@Override
	public String join() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Restoran  r = new Restoran(rs.getInt("restoranID"), rs.getString("naziv"),
					rs.getString("adresa"));

			lista.add(r);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		
		return " (naziv, adresa) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return " restoranID = " + restoranID;
	}

	@Override
	public String vrednostiZaInsert() {
		// TODO Auto-generated method stub
		return "'" + nazivRestorana + "', '" + adresa + "'";
	}

	@Override
	public String vrednostiZaUpdate() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return "";
	}

}
