package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JeloTest {
	Jelo jelo;

	@BeforeEach
	void setUp() throws Exception {
		jelo=new Jelo();
	}

	@AfterEach
	void tearDown() throws Exception {
		jelo=null;
	}

	@Test
	public void testKonstruktor() {
		Restoran restoran = new Restoran(2,"Restoran","Niksicka");
		TipJela tipJela = new TipJela(2,"Dorucak");
		jelo = new Jelo(3, "Ovsena kasa", 450, restoran, tipJela);
		assertEquals(3, jelo.getJeloID());
		assertEquals("Ovsena kasa", jelo.getNazivJela());
		assertEquals(450, jelo.getCenaJela());
		assertEquals(restoran,jelo.getRestoran());
		assertEquals(tipJela, jelo.getTipJela());
	
	}
	
	@Test
	public void testJeloID() {
		jelo.setJeloID(15);
		assertEquals(15, jelo.getJeloID());
		
	}
	
	@Test
	public void testNazivIspravno() {
		jelo.setNazivJela("Ovsena kasa");
		assertEquals("Ovsena kasa", jelo.getNazivJela());
		
	}
	
	@Test
	public void testNazivPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> jelo.setNazivJela(""));
		
	}
	
	@Test
	public void testNazivNull() {
		assertThrows(java.lang.NullPointerException.class,() -> jelo.setNazivJela(null));
		
	}
	
	@Test
	public void testCenaJelaIspravno() {
		jelo.setCenaJela(450);
		assertEquals(450, jelo.getCenaJela());
		
	}
	
	@Test
	public void testCenaJelaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> jelo.setCenaJela(-1));
		
	}

	@Test
	public void testRestoran() {
		Restoran restoran = new Restoran(2,"Restoran","Niksicka");
		jelo.setRestoran(restoran);
		
		assertEquals(restoran, jelo.getRestoran());
	}
	
	@Test
	public void testTipJela() {
		TipJela tipJela = new TipJela(2,"Dorucak");
		jelo.setTipJela(tipJela);
		
		assertEquals(tipJela, jelo.getTipJela());
	}
	

	@Test
	public void testNazivTabele() {
		String s = jelo.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("jelo"));
	}
	
	@Test
	public void testAlijas() {
		String s = jelo.alijas();
		
		assertTrue(s.toLowerCase().contains("j"));
	}
	
	@Test
	public void testJoin() {
		assertEquals(" JOIN TIPJELA TJ ON (J.IDTIPAJELA = TJ.TIPJELAID)", jelo.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals("", jelo.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		jelo.setJeloID(77);

		String s = jelo.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		Restoran restoran = new Restoran(2,"Restoran","Niksicka");
		TipJela tipJela = new TipJela(2,"Dorucak");
		jelo = new Jelo(56,"Grilovana piletina", 650, restoran, tipJela);

		String s = jelo.vrednostiZaInsert();
		
		assertTrue(s.contains(""));
		
	}

	@Test
	public void testVrednostiZaUpdate() {
		Restoran restoran = new Restoran(2,"Restoran","Niksicka");
		TipJela tipJela = new TipJela(2,"Dorucak");
		jelo = new Jelo(56,"Grilovana piletina", 650, restoran, tipJela);

		String s = jelo.vrednostiZaUpdate();
		
		assertTrue(s.contains(""));
		
	}
	
	@Test
	public void testUslov() {
		assertEquals("", jelo.uslov());
	}


}
