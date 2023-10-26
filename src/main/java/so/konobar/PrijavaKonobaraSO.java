package so.konobar;

import java.util.ArrayList;

import db.DbBroker;
import domen.Konobar;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * 
 * Odnosi se na sistemsku operaciju za prijavu korisnika(konobara) na sistem.
 * 
 * @author Jelena
 *
 */
public class PrijavaKonobaraSO extends OpstaSistemskaOperacija {

	/**
	 * Instanca klase Konobar koja se odnosi na ulogovanog konobara.
	 */
	private Konobar ulogovani;

	/**
	 * Proverava da li je poslati objekat klase Konobar.
	 * 
	 * @throws Exception ako prosledjeni objekat nije instanca klase Konobar.
	 */
	@Override
	protected void validacija(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Konobar)) {
			throw new Exception("Dati objekat nije instanca klase Konobar.");
		}
	}

	/**
	 * Poziva DbBrokera da izvrsi SELECT upit nad zeljenom tabelom u bazi, a zatim
	 * prolazi kroz listu svih konobara dobijenih iz baze i proverava da li postoji
	 * konobar sa unetim kredencijalima za logovanje na sistem.
	 * 
	 * @throws Exception ako ne postoji konobar sa unetim kredencijalima.
	 */
	@Override
	protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {

		Konobar k = (Konobar) odo;

		ArrayList<Konobar> konobari = (ArrayList<Konobar>) (ArrayList<?>) DbBroker.getInstance().select(k);

		for (Konobar konobar : konobari) {
			if (konobar.getUsername().equals(k.getUsername()) && konobar.getPassword().equals(k.getPassword())) {
				ulogovani = konobar;
				return;
			}
		}

		throw new Exception("Ne postoji korisnik sa datim kredencijalima.");

	}

	/**
	 * Vraca ulogovanog konobara na sistem.
	 * 
	 * @return ulogovani korisnik kao Konobar.
	 */
	public Konobar getKonobar() {
		return ulogovani;
	}

}
