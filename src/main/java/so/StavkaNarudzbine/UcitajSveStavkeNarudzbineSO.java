package so.StavkaNarudzbine;

import java.util.ArrayList;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;
import domen.TipJela;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za ucitavanje svih stavki narudzbine iz baze
 * podataka.
 * 
 * @author Jelena
 *
 */
public class UcitajSveStavkeNarudzbineSO extends OpstaSistemskaOperacija {
	/**
	 * Lista sa svim stavkama narudzbine.
	 */
	private ArrayList<StavkaNarudzbine> lista;

	/**
	 * Proverava da li je poslati objekat klase StavkaNarudzbine.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase
	 *                   StavkaNarudzbine.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof StavkaNarudzbine)) {
			throw new Exception("Dati objekat nije instanca klase StavkaNarudzbine.");
		}
	}

	/**
	 * Poziva DbBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, gde se
	 * kao rezultat dobija lista sa svim stavkama narudzbine.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> stavke = DbBroker.getInstance().select(odo);
		lista = (ArrayList<StavkaNarudzbine>) (ArrayList<?>) stavke;
	}

	/**
	 * Vraca listu sa svim stavkama narudzbine.
	 * 
	 * @return lista sa svim stavkama narudzbine.
	 */
	public ArrayList<StavkaNarudzbine> getLista() {
		return lista;
	}
}
