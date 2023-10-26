package so.recenzija;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DbBroker;
import domen.Konobar;
import domen.Narudzbina;

import domen.Recenzija;
import domen.Restoran;





class IzmeniRecenzijuSOTest {
	IzmeniRecenzijuSO so;

	@BeforeEach
	void setUp() throws Exception {
		so=new IzmeniRecenzijuSO();
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
	public void testUspesnaIzmenaRecenzije() {
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
		
		try {
			new DodajRecenzijuSO().izvrsavanje(recenzija);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Recenzija> recenzije = ucitajRecenzije();
		for(Recenzija re : recenzije) {
			if(re.getKomentar().equals(recenzija.getKomentar())) {
				recenzija.setRecenzijaID(re.getRecenzijaID());
			}
		}
		recenzija.setKomentar("Lose!!!");
		recenzija.setOcena(1);
		
		try {
			so.izvrsavanje(recenzija);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		recenzije=ucitajRecenzije();
		
		for(Recenzija re : recenzije) {
			if(re.getRecenzijaID() == recenzija.getRecenzijaID()) {
				assertTrue(re.getKomentar().equals(recenzija.getKomentar()));
				assertTrue(re.getOcena()==recenzija.getOcena());
				break;
			}
		}
		ObrisiRecenziju(recenzija);
	}

	private void ObrisiRecenziju(Recenzija recenzija) {
		ObrisiRecenzijuSO orso = new ObrisiRecenzijuSO();
		try {
			orso.izvrsavanje(recenzija);
			DbBroker.getInstance().getConnection().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ArrayList<Recenzija> ucitajRecenzije() {
		try {
			UcitajRecenzijeSO urso = new UcitajRecenzijeSO();
			urso.izvrsavanje(new Recenzija());
			return urso.getLista();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
