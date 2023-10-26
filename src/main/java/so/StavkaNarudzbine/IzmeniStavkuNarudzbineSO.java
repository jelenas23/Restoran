package so.StavkaNarudzbine;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import domen.StavkaNarudzbine;
import so.OpstaSistemskaOperacija;

/**
 * Sistemska operacija za izmenu podataka date stavke narudzbine u bazi
 * podataka.
 * 
 * @author Jelena
 *
 */
public class IzmeniStavkuNarudzbineSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase
	 * StavkaNarudzbine.
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase Recenzija.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof StavkaNarudzbine)) {
			throw new Exception("Dati objekat nije instanca klase StavkaNarudzbine.");
		}

	}

	/**
	 * Vrsi izmenu podataka stavke narudzbine, tako sto poziva DbBrokera da izvrsi
	 * UPDATE upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().update(odo);

	}

}
