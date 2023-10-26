package so.recenzija;

import java.util.ArrayList;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import so.OpstaSistemskaOperacija;

/**
 * Sistemska operacija za izmenu podataka date recenzije u bazi podataka.
 * 
 * @author Jelena
 *
 */
public class IzmeniRecenzijuSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Recenzija.
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase Recenzija.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Recenzija)) {
			throw new Exception("Dati objekat nije instanca klase Recenzija.");
		}

	}

	/**
	 * Vrsi izmenu podataka o recenziji, tako sto poziva DbBrokera da izvrsi UPDATE
	 * upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().update(odo);
	}
}
