package so.recenzija;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Jelo;
import domen.Konobar;
import domen.Narudzbina;
import domen.Recenzija;
import domen.Restoran;
import domen.StavkaNarudzbine;
import domen.TipJela;
import so.StavkaNarudzbine.DodajStavkuNarudzbineSO;
import so.StavkaNarudzbine.UcitajSveStavkeNarudzbineSO;

class ObrisiRecenzijuSOTest {
	ObrisiRecenzijuSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new ObrisiRecenzijuSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		so=null;
	}


	@Test
	public void testNeuspesnaValidacijaRazlicitaKlasa() {
		assertThrows(Exception.class, () -> so.validacija(new Restoran()));
	}
	
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> so.validacija(null));
	}
	
	@Test
	void testUspesnoBrisanjeRecenzije() {
		Recenzija recenzija = new Recenzija();
		Narudzbina narudzbina = new Narudzbina();
		Konobar k = new Konobar();
		Restoran r = new Restoran();
		r.setRestoranID(1);
		k.setKonobarID(1);
		k.setRestoran(r);
		narudzbina.setNarudzbinaID(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		narudzbina.setDatumNarudzbine(datum);
		narudzbina.setKonobar(k);
		
		recenzija.setKomentar("Sve pohvale!");
		recenzija.setNarudzbina(narudzbina);
		recenzija.setOcena(10);
		
		dodajRecenziju(recenzija);
		
		ArrayList<Recenzija> recenzije= UcitajRecenzije();
		int brojRecenzijaPreBrisanja = recenzije.size();
		
		for(Recenzija re : recenzije) {
			if(re.getKomentar().equals(recenzija.getKomentar())) {
				recenzija.setRecenzijaID(re.getRecenzijaID());
			}
		}

		try {
			so.izvrsavanje(recenzija);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		recenzije = UcitajRecenzije();
		
		assertEquals(brojRecenzijaPreBrisanja - 1,recenzije.size());
		assertFalse(recenzije.contains(recenzija));
	}

	private ArrayList<Recenzija> UcitajRecenzije() {
		try {
			UcitajRecenzijeSO urso = new UcitajRecenzijeSO();
			urso.izvrsavanje(new Recenzija());
			return urso.getLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void dodajRecenziju(Recenzija recenzija) {
		DodajRecenzijuSO drso = new DodajRecenzijuSO();
		try {
			drso.izvrsavanje(recenzija);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
