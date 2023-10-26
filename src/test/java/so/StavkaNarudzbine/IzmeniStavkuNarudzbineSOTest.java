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


class IzmeniStavkuNarudzbineSOTest {
	IzmeniStavkuNarudzbineSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new IzmeniStavkuNarudzbineSO();
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
	public void testUspesnaIzmenaStavkeNarudzbine() {
		StavkaNarudzbine stavka  = new StavkaNarudzbine();
		Restoran r = new Restoran();
		r.setRestoranID(1);
		TipJela tj = new TipJela();
		tj.setTipJelaID(2);
		stavka.setKolicina(15);
		stavka.setNapomena("Sa vise crnog luka.");
		stavka.setJelo(new Jelo(2, "Burito", 800,r , tj));
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
		
		try {
			new DodajStavkuNarudzbineSO().izvrsavanje(stavka);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ArrayList<StavkaNarudzbine> stavke = UcitajStavke();
		for(StavkaNarudzbine s : stavke) {
			if(s.getNapomena().equals(stavka.getNapomena())) {
				stavka.setStavkaNarudzbineID(s.getStavkaNarudzbineID());
			}
		}
		stavka.setNapomena("Bez luka.");
		stavka.setKolicina(4);
		
		try {
			so.izvrsavanje(stavka);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stavke=UcitajStavke();
		
		for(StavkaNarudzbine s: stavke) {
			if(s.getStavkaNarudzbineID() == stavka.getStavkaNarudzbineID()) {
				assertTrue(s.getNapomena().equals(stavka.getNapomena()));
				assertTrue(s.getKolicina()==stavka.getKolicina());
				break;
			}
		}
		ObrisiStavku(stavka);
	}

	private void ObrisiStavku(StavkaNarudzbine stavka) {
		ObrisiStavkuNarudzbineSO obrso = new ObrisiStavkuNarudzbineSO();
		try {
			obrso.izvrsavanje(stavka);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ArrayList<StavkaNarudzbine> UcitajStavke() {
		try {
			UcitajSveStavkeNarudzbineSO usno = new UcitajSveStavkeNarudzbineSO();
			usno.izvrsavanje(new StavkaNarudzbine());
			return usno.getLista();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
