package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Konobar predstavlja administratora sistema.
 * Konobar ima id, ime i prezime, kontakt telefon, restoran u kome je zaposlen, username i password
 * pomocu kojih se loguje na sistem.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Konobar extends OpstiDomenskiObjekat{
	
    /**
     * ID konobara kao int.
     */
	private transient int konobarID;
	/**
	 * ime i prezime konobara kao String.
	 */
	private String imePrezime;
	/**
	 * kontakt telefon konobara kao String.
	 */
	private String kontaktTelefon;
    /**
     * restoran u kome konobar radi kao Restoran.
     */
	private Restoran restoran;
	/**
	 * username konobara kao String.
	 */
	private transient String username;
	/**
	 * password konobara kao String.
	 */
	private transient String password;

	/**
	 * Neparametrizovani konstruktor koji inicijalizuje novog konobara.
	 */
	public Konobar() {
		
	}

	
	


	/**
	 * Parametrizovani konstrukor koji kreira novog korisnika i postavlja mu zadate vrednosti.
	 * 
	 * @param konobarID       vrednost za ID konobara tipa int.
	 * @param imePrezime      vrednost za ime i prezime konobara tipa String.
	 * @param kontaktTelefon  vrednost za kontakt telefon  konobara tipa String.
	 * @param restoran        vrednost za restoran konobara tipa Restoran.
	 * @param username        vrednost za username  konoabra tipa String.
	 * @param password        vrednost za password konobara tipa String.
	 */
	public Konobar(int konobarID, String imePrezime, String kontaktTelefon, Restoran restoran, String username,
			String password) {
	
		setKonobarID(konobarID);
		setImePrezime(imePrezime);
		setKontaktTelefon(kontaktTelefon);
		setRestoran(restoran);
		setUsername(username);
		setPassword(password);
		
	}


	/**
     * Vraca ID konobara.
     * 
     * @return ID konobara kao int.
     */
	public int getKonobarID() {
		return konobarID;
	}

	/**
	 * Postavlja ID konobara.
	 * 
	 * @param konobarID kao vrednost za ID konobara.
	 */
	public void setKonobarID(int konobarID) {
		this.konobarID = konobarID;
	}

	/**
     * Vraca ime i prezime konobara.
     * 
     * @return imePrezime konobara kao String.
     */
	public String getImePrezime() {
		return imePrezime;
	}

	/**
	 * Postavlja ime i prezime konobara.
	 * 
	 * @param imePrezime kao vrednost za ime i prezime konobara.
	 * 
	 * @throws NullPointerException ako je uneto imePrezime null.
	 * @throws IllegalArgumentException ako je duzina unetog imena i prezimena manja od sedam karaktera ili ako je prazan string.
	 */
	public void setImePrezime(String imePrezime) {
		if(imePrezime==null) throw new NullPointerException();
		if(imePrezime.length()<7 || imePrezime.equals("")) throw new IllegalArgumentException();
		this.imePrezime = imePrezime;
	}

	/**
	 * Vraca kontakt telefon konobara.
	 * 
	 * @return kontaktTelefon konobara kao String.
	 */
	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	/**
	 * Postavlja kontakt telefon konobara.
	 * 
	 * @param kontaktTelefon kao vrednost za kontakt telefon konobara.
	 * 
	 * @throws NullPointerException ako je uneti kontakt telefon null.
	 * @throws IllegalArgumentException ako je duzina unetog kontkat telefona manja od sest karaktera ili ako je prazan string.
	 */
	public void setKontaktTelefon(String kontaktTelefon) {
		if(kontaktTelefon == null) throw new NullPointerException();
		if(kontaktTelefon.length() < 6 || kontaktTelefon.equals("")) throw new IllegalArgumentException();
		this.kontaktTelefon = kontaktTelefon;
	}


	/**
     * Vraca restoran u kome konobar radi.
     * 
     * @return restoran kao Restoran.
     */
	public Restoran getRestoran() {
		return restoran;
	}

	/**
     * Postavlja restoran u kome konobar radi.
     * 
     * @param restoran kao vrednost za atribut klase Restoran.
     */
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}


	/**
	 * Vraca username korisnika sistema, koji mu je potreban za logovanje na sistem.
	 * 
	 * @return username konobara kao String.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Postavlja username korisnika sistema, koji mu je potreban za logovanje na sistem.
	 * 
	 * @param username kao vrednost za username konobara.
	 * 
	 * @throws NullPointerException ako je uneti username null.
	 * @throws IllegalArgumentException ako je duzina unetog username-a manja od cetiri karaktera ili ako je prazan string.
	 * 
	 */
	public void setUsername(String username) {
		if(username==null) throw new NullPointerException();
        if(username.length()<4 || username.equals("")) throw new IllegalArgumentException();
        this.username = username;
	}

	/**
	 * Vraca password korisnika sistema, koji mu je potreban za logovanje na sistem.
	 * 
	 * @return password konobara kao String.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Postavlja password korisnika sistema, koji mu je potreban za logovanje na sistem.
	 * 
	 * @param password kao vrednost za password konobara.
	 * 
	 * @throws NullPointerException ako je uneti password null.
	 * @throws IllegalArgumentException ako je duzina unetog password-a manja od osam karaktera ili ako je prazan string.
	 * 
	 */
	public void setPassword(String password) {
		if(password==null) throw new NullPointerException();
        if(password.length()<8 || password.equals("")) throw new IllegalArgumentException();
        this.password = password;
	}

	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " konobar ";
	}

	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " k ";
	}

	@Override
	public String join() {
		// TODO Auto-generated method stub
		return " JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID)";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			
			Restoran r = new Restoran(rs.getInt("idRestorana"),rs.getString("naziv"),rs.getString("adresa"));
			
			Konobar k  = new Konobar(rs.getInt("konobarID"), rs.getString("imePrezime"), rs.getString("kontaktTelefon"),r,rs.getString("username"),rs.getString("password"));

			lista.add(k);
			System.out.println(lista.size());
		}
        
		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		// TODO Auto-generated method stub
		return " (imePrezime, kontaktTelefon, idRestorana) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return " kononbarID = " + konobarID;
	}

	@Override
	public String vrednostiZaInsert() {
		// TODO Auto-generated method stub
		return "";
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
