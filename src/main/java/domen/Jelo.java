package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Ova klasa predstavlja jela koja se sluze u restoranu.
 * Ima svoj id,naziv,cenu,restoran u kome se sluzi i tip kome to jelo pripada.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Jelo extends OpstiDomenskiObjekat{
	/**
	 * ID jela kao int.
	 */
	private int jeloID;
	/**
	 * Naziv jela kao String.
	 */
	private String nazivJela;
	/**
	 * Cena jela kao int.
	 */
	private int cenaJela;
	/**
	 * Restoran u kome se dato jelo sluzi kao Restoran.
	 */
	private Restoran restoran;
	/**
	 * Tip jela kome dato jelo pripada kao TipJela.
	 */
	private TipJela tipJela;
	
	
    /**
     * Neparametrizovani konstruktor klase Jelo koji inicijalizuje novo jelo.
     */
	public Jelo() {
		 
	}
	
    /**
     * Parametrizovani konstrukor koji kreira novo jelo i postavlja mu zadate vrednosti
     * 
     * @param jeloID     vrednost za ID jela tipa int.
     * @param nazivJela  vrednost za naziv jela tipa String.
     * @param cenaJela   vrednost za cenu jela tipa int.
     * @param restoran   vrednost za restoran tipa Restoran.
     * @param tipJela    vrednost za tip jela tipa TipJela.
     */
	public Jelo(int jeloID, String nazivJela, int cenaJela, Restoran restoran, TipJela tipJela) {
		setJeloID(jeloID);
		setNazivJela(nazivJela);
		setCenaJela(cenaJela);
		setRestoran(restoran);
		setTipJela(tipJela);
	}


	/**
	 * Vraca ID jela.
	 * 
	 * @return ID jela kao int.
	 */
	public int getJeloID() {
		return jeloID;
	}

    /**
     * Postavlja ID jela.
     * 
     * @param jeloID kao vrednost za ID jela.
     */
	public void setJeloID(int jeloID) {
		this.jeloID = jeloID;
	}

    /**
     * Vraca naziv jela.
     * 
     * @return naziv jela kao String.
     */
	public String getNazivJela() {
		return nazivJela;
	}

   /**
    * Postavlja naziv jela.
    * 
    * @param nazivJela kao vrednost za naziv jela.
    * 
    * @throws NullPointerException ako je unet naziv jela null.
    *  @throws IllegalArgumentException ako je naziv jela prazan string.
    */
	public void setNazivJela(String nazivJela) {
		if(nazivJela==null) throw new NullPointerException();
		if(nazivJela.equals("")) throw new IllegalArgumentException();
		this.nazivJela = nazivJela;
	}

   /**
    * Vraca cenu datog jela.
    * 
    * @return cena jela kao int.
    */
	public int getCenaJela() {
		return cenaJela;
	}

   /**
    * Postavlja cenu jela.
    * 
    * @param cenaJela koa vrednost za celu jela.
    * @throws IllegalArgumentException ako je uneta cena manja od nule.
    */
	public void setCenaJela(int cenaJela) {
		if(cenaJela<0) throw new IllegalArgumentException();
		this.cenaJela = cenaJela;
	}

    /**
     * Vraca restoran u kome se jelo sluzi.
     * 
     * @return restoran kao Restoran.
     */
	public Restoran getRestoran() {
		return restoran;
	}

    /**
     * Postavlja restoran u kome se jelo sluzi.
     * 
     * @param restoran kao vrednost za atribut klase Restoran.
     */
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

    /**
     * Vraca tip kome dato jelo pripada.
     * 
     * @return tipJela kao TipJela.
     */
	public TipJela getTipJela() {
		return tipJela;
	}

    /**
     * Postavlja tip kome jelo pripada.
     * 
     * @param tipJela kao vrednost za tip jela.
     */
	public void setTipJela(TipJela tipJela) {
		this.tipJela = tipJela;
	}


	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " jelo ";
	}

	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " j ";
	}

	@Override
	public String join() {
		// TODO Auto-generated method stub
		return  " JOIN TIPJELA TJ ON (J.IDTIPAJELA = TJ.TIPJELAID)";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			
			Restoran r = new Restoran(rs.getInt("idRestorana"),rs.getString("naziv"),rs.getString("adresa"));
			
			TipJela tj = new TipJela(rs.getInt("idTipaJela"),rs.getString("nazivTipaJela"));
				
			
			Jelo j = new Jelo(rs.getInt("jeloID"), rs.getString("nazivJela"),
					rs.getInt("cenaJela"),r,tj);

			lista.add(j);
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
		return " jeloID = " + jeloID;
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
