package so.StavkaNarudzbine;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;
import so.OpstaSistemskaOperacija;

/**
 * Odnosi se na sistemsku operaciju za brisanje odredjene stavke narudzbine iz
 * baze podataka.
 * 
 * @author Jelena
 *
 */
public class ObrisiStavkuNarudzbineSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase
	 * StavkaNarudzbine.
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase StavkaNarudzbine.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof StavkaNarudzbine)) {
			throw new Exception("Dati objekat nije instanca klase StavkaNarudzbine.");
		}

	}

	/**
	 * Vrsi brisanje prosledjene stavke narudzbine u bazi podataka, tako sto poziva
	 * DbBrokera da izvrsi DELETE upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().delete(odo);

	}

}
