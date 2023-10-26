package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
/**
 * Ova klasa predstavlja narudzbinu u restoranu.
 * Ima svoj id,datum i konobara koji je kreirao datu narudzbinu.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Narudzbina extends OpstiDomenskiObjekat{

	/**
	 * ID narudzbine kao int.
	 */
	private transient int narudzbinaID;
	/**
	 * Datum kreiranja narudzbine kao Date.
	 */
	private Date datumNarudzbine;
	/**
	 * Konobar koji je kreirao narudzbinu kao Konobar.
	 */
	private Konobar konobar;
	/**
     * Neparametrizovani konstruktor klase Narudzbina koji inicijalizuje novu narudzbinu.
     */
	public Narudzbina() {
	
	}
	/**
	 * Parametrizovani konstrukor koji kreira novu narudzbinu  i postavlja joj zadate vrednosti
	 * 
	 * @param narudzbinaID      vrednost za ID narudzbine tipa int.
	 * @param datumNarudzbine   vrednost za datum narudzbine tipa Date.
	 * @param konobar           vrednost za konobara narudzbine tipa Konobar.
	 */
	public Narudzbina(int narudzbinaID, Date datumNarudzbine, Konobar konobar) {
		setNarudzbinaID(narudzbinaID);
		setDatumNarudzbine(datumNarudzbine);
		setKonobar(konobar);
	}
	/**
	 * Vraca ID narudzbine.
	 * 
	 * @return ID narudzbine kao int.
	 */
	public int getNarudzbinaID() {
		return narudzbinaID;
	}
	/**
     * Postavlja ID narudzbine.
     * 
     * @param narudzbinaID kao  vrednost za ID narudzbine.
     */
	public void setNarudzbinaID(int narudzbinaID) {
		this.narudzbinaID = narudzbinaID;
	}
	/**
	 * Vraca datum i vreme kada je kreirana narudzbina.
	 * 
	 * @return datum kreiranja narudzbine kao Date.
	 */
	public Date getDatumNarudzbine() {
		return datumNarudzbine;
	}
	/**
	 * Postavlja datum narudzbine.
	 * 
	 * @param datumNarudzbine kao vrednost za datum narudzbine.
	 * 
	 * @throws NullPointerException ako je uneti datum null.
	 * @throws IllegalArgumentException ako je uneti datum nakon trenutnog datuma.
	 */
	public void setDatumNarudzbine(Date datumNarudzbine) {
		if(datumNarudzbine==null)throw new NullPointerException();
		if(datumNarudzbine.after(new Date())) throw new IllegalArgumentException();
		this.datumNarudzbine = datumNarudzbine;
	}
	/**
	 * Vraca konobara koji je kreirao narudzbinu.
	 * 
	 * @return konobar kao Konobar.
	 */
	public Konobar getKonobar() {
		return konobar;
	}
	/**
	 * Postavlja vrednost konobara koji je kreirao narudzbinu.
	 * 
	 * @param konobar kao vrednost za Konobar.
	 */
	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}
	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " narudzbina ";
	}
	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " n ";
	}
	@Override
	public String join() {
		
		return  " JOIN KONOBAR K ON N.IDKONOBARA=K.KONOBARID "
				+ "JOIN RESTORAN R ON (R.RESTORANID = K.IDRESTORANA) ";
	}
	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Restoran  r = new Restoran(rs.getInt("restoranID"), rs.getString("naziv"),
					rs.getString("adresa"));
			Konobar k = new Konobar(rs.getInt("konobarID"), rs.getString("imePrezime"),rs.getString("kontaktTelefon"), r ,rs.getString("username"),rs.getString("password"));
			
			Narudzbina n = new Narudzbina(rs.getInt("narudzbinaID"),rs.getDate("datumNarudzbine"),k);

			lista.add(n);
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
		return " narudzbinaID = " + narudzbinaID;
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
