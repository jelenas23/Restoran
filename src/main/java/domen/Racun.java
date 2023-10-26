package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Ova klasa predstavlja racun koji se placa u restoranu. Ima svoj id,nacin
 * placanja,iznos za placanje i narudzbinu na koju se odnosi.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Racun extends OpstiDomenskiObjekat {

	/**
	 * ID racuna kao int.
	 */
	private int racunID;
	/**
	 * Nacin placanja kao String.
	 */
	private String nacinPlacanja;
	/**
	 * Iznos za placanje kao int.
	 */
	private int iznos;
	/**
	 * Narudzbina na koju se odnosi racun kao Narudzbina.
	 */
	private Narudzbina narudzbina;

	/**
	 * Neparametrizovani konstruktor klase Racun koji inicijalizuje novi Racun.
	 */
	public Racun() {

	}

	/**
	 * Parametrizovani konstrukor koji kreira novi racun i postavlja mu zadate
	 * vrednosti
	 * 
	 * @param racunID       vrednost za ID racuna tipa int.
	 * @param nacinPlacanja vrednost za nacin placanja racuna tipa String.
	 * @param iznos         vrednost za iznos racuna tipa int.
	 * @param narudzbina    vrednost za narudzbinu na koju se odnosi racun tipa
	 *                      Narudzbina.
	 */
	public Racun(int racunID, String nacinPlacanja, int iznos, Narudzbina narudzbina) {
		setRacunID(racunID);
		setNacinPlacanja(nacinPlacanja);
		setIznos(iznos);
		setNarudzbina(narudzbina);
	}

	/**
	 * Vraca ID racuna.
	 * 
	 * @return ID racuna kao int.
	 */
	public int getRacunID() {
		return racunID;
	}

	/**
	 * Postavlja ID racuna.
	 * 
	 * @param racunID kao vrednost za ID racuna.
	 */
	public void setRacunID(int racunID) {
		this.racunID = racunID;
	}

	/**
	 * Vraca nacin placanja racuna.
	 * 
	 * @return nacin placanja kao String.
	 */
	public String getNacinPlacanja() {
		return nacinPlacanja;
	}

	/**
	 * Postavlja nacin placanja racuna.
	 * 
	 * @param nacinPlacanja kao vrednost za nacin placanja.
	 * 
	 * @throws NullPointerException     ako je unet nacin placanja racuna null.
	 * @throws IllegalArgumentException ako je nacin placanja prazan string.
	 */
	public void setNacinPlacanja(String nacinPlacanja) {
		if (nacinPlacanja == null)
			throw new NullPointerException();
		if (nacinPlacanja.equals(""))
			throw new IllegalArgumentException();
		this.nacinPlacanja = nacinPlacanja;
	}

	/**
	 * Vraca iznos datog racuna.
	 * 
	 * @return iznos kao int.
	 */
	public int getIznos() {
		return iznos;
	}

	/**
	 * Postavlja iznos racuna.
	 * 
	 * @param iznos kao vrednost za iznos racuna.
	 * @throws IllegalArgumentException ako je uneti iznos manji od nule.
	 */
	public void setIznos(int iznos) {
		if (iznos < 0)
			throw new IllegalArgumentException();
		this.iznos = iznos;
	}

	/**
	 * Vraca narudzbinu na koju se racun odnosi.
	 * 
	 * @return narudzbina kao Narudzbina.
	 */
	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	/**
	 * Postavlja vrednost narudzbine na koju se racun odnosi.
	 * 
	 * @param narudzbina kao vrednost tipa Narudzbina.
	 */
	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " racun ";
	}

	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " ra ";
	}

	@Override
	public String join() {
		// TODO Auto-generated method stub
		return " JOIN NARUDZBINA N (IDNARUDZBINE) " + "JOIN KONOBAR K ON (N.IDKONOBARA = K.KONOBARID) "
				+ "JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) ";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Restoran r = new Restoran(rs.getInt("restoranID"), rs.getString("naziv"), rs.getString("adresa"));
			Konobar k = new Konobar(rs.getInt("konobarID"), rs.getString("imePrezime"), rs.getString("kontaktTelefon"),
					r, rs.getString("username"), rs.getString("password"));

			Narudzbina n = new Narudzbina(rs.getInt("narudzbinaID"), rs.getDate("datumNarudzbine"), k);

			Racun ra = new Racun(rs.getInt("racunID"), rs.getString("nacinPlacanja"), rs.getInt("iznos"), n);
			lista.add(ra);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return " racunID = " + racunID;
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
