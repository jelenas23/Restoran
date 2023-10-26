package so.konobar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Konobar;
import domen.Restoran;


class PrijavaKonobaraSOTest {
	
	PrijavaKonobaraSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new PrijavaKonobaraSO();
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
	public void testNePostojiKonobar() {
		Konobar konobar  = new Konobar();
		konobar.setUsername("marija");
		konobar.setPassword("marija12345");
	
		assertThrows(Exception.class, () -> so.izvrsavanje(konobar));
	}
	
	@Test
	public void testUspesnaPrijava() {
		Konobar konobar  = new Konobar();
		konobar.setUsername("jeca1");
		konobar.setPassword("jecajeca1");
		
		try {
			so.izvrsavanje(konobar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(konobar.getUsername(), so.getKonobar().getUsername());
		assertEquals(konobar.getPassword(), so.getKonobar().getPassword());
	}

}
