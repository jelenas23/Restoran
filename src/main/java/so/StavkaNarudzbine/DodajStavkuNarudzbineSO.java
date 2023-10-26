package so.StavkaNarudzbine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Recenzija;
import domen.StavkaNarudzbine;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za dodavanje nove stavke narudzbine.
 * 
 * @author Jelena
 *
 */
public class DodajStavkuNarudzbineSO extends OpstaSistemskaOperacija {
	/**
	 * Proverava da li su podaci validni, tj. da li je objekat klase
	 * StavkaNarudzbine.
	 *
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase StavkaNarudzbine.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof StavkaNarudzbine)) {
			throw new Exception("Dati objekat nije instanca klase StavkaNarudzbine.");
		}

		StavkaNarudzbine s = (StavkaNarudzbine) odo;

	}

	/**
	 * Izvrsava dodavanje nove stavke, tako sto poziva DbBrokera da izvrsi INSERT
	 * upit.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
		DbBroker.getInstance().insert(odo);
	}
}
