package so.recenzija;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Konobar;
import domen.Narudzbina;

import domen.Recenzija;
import domen.Restoran;



class DodajRecenzijuSOTest {

	
	DodajRecenzijuSO  so;

	@BeforeEach
	void setUp() throws Exception {
		so = new DodajRecenzijuSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
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
	public void testUspesnoDodavanjeRecenzije() {
		ArrayList<Recenzija> recenzije = ucitajRecenzije();
		int brojRecenzijaPreDodavanja = recenzije.size();
		
		Recenzija recenzija = new Recenzija();
		Narudzbina narudzbina = new Narudzbina();
		Konobar k = new Konobar();
		Restoran r = new Restoran();
		r.setRestoranID(1);
		k.setKonobarID(1);
		k.setRestoran(r);
		narudzbina.setNarudzbinaID(3);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		narudzbina.setDatumNarudzbine(datum);
		narudzbina.setKonobar(k);
		
		recenzija.setKomentar("Sve pohvale!");
		recenzija.setNarudzbina(narudzbina);
		recenzija.setOcena(10);
		
		try {
			so.izvrsavanje(recenzija);
		} catch (Exception e) {
			e.printStackTrace();
		}
		recenzije = ucitajRecenzije();
		
		assertEquals(brojRecenzijaPreDodavanja + 1, recenzije.size());
		// && rec.getOcena()==recenzija.getOcena() && rec.getKomentar().equals(recenzija.getKomentar())
		boolean postoji=false;
		for(Recenzija rec : recenzije) {
			if(rec.getNarudzbina().getNarudzbinaID() == recenzija.getNarudzbina().getNarudzbinaID()) {
				postoji = true;
				break;
			}
		}
		
		assertEquals((brojRecenzijaPreDodavanja + 1), recenzije.size());
		assertEquals(true, postoji);
		
		Recenzija obrisiR = recenzije.get(recenzije.size()-1);
		obrisiRecenziju(obrisiR);
		
		
	}

	private ArrayList<Recenzija> ucitajRecenzije() {
		try {
			UcitajRecenzijeSO urso = new UcitajRecenzijeSO();
			urso.izvrsavanje(new Recenzija());
			return urso.getLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}

	private void obrisiRecenziju(Recenzija recenzija) {
		ObrisiRecenzijuSO obrso = new ObrisiRecenzijuSO();
		try {
			obrso.izvrsavanje(recenzija);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	public void testNeuspesnoDodavanjePostojiNarudzbina() {
		
		Recenzija recenzija = new Recenzija();
		Narudzbina narudzbina = new Narudzbina();
		Konobar k = new Konobar();
		Restoran r = new Restoran();
		r.setRestoranID(1);
		k.setKonobarID(1);
		k.setRestoran(r);
		narudzbina.setNarudzbinaID(3);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		narudzbina.setDatumNarudzbine(datum);
		narudzbina.setKonobar(k);
		
		recenzija.setKomentar("Sve pohvale!");
		recenzija.setNarudzbina(narudzbina);
		recenzija.setOcena(10);
		
		String komentar = recenzija.getKomentar();

		try {
			so.izvrsavanje(recenzija);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		recenzija.setKomentar("OK");

		assertThrows(Exception.class, () -> so.izvrsavanje(recenzija));

		recenzija.setKomentar(komentar);
		
		
        ArrayList<Recenzija> recenzije= ucitajRecenzije();
		
		for(int i = 0;i < recenzije.size();i++) {
			if(recenzije.get(i).getNarudzbina().getNarudzbinaID() == recenzija.getNarudzbina().getNarudzbinaID()) {
				obrisiRecenziju(recenzije.get(i));
			}
		}
	}
	
}


