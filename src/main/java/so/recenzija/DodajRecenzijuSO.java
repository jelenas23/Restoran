package so.recenzija;

import java.util.ArrayList;

import db.DbBroker;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za dodavanje nove recenzije.
 * 
 * @author Jelena
 *
 */
public class DodajRecenzijuSO extends OpstaSistemskaOperacija {

	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Recenzija.
	 *
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
	 * Izvrsava dodavanje nove recenzije, tako sto poziva DbBrokera da izvrsi INSERT
	 * upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().insert(odo);
	}

}
