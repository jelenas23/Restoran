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
	 * Proverava da li su podaci validni, tj. da li je objekat klase Recenzija i da li 
	 * u bazi vec postoji recenzija za datu narudzbinu.
	 * 
	 * @throws Exception ukoliko objekat nije instanca klase Recenzija.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Recenzija)) {
			throw new Exception("Dati objekat nije instanca klase Recenzija.");
		}

         Recenzija r = (Recenzija) odo;
		
		ArrayList<Recenzija> recenzije= (ArrayList<Recenzija>) (ArrayList<?>) DbBroker.getInstance().select(new Recenzija());

		for (Recenzija rec : recenzije) {
			if (r.getNarudzbina().getNarudzbinaID()== rec.getNarudzbina().getNarudzbinaID()) {
				throw new Exception("Vec postoji recenzija za datu narudzbinu.");
			}
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
