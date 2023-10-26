package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Ova klasa predstavlja tip kojeg neko jelo moze biti.
 * Ima svoj id i naziv.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class TipJela extends OpstiDomenskiObjekat{

	/**
	 * ID tipa jela kao int.
	 */
	private int tipJelaID;
	/**
	 * Naziv tipa jela kao String.
	 */
	private String nazivTipaJela;
	/**
	 * Neparametrizovani konstruktor koji inicijalizuje novi objekat klase TipJela.
	 */
	public TipJela() {
		
	}
	/**
	 * Parametrizovani konstruktor koji kreira novi tip jela i postavlja mu zadate vrednosti.
	 * 
	 * @param tipJelaID     vrednost za ID tipa jela kao int.
	 * @param nazivTipaJela vrednost za naziv tipa jela kao String.
	 */
	public TipJela(int tipJelaID, String nazivTipaJela) {
		setTipJelaID(tipJelaID);
		setNazivTipaJela(nazivTipaJela);
	}
	/**
     * Vraca ID tipa jela.
     * 
     * @return ID tipa jela kao int.
     */
	public int getTipJelaID() {
		return tipJelaID;
	}
	/**
	 * Postavlja ID tipa jela.
	 * 
	 * @param tipJelaID kao vrednost za ID tipa jela.
	 */
	public void setTipJelaID(int tipJelaID) {
		this.tipJelaID = tipJelaID;
	}
	/**
	 * Vraca naziv tipa jela.
	 * 
	 * @return nazivTipaJela kao String.
	 */
	public String getNazivTipaJela() {
		return nazivTipaJela;
	}
	/**
	 * Postavlja naziv tipa jela.
	 * 
	 * @param nazivTipaJela kao vrednost za naziv tipa jela.
	 * 
	 * @throws NullPointerException ako je uneti naziv null.
	 * @throws IllegalArgumentException ako je naziv tipa jela prazan string.
	 */
	public void setNazivTipaJela(String nazivTipaJela) {
		if(nazivTipaJela==null)throw new NullPointerException();
		if(nazivTipaJela.equals("")) throw new IllegalArgumentException();
		this.nazivTipaJela = nazivTipaJela;
	}
	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " tipjela";
	}
	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " tj ";
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
			TipJela tj  = new TipJela(rs.getInt("tipJelaID"), rs.getString("nazivTipaJela"));

			lista.add(tj);
		}

		rs.close();
		return lista;
	}
	@Override
	public String koloneZaInsert() {
		
		return " (nazivTipaJela) ";
	}
	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return " tipJelaID = " + tipJelaID;
	}
	@Override
	public String vrednostiZaInsert() {
		
		return  "'" + nazivTipaJela + "'";
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
