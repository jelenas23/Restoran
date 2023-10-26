package domen;

import static org.junit.jupiter.api.Assertions.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestoranTest {
	
	Restoran restoran;

	@BeforeEach
	void setUp() throws Exception {
		restoran = new Restoran();
	}

	@AfterEach
	void tearDown() throws Exception {
		restoran=null;
	}

	@Test
	public void testKonstruktor() {
		restoran = new Restoran(2,"Restoran","Niksicka");
		assertEquals(2, restoran.getRestoranID());
		assertEquals("Restoran", restoran.getNazivRestorana());
		assertEquals("Niksicka", restoran.getAdresa());
	}

	@Test
	public void testRestoranID() {
		restoran.setRestoranID(15);
		assertEquals(15, restoran.getRestoranID());
		
	}
	
	@Test
	public void testNazivIspravno() {
		restoran.setNazivRestorana("Restoran");
		assertEquals("Restoran", restoran.getNazivRestorana());
		
	}
	
	@Test
	public void testNazivPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> restoran.setNazivRestorana(""));
		
	}
	
	@Test
	public void testNazivNull() {
		assertThrows(java.lang.NullPointerException.class,() -> restoran.setNazivRestorana(null));
		
	}
	
	@Test
	public void testAdresaIspravno() {
		restoran.setAdresa("Niksicka");
		assertEquals("Niksicka", restoran.getAdresa());
		
	}
	
	@Test
	public void testAdresaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> restoran.setAdresa("ulica"));
		assertThrows(java.lang.IllegalArgumentException.class,() -> restoran.setAdresa(""));
		
	}
	
	@Test
	public void testAdresaNull() {
		assertThrows(java.lang.NullPointerException.class,() -> restoran.setAdresa(null));
		
	}

	@Test
	public void testNazivTabele() {
		String s = restoran.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("restoran"));
	}
	
	@Test
	public void testAlijas() {
		String s =restoran.alijas();
		
		assertTrue(s.toLowerCase().contains("r"));
	}
	
	@Test
	public void testJoin() {
		assertEquals( "", restoran.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (naziv, adresa) ", restoran.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		restoran.setRestoranID(77);

		String s = restoran.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		
		
		Restoran r = new Restoran(1, "R", "Jove Ilica");
		
		
		
		String s = r.vrednostiZaInsert();
		
		assertTrue(s.contains("R"));
		assertTrue(s.contains("Jove Ilica"));

		

	}

	@Test
	public void testVrednostiZaUpdate() {
		
		String s = restoran.vrednostiZaUpdate();
		
		assertTrue(s.contains(""));
	
			
	}
	
	@Test
	public void testUslov() {
		assertEquals("", restoran.uslov());
	}

	
}
