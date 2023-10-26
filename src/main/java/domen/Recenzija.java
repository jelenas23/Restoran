package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Ova klasa predstavlja recenziju koja se vezuje za datu narudzbinu. 
 * Ima svoj id, ocenu, komentar i narudzbinu na koju se odnosi.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira sve njene apstraktne metode.
 * 
 * @author Jelena
 */
public class Recenzija extends OpstiDomenskiObjekat{

	/**
	 * ID recenzije kao int.
	 */
	private transient int recenzijaID;
	/**
	 * Ocena recenzije kao int.
	 */
	private int ocena;
	/**
	 * Komentar na narudzbinu kao String.
	 */
	private String komentar;
	/**
	 * Narudzbina na koju se recenzija odnosi kao Narudzbina.
	 */
	private Narudzbina narudzbina;
	
	/**
	 * Neparametrizovani konstruktor klase Recenzija koji inicijalizuje novu Recenziju.
	 */
	public Recenzija() {
		
	}

	/**
	 * Parametrizovani konstrukor koji kreira novu recenziju i postavlja joj zadate
	 * vrednosti
	 * 
	 * @param recenzijaID   vrednost za ID recenzije tipa int.
	 * @param ocena         vrednost za ocenu recenzije tipa int.
	 * @param komentar      vrednost za komentar  recenzije tipa String.
	 * @param narudzbina    vrednost za narudzbinu na koju se recenzija odnosi tipa Narudzbina.
	 */
	public Recenzija(int recenzijaID, int ocena, String komentar, Narudzbina narudzbina) {
		setRecenzijaID(recenzijaID);
		setOcena(ocena);
		setKomentar(komentar);;
		setNarudzbina(narudzbina);
	}

	/**
	 * Vraca ID recenzije.
	 * 
	 * @return ID recenzije kao int.
	 */
	public int getRecenzijaID() {
		return recenzijaID;
	}

	/**
	 * Postavlja ID recenzije.
	 * 
	 * @param recenzijaID kao vrednost za ID recenzije.
	 */
	public void setRecenzijaID(int recenzijaID) {
		this.recenzijaID = recenzijaID;
	}

	/**
	 * Vraca ocenu za datu recenziju.
	 * 
	 * @return ocena kao int.
	 */
	public int getOcena() {
		return ocena;
	}

	/**
	 * Postavlja ocenu za datu recenziju.
	 * 
	 * @param ocena kao vrednost za ocenu.
	 * @throws IllegalArgumentException ako je uneta ocena manji od jedan ili veca od deset.
	 */
	public void setOcena(int ocena) {
		if(ocena<1 || ocena>10) throw new IllegalArgumentException();
		this.ocena = ocena;
	}

	/**
	 * Vraca komentar.
	 * 
	 * @return komentar kao String.
	 */
	public String getKomentar() {
		return komentar;
	}

	/**
	 * Postavlja komentar za narudzbinu.
	 * 
	 * @param komentar kao vrednost za komentar.
	 * 
	 */
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	/**
	 *  Vraca narudzbinu na koju se recenzija odnosi.
	 *  
	 * @return narudzbina kao Narudzbina.
	 */
	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	/**
	 * Postavlja vrednost narudzbine na koju se recenzija  odnosi.
	 * @param narudzbina kao vrednost tipa Narudzbina.
	 */
	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	@Override
	public String nazivTabele() {
		// TODO Auto-generated method stub
		return " recenzija ";
	}

	@Override
	public String alijas() {
		// TODO Auto-generated method stub
		return " re ";
	}

	@Override
	public String join() {
		// TODO Auto-generated method stub
		return  " JOIN NARUDZBINA N ON RE.IDNARUDZBINE=N.NARUDZBINAID "
		+ "JOIN KONOBAR K ON (N.IDKONOBARA = K.KONOBARID) "
		+ "JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) ";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Restoran  r = new Restoran(rs.getInt("restoranID"), rs.getString("naziv"),
					rs.getString("adresa"));
			Konobar k = new Konobar(rs.getInt("konobarID"), rs.getString("imePrezime"),rs.getString("kontaktTelefon"), r ,rs.getString("username"),rs.getString("password"));
			
			Narudzbina n = new Narudzbina(rs.getInt("narudzbinaID"),rs.getDate("datumNarudzbine"),k);

			Recenzija  re= new Recenzija(rs.getInt("recenzijaID"),rs.getInt("ocena"), rs.getString("komentar"), n);
			
			lista.add(re);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		// TODO Auto-generated method stub
		return " (ocena, komentar,idNarudzbine) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		// TODO Auto-generated method stub
		return" recenzijaID = " + recenzijaID;
	}

	@Override
	public String vrednostiZaInsert() {
		// TODO Auto-generated method stub
		return "'"+ocena+"','"+komentar+"',"+narudzbina.getNarudzbinaID();
	}

	@Override
	public String vrednostiZaUpdate() {
		// TODO Auto-generated method stub
		return " ocena = '" + ocena+ "', komentar= '" + komentar + "' ";
	}

	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return "";
	}
	
	
	
}
