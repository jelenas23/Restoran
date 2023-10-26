package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecenzijaTest {
	Recenzija recenzija;

	@BeforeEach
	void setUp() throws Exception {
		recenzija=new Recenzija();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		recenzija=null;
	}

	@Test
	public void testKonstruktor() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		Konobar konobar=new Konobar(1,"Jelena Stojanovic","062123456",r,"jeca1","jecajeca1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Narudzbina narudzbina = new Narudzbina(1, datum, konobar);
		
		recenzija = new Recenzija(1, 5, "Odlicno!", narudzbina);
		assertEquals(1, recenzija.getRecenzijaID());
		assertEquals("Odlicno!", recenzija.getKomentar());
		assertEquals(5, recenzija.getOcena());
		assertEquals(narudzbina, recenzija.getNarudzbina());
		
	}
	
	@Test
	public void testRecenzijaID() {
		recenzija.setRecenzijaID(15);
		assertEquals(15,recenzija.getRecenzijaID());
		
	}

	@Test
	public void testKomentar() {
		recenzija.setKomentar("Odlicno!");
		assertEquals("Odlicno!", recenzija.getKomentar());
		
	}


	
	
	@Test
	public void testOcenaIspravno() {
		recenzija.setOcena(5);
		assertEquals(5, recenzija.getOcena());
		
	}
	
	@Test
	public void testOcenaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> recenzija.setOcena(-1));
		
	}
	@Test
	public void testNarudzbina() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		Konobar konobar=new Konobar(1,"Jelena Stojanovic","062123456",r,"jeca1","jecajeca1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Narudzbina narudzbina = new Narudzbina(1, datum, konobar);
		recenzija.setNarudzbina(narudzbina);
		assertEquals(narudzbina, recenzija.getNarudzbina());
	}


	public void testNazivTabele() {
		String s = recenzija.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("recenzija"));
	}
	
	@Test
	public void testAlijas() {
		String s =recenzija.alijas();
		
		assertTrue(s.toLowerCase().contains("re"));
	}
	
	@Test
	public void testJoin() {
		assertEquals( " JOIN NARUDZBINA N ON RE.IDNARUDZBINE=N.NARUDZBINAID "
				+ "JOIN KONOBAR K ON (N.IDKONOBARA = K.KONOBARID) "
				+ "JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) ", recenzija.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (ocena, komentar,idNarudzbine) ", recenzija.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		recenzija.setRecenzijaID(77);

		String s = recenzija.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		
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
		
		Recenzija recenzija = new Recenzija(2, 10, "Sve pohvale!", narudzbina);
		String s = recenzija.vrednostiZaInsert();
		
		assertTrue(s.contains("10"));
		assertTrue(s.contains("Sve pohvale!"));
		assertTrue(s.contains("2"));	
		

	}

	@Test
	public void testVrednostiZaUpdate() {
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
		
		Recenzija recenzija = new Recenzija(2, 10, "Sve pohvale!", narudzbina);
		String s = recenzija.vrednostiZaUpdate();
		
		assertTrue(s.contains("10"));
		assertTrue(s.contains("Sve pohvale!"));
			
	}
	
	@Test
	public void testUslov() {
		assertEquals("", recenzija.uslov());
	}

}
