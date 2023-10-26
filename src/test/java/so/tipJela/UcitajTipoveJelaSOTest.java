package so.tipJela;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Restoran;
import domen.TipJela;


class UcitajTipoveJelaSOTest {
	UcitajTipoveJelaSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajTipoveJelaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
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
	public void testUspesnoUcitanaMesta() {
		try {
			so.izvrsavanje(new TipJela());
			List<TipJela> tipovi = so.getLista();
			assertNotNull(tipovi);
			assertFalse(tipovi.isEmpty());
			assertEquals(2, tipovi.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
