package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KonobarTest {
	
	Konobar konobar;
	
	@BeforeEach
	void setUp() throws Exception {
		konobar= new Konobar();
	}

	@AfterEach
	void tearDown() throws Exception {
		konobar=null;
	}

	@Test
	public void testKonstruktor() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		konobar=new Konobar(1,"Jelena Stojanovic","062123456",r,"jeca1","jecajeca1");
		assertEquals(1, konobar.getKonobarID());
		assertEquals("Jelena Stojanovic", konobar.getImePrezime());
		assertEquals("062123456", konobar.getKontaktTelefon());
		assertEquals(1,r.getRestoranID());
		assertEquals("jeca1", konobar.getUsername());
		assertEquals("jecajeca1", konobar.getPassword());
	}
	
	@Test
	public void testKonobarID() {
		konobar.setKonobarID(15);
		assertEquals(15, konobar.getKonobarID());
		
	}
	
	@Test
	public void testKontaktTelefonIspravno() {
		konobar.setKontaktTelefon("062123456");
		assertEquals("062123456", konobar.getKontaktTelefon());
		
	}


	@Test
	public void testKontaktTelefonPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> konobar.setKontaktTelefon("123"));
		assertThrows(java.lang.IllegalArgumentException.class,() -> konobar.setKontaktTelefon(""));
		
	}
	
	@Test
	public void testKontaktTelefonNull() {
		assertThrows(java.lang.NullPointerException.class,() -> konobar.setKontaktTelefon(null));
		
	}
	
	@Test
	public void testImePrezimeIspravno() {
		konobar.setImePrezime("Jelena Stojanovic");
		assertEquals("Jelena Stojanovic", konobar.getImePrezime());
		
	}


	@Test
	public void testImePrezimePogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> konobar.setImePrezime("rrr"));
		assertThrows(java.lang.IllegalArgumentException.class,() -> konobar.setImePrezime(""));
		
	}
	
	@Test
	public void testImePrezimeNull() {
		assertThrows(java.lang.NullPointerException.class,() -> konobar.setImePrezime(null));
		
	}
	
	@Test
	public void testRestoran() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		konobar.setRestoran(r);
		
		assertEquals(r, konobar.getRestoran());
	}
	
	@Test
	public void testUsernameIspravan() {
		konobar.setUsername("jeca1");

		assertEquals("jeca1", konobar.getUsername());
	}
	
	@Test
	public void testUsernameNijeIspravan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> konobar.setUsername("je"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> konobar.setUsername(""));
	}
	
	@Test
	public void testUsernameNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> konobar.setUsername(null));
	}
	
	@Test
	public void testPassworrdIspravan() {
		konobar.setPassword("jecajeca1");

		assertEquals("jecajeca1", konobar.getPassword());
	}
	
	@Test
	public void testPasswordNijeIspravan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> konobar.setPassword("je"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> konobar.setPassword(""));
	}
	
	@Test
	public void testPasswordNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> konobar.setPassword(null));
	}

	@Test
	public void testNazivTabele() {
		String s = konobar.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("konobar"));
	}
	
	@Test
	public void testAlijas() {
		String s = konobar.alijas();
		
		assertTrue(s.toLowerCase().contains("k"));
	}
	
	@Test
	public void testJoin() {
		assertEquals(" JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID)", konobar.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (imePrezime, kontaktTelefon, idRestorana) ", konobar.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		konobar.setKonobarID(77);

		String s = konobar.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		
		String s = konobar.vrednostiZaInsert();
		
		assertTrue(s.contains(""));
		
	}

	@Test
	public void testVrednostiZaUpdate() {
		
		String s = konobar.vrednostiZaUpdate();
		
		assertTrue(s.contains(""));
		
	}
	
	@Test
	public void testUslov() {
		assertEquals("", konobar.uslov());
	}
}
