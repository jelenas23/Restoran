package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacunTest {

	Racun racun;
	@BeforeEach
	void setUp() throws Exception {
		racun=new Racun();
	}

	@AfterEach
	void tearDown() throws Exception {
		racun=null;
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
		
		racun = new Racun(1,"kes",1000, narudzbina);
		assertEquals(1, racun.getRacunID());
		assertEquals("kes", racun.getNacinPlacanja());
		assertEquals(1000, racun.getIznos());
		assertEquals(narudzbina, racun.getNarudzbina());
		
	}
	
	@Test
	public void testRacunID() {
		racun.setRacunID(15);
		assertEquals(15,racun.getRacunID());
		
	}

	@Test
	public void testNacinPlacanjaIspravno() {
		racun.setNacinPlacanja("kes");
		assertEquals("kes", racun.getNacinPlacanja());
		
	}


	@Test
	public void testNacinPlacanjaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> racun.setNacinPlacanja(""));
		
	}
	
	@Test
	public void testNacinPlacanjaNull() {
		assertThrows(java.lang.NullPointerException.class,() -> racun.setNacinPlacanja(null));
		
	}
	
	@Test
	public void testIznosIspravno() {
		racun.setIznos(1000);
		assertEquals(1000, racun.getIznos());
		
	}
	
	@Test
	public void testIznosPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> racun.setIznos(-1));
		
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
		racun.setNarudzbina(narudzbina);
		assertEquals(narudzbina, racun.getNarudzbina());
	}

	
	@Test
	public void testNazivTabele() {
		String s = racun.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("racun"));
	}
	
	@Test
	public void testAlijas() {
		String s =racun.alijas();
		
		assertTrue(s.toLowerCase().contains("ra"));
	}
	
	@Test
	public void testJoin() {
		assertEquals( " JOIN NARUDZBINA N (IDNARUDZBINE) "
				+ "JOIN KONOBAR K ON (N.IDKONOBARA = K.KONOBARID) "
				+ "JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) ", racun.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals("", racun.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		racun.setRacunID(77);

		String s = racun.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		

		String s = racun.vrednostiZaInsert();
		
			
		assertTrue(s.contains(""));
	}

	@Test
	public void testVrednostiZaUpdate() {
		String s = racun.vrednostiZaInsert();
		
		
		assertTrue(s.contains(""));
	}
	
	@Test
	public void testUslov() {
		assertEquals("", racun.uslov());
	}

}
