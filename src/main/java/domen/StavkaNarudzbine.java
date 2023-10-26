package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * StavkaNarudzbine predstavlja sve stavke koje se nalaze u jednoj narudzbini.
 * Stavka ima svoj id,kolicinu,napomenu,jelo i narudzbinu.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class StavkaNarudzbine extends OpstiDomenskiObjekat{

	/**
	 * ID stavke narudzbine kao int.
	 */
	private int stavkaNarudzbineID;
	/**
	 * Kolicina neke stavke kao int.
	 */
	private int kolicina;
	/**
	 * Napomena za neku stavku kao String.
	 */
	private String napomena;
	/**
	 * Jelo koje predstavlja stavku narudzbine kao Jelo.
	 */
	private Jelo jelo;
	/**
	 * Narudzbina za koju se neka stavka vezuje kao Narudzbina.
	 */
	private Narudzbina narudzbina;
	
	/**
	 * Neparametrizovani konstruktor klase StavkaNarudzbine koji inicijalizuje novu stavku narudzbine.
	 */
	public StavkaNarudzbine() {
		
	}
	
	/**
	 * Parametrizovani konstrukor koji kreira novu stavku i postavlja joj zadate
	 * vrednosti
	 *   
	 * @param stavkaNarudzbineID    vrednost za ID stavke kao int.
	 * @param kolicina              vrednost za kolicinu stavke kao int.
	 * @param napomena              vrednost za napomenu stavke kao String.
	 * @param jelo                  vrednost za jelo na koje se odnosi stavka kao Jelo.
	 * @param narudzbina            vrednost za narudzbinu za koju se stavka vezuje kao Narudzbina.
	 */
	public StavkaNarudzbine(int stavkaNarudzbineID, int kolicina, String napomena, Jelo jelo, Narudzbina narudzbina) {
		super();
		this.stavkaNarudzbineID = stavkaNarudzbineID;
		this.kolicina = kolicina;
		this.napomena = napomena;
		this.jelo = jelo;
		this.narudzbina = narudzbina;
	}
	
	/**
	 * Vraca ID stavke narudzbine.
	 * 
	 * @return ID stavke narudzbine kao int.
	 */
	public int getStavkaNarudzbineID() {
		return stavkaNarudzbineID;
	}
	/**
	 * Postavlja ID stavke narudzbine.
	 * 
	 * @param stavkaNarudzbineID kao vrednost za ID stavke narudzbine.
	 */
	public void setStavkaNarudzbineID(int stavkaNarudzbineID) {
		this.stavkaNarudzbineID = stavkaNarudzbineID;
	}

	/**
	 * Vraca kolicinu.
	 * 
	 * @return kolicina kao int.
	 */
	public int getKolicina() {
		return kolicina;
	}
	/**
	 * Postavlja kolicinu stavke.
	 * 
	 * @param kolicina kao vrednost za kolicinu.
	 * @throws IllegalArgumentException ako je uneta kolicina manja od nule.
	 */
	public void setKolicina(int kolicina) {
		if(kolicina<0) throw new IllegalArgumentException();
		this.kolicina = kolicina;
	}
	/**
	 * Vraca napomenu.
	 * 
	 * @return napomena kao String.
	 */
	public String getNapomena() {
		return napomena;
	}
	/**
	 * Postavlja vrednost napomene.
	 * 
	 * @param napomena kao vrednost za napomenu.
	 */
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	/**
	 * Vraca jelo za koje se stavka vezuje.
	 * 
	 * @return jelo kao Jelo.
	 */
	public Jelo getJelo() {
		return jelo;
	}
	/**
	 * Postavlja vrednost jela na koje se stavka odnosi.
	 * 
	 * @param jelo kao vrednost tipa Jelo.
	 */
	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}
	/**
	 * Vraca narudzbinu za koju se stavka vezuje.
	 * 
	 * @return narudzbina kao Narudzbina.
	 */
	public Narudzbina getNarudzbina() {
		return narudzbina;
	}
	/**
	 * Postavlja vrednost narudzbineza koju se stavka vezuje.
	 * 
	 * @param narudzbina kao vrednost tipa Narudzbina.
	 */
	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}
	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " stavkanarudzbine ";
	}
	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " s ";
	}
	@Override
	public String join() {
		
		return  " JOIN NARUDZBINA N ON s.idnarudzbine=n.narudzbinaid "
				+"JOIN JELO J ON (S.IDJELA = J.JELOID) "
				+"JOIN KONOBAR K  ON (N.IDKONOBARA = K.KONOBARID)"
				+ " JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) "
				+ "JOIN TIPJELA TJ ON (J.IDTIPAJELA = TJ.TIPJELAID)";
				
		
	}
	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Restoran  r = new Restoran(rs.getInt("restoranID"), rs.getString("naziv"),
					rs.getString("adresa"));
			Konobar k = new Konobar(rs.getInt("konobarID"), rs.getString("imePrezime"),rs.getString("kontaktTelefon"), r ,rs.getString("username"),rs.getString("password"));
			
			Narudzbina n = new Narudzbina(rs.getInt("narudzbinaID"),rs.getDate("datumNarudzbine"),k);


			TipJela tj = new TipJela(rs.getInt("idTipaJela"),rs.getString("nazivTipaJela"));
				
			
			Jelo j = new Jelo(rs.getInt("jeloID"), rs.getString("nazivJela"),
					rs.getInt("cenaJela"),r,tj);
			StavkaNarudzbine s = new StavkaNarudzbine(rs.getInt("stavkaNarudzbineID"),rs.getInt("kolicina"), rs.getString("napomena"), j, n);
			
			lista.add(s);
			
		}
		rs.close();
		return lista;
	}
	@Override
	public String koloneZaInsert() {
		// TODO Auto-generated method stub
		return " (kolicina, napomena, idJela, idNarudzbine) ";
	}
	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return " stavkaNarudzbineID= "+stavkaNarudzbineID;
	}
	@Override
	public String vrednostiZaInsert() {
		// TODO Auto-generated method stub
		return  kolicina + ",'" + napomena + "',"+jelo.getJeloID()+" ,"+narudzbina.getNarudzbinaID();
	}
	@Override
	public String vrednostiZaUpdate() {
		// TODO Auto-generated method stub
		return " kolicina = " + kolicina+ ", napomena = '" + napomena+ "' ";
	}
	@Override
	public String uslov() {
		
		return "";
				//" WHERE S.IDNARUDZBINE= " + narudzbina.getNarudzbinaID();
	}
	
	
}
