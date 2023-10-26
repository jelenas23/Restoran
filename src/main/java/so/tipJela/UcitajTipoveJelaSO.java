package so.tipJela;

import java.util.ArrayList;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.TipJela;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za ucitavanje svih tipova jela iz baze
 * podataka.
 * 
 * @author Jelena
 *
 */
public class UcitajTipoveJelaSO extends OpstaSistemskaOperacija {
	/**
	 * Lista sa svim tipovima jela.
	 */
	private ArrayList<TipJela> lista;

	/**
	 * Proverava da li je poslati objekat klase TipJela.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase TipJela.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof TipJela)) {
			throw new Exception("Dati objekat nije instanca klase TipJela.");
		}
	}

	/**
	 * Poziva DbBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, gde se
	 * kao rezultat dobija lista svih tipova jela.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> tipovi = DbBroker.getInstance().select(odo);
		lista = (ArrayList<TipJela>) (ArrayList<?>) tipovi;
	}

	/**
	 * Vraca listu sa svim tipovima jela.
	 * 
	 * @return lista svih tipova jela.
	 */
	public ArrayList<TipJela> getLista() {
		return lista;
	}

}
