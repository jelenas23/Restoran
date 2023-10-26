package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NarudzbinaTest {
	Narudzbina narudzbina;

	@BeforeEach
	void setUp() throws Exception {
		narudzbina=new Narudzbina();
	}

	@AfterEach
	void tearDown() throws Exception {
		narudzbina = null;
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
		narudzbina = new Narudzbina(1, datum, konobar);
		assertEquals(1, narudzbina.getNarudzbinaID());
		assertEquals(datum, narudzbina.getDatumNarudzbine());
		assertEquals(konobar, narudzbina.getKonobar());
		
	}
	

	@Test
	public void testNarudzbinaID() {
		narudzbina.setNarudzbinaID(15);
		assertEquals(15, narudzbina.getNarudzbinaID());
		
	}
	@Test
	public void testDatumIspravan() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date datum = null;
		try {
			datum = sdf.parse("05.10.2023 22:24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		narudzbina.setDatumNarudzbine(datum);
		
		assertEquals(datum, narudzbina.getDatumNarudzbine());
		
	}
	
	@Test 
	public void testDatumNijeIspravan() {
		Date datum = Date.from(LocalDate.of(2085, 9, 9).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		assertThrows(java.lang.IllegalArgumentException.class, () ->narudzbina.setDatumNarudzbine(datum));
	}
	
	@Test 
	public void testSetDatumRodjenjaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> narudzbina.setDatumNarudzbine(null));
	}
	@Test
	public void testKonobar() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		Konobar konobar=new Konobar(1,"Jelena Stojanovic","062123456",r,"jeca1","jecajeca1");
		narudzbina.setKonobar(konobar);
		assertEquals(konobar, narudzbina.getKonobar());
	}
	


	@Test
	public void testNazivTabele() {
		String s = narudzbina.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("narudzbina"));
	}
	
	@Test
	public void testAlijas() {
		String s = narudzbina.alijas();
		
		assertTrue(s.toLowerCase().contains("n"));
	}
	
	@Test
	public void testJoin() {
		assertEquals(" JOIN KONOBAR K ON N.IDKONOBARA=K.KONOBARID "
				+ "JOIN RESTORAN R ON (R.RESTORANID = K.IDRESTORANA) ", narudzbina.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals("", narudzbina.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		narudzbina.setNarudzbinaID(77);

		String s = narudzbina.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		

		String s = narudzbina.vrednostiZaInsert();
		
		assertTrue(s.contains(""));
		
	}

	@Test
	public void testVrednostiZaUpdate() {
		
        String s = narudzbina.vrednostiZaUpdate();
		
		assertTrue(s.contains(""));
	}
	
	@Test
	public void testUslov() {
		assertEquals("", narudzbina.uslov());
	}
	

}
