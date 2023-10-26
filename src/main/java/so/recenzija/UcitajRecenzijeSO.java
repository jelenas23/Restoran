package so.recenzija;

import java.util.ArrayList;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import domen.TipJela;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za ucitavanje svih recenzija iz baze
 * podataka.
 * 
 * @author Jelena
 *
 */
public class UcitajRecenzijeSO extends OpstaSistemskaOperacija {

	/**
	 * Lista sa svim recenzijama.
	 */
	private ArrayList<Recenzija> lista;

	/**
	 * Proverava da li je poslati objekat klase Recenzija.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Recenzija.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Recenzija)) {
			throw new Exception("Dati objekat nije instanca klase Recenzija.");
		}
	}

	/**
	 * Poziva DbBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, gde se
	 * kao rezultat dobija lista sa svim recenzijama.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> recenzije = DbBroker.getInstance().select(odo);
		lista = (ArrayList<Recenzija>) (ArrayList<?>) recenzije;
	}

	/**
	 * Vraca listu sa svim recenzijama.
	 * 
	 * @return lista sa svim recenzijama.
	 */
	public ArrayList<Recenzija> getLista() {
		return lista;
	}

}
