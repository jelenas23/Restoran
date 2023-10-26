package so.recenzija;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za brisanje odredjene recenzije iz baze
 * podataka.
 * 
 * @author Jelena
 *
 */
public class ObrisiRecenzijuSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase Recenzija. .
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
	 * Vrsi brisanje prosledjene recenzije u bazi podataka, tako sto poziva
	 * DbBrokera da izvrsi DELETE upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().delete(odo);

	}

}
