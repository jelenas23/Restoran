package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaNarudzbineTest {

	StavkaNarudzbine stavka;
	@BeforeEach
	void setUp() throws Exception {
		stavka = new StavkaNarudzbine();
	}

	@AfterEach
	void tearDown() throws Exception {
		stavka=null;
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
		
		TipJela tj = new TipJela(1, "Dorucak");
		Jelo jelo = new Jelo(3, "Ovsena kasa", 450, r, tj);
		
		stavka = new StavkaNarudzbine(1, 2, "Bez mleka", jelo, narudzbina);
		
		assertEquals(1, stavka.getStavkaNarudzbineID());
		assertEquals(2, stavka.getKolicina());
		assertEquals("Bez mleka", stavka.getNapomena());
		assertEquals(jelo, stavka.getJelo());
		assertEquals(narudzbina, stavka.getNarudzbina());
		
	}
	
	
	@Test
	public void testStavkaNarudzbineID() {
		stavka.setStavkaNarudzbineID(15);
		assertEquals(15,stavka.getStavkaNarudzbineID());
		
	}

	@Test
	public void testKolicinaIspravno() {
		stavka.setKolicina(3);;
		assertEquals(3, stavka.getKolicina());
		
	}
	
	@Test
	public void testKolicinaPogresno() {
		assertThrows(java.lang.IllegalArgumentException.class,() -> stavka.setKolicina(-1));
		
	}
	
	@Test
	public void testNapomena() {
		stavka.setNapomena("Bez mleka");
		assertEquals("Bez mleka", stavka.getNapomena());
		
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
		stavka.setNarudzbina(narudzbina);
		assertEquals(narudzbina, stavka.getNarudzbina());
	}
	
	@Test
	public void testJelo() {
		Restoran r = new Restoran(1,"R","Jove Ilica");
		TipJela tj = new TipJela(1, "Dorucak");
		Jelo jelo = new Jelo(3, "Ovsena kasa", 450, r, tj);
		stavka.setJelo(jelo);
		assertEquals(jelo, stavka.getJelo());
	}

	@Test
	public void testNazivTabele() {
		String s = stavka.nazivTabele();
		
		assertTrue(s.toLowerCase().contains("stavkanarudzbine"));
	}
	
	@Test
	public void testAlijas() {
		String s = stavka.alijas();
		
		assertTrue(s.toLowerCase().contains("s"));
	}
	
	@Test
	public void testJoin() {
		assertEquals(" JOIN NARUDZBINA N ON s.idnarudzbine=n.narudzbinaid "
				+"JOIN JELO J ON (S.IDJELA = J.JELOID) "
				+"JOIN KONOBAR K  ON (N.IDKONOBARA = K.KONOBARID)"
				+ " JOIN RESTORAN R ON (K.IDRESTORANA = R.RESTORANID) "
				+ "JOIN TIPJELA TJ ON (J.IDTIPAJELA = TJ.TIPJELAID)", stavka.join());
	}
	
	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (kolicina, napomena, idJela, idNarudzbine) ", stavka.koloneZaInsert());
	}
	
	@Test
	public void testPrimarniKljuc() {
		stavka.setStavkaNarudzbineID(77);

		String s = stavka.vrednostZaPrimarniKljuc();
		
		assertTrue(s.contains("77"));
	}

	@Test
	public void testVrednostiZaInsert() {
		
		
        stavka  = new StavkaNarudzbine();
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
		String s = stavka.vrednostiZaInsert();
		
		assertTrue(s.contains("15"));
		assertTrue(s.contains("Sa vise crnog luka."));
		assertTrue(s.contains("2"));
		assertTrue(s.contains("1"));
		
	}

	@Test
	public void testVrednostiZaUpdate() {
		

        stavka  = new StavkaNarudzbine();
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
		String s = stavka.vrednostiZaUpdate();
		
		assertTrue(s.contains("15"));
		assertTrue(s.contains("Sa vise crnog luka."));
		
	}
	
	@Test
	public void testUslov() {
		assertEquals("", stavka.uslov());
	}

}
