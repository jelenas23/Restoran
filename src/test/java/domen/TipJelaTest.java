package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TipJelaTest {
	
	TipJela tipJela;

	@BeforeEach
	void setUp() throws Exception {
		tipJela=new TipJela();
	}

	@AfterEach
	void tearDown() throws Exception {
		tipJela=null;
	}

	@Test
	public void testKonstruktor() {
		tipJela = new TipJela(2,"Dorucak");
		assertEquals(2, tipJela.getTipJelaID());
		assertEquals("Dorucak", tipJela.getNazivTipaJela());
		
	}

	@Test
	public void testTipJelaID() {
		tipJela.setTipJelaID(18);
		assertEquals(18, tipJela.getTipJelaID());
		
	}
	
	@Test
	public void testNazivTipaJelaIspravno() {
		tipJela.setNazivTipaJela("Dorucak");
		assertEquals("Dorucak", tipJela.getNazivTipaJela());
		
	}
	
	@Test
	public void testNazivTipaJelaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> tipJela.setNazivTipaJela(""));
		
	}
	
	@Test
	public void testNazivTipaJelaNull() {
		assertThrows(java.lang.NullPointerException.class,() -> tipJela.setNazivTipaJela(null));
		
  }
	
	@Test
	public void testNazivTabele() {
		String s = tipJela.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("tipjela"));
	}
	
	@Test
	public void testAlijas() {
		String s =tipJela.alijas();
		
		assertTrue(s.toLowerCase().contains("tj"));
	}
	
	@Test
	public void testJoin() {
		assertEquals( "", tipJela.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (nazivTipaJela) ",tipJela.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		tipJela.setTipJelaID(77);

		String s =tipJela.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		
		
		tipJela = new TipJela(5, "Rucak");
		
		
		
		String s = tipJela.vrednostiZaInsert();
		
		assertTrue(s.contains("Rucak"));
	

		

	}

	@Test
	public void testVrednostiZaUpdate() {
		
		String s = tipJela.vrednostiZaUpdate();
		
		assertTrue(s.contains(""));
	
			
	}
	
	@Test
	public void testUslov() {
		assertEquals("", tipJela.uslov());
	}


}
