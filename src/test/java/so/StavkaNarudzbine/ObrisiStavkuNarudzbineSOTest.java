package so.StavkaNarudzbine;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Jelo;
import domen.Konobar;
import domen.Narudzbina;

import domen.Restoran;

import domen.StavkaNarudzbine;
import domen.TipJela;



class ObrisiStavkuNarudzbineSOTest {
	ObrisiStavkuNarudzbineSO so;

	@BeforeEach
	void setUp() throws Exception {
		so=new ObrisiStavkuNarudzbineSO();
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
	void testUspesnoBrisanjeStavkeNarudzbine() {
		StavkaNarudzbine stavka  = new StavkaNarudzbine();
		Restoran r = new Restoran();
		r.setRestoranID(1);
		TipJela tj = new TipJela();
		tj.setTipJelaID(2);
		stavka.setKolicina(15);
		stavka.setNapomena("Sa vise crnog luka.");
		Jelo jelo = new Jelo(2, "Burito", 800,r , tj);
		stavka.setJelo(jelo);
		Konobar k = new Konobar();
		k.setKonobarID(3);
		Narudzbina n = new Narudzbina();
		n.setKonobar(k);
		n.setNarudzbinaID(1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n.setDatumNarudzbine(datum);
		stavka.setNarudzbina(n);
		
		dodajStavku(stavka);
		
		ArrayList<StavkaNarudzbine> stavke = UcitajSveStavkeNarudzbine();
		int brojStavkiPreBrisanja = stavke.size();
		
		for(StavkaNarudzbine s : stavke) {
			if(s.getNapomena().equals(stavka.getNapomena())) {
				stavka.setStavkaNarudzbineID(s.getStavkaNarudzbineID());
			}
		}

		try {
			so.izvrsavanje(stavka);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stavke = UcitajSveStavkeNarudzbine();
		
		assertEquals(brojStavkiPreBrisanja - 1,stavke.size());
		assertFalse(stavke.contains(stavka));
	}

	private ArrayList<StavkaNarudzbine> UcitajSveStavkeNarudzbine() {
		try {
			UcitajSveStavkeNarudzbineSO upso = new UcitajSveStavkeNarudzbineSO();
			upso.izvrsavanje(new StavkaNarudzbine());
			return upso.getLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void dodajStavku(StavkaNarudzbine stavka) {
		DodajStavkuNarudzbineSO dsn = new DodajStavkuNarudzbineSO();
		try {
			dsn.izvrsavanje(stavka);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
