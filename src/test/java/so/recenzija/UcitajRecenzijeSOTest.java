package so.recenzija;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Recenzija;
import domen.Restoran;
import domen.TipJela;
import so.tipJela.UcitajTipoveJelaSO;

class UcitajRecenzijeSOTest {
	UcitajRecenzijeSO so;

	@BeforeEach
	void setUp() throws Exception {
		so = new UcitajRecenzijeSO();
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
	public void testUspesnoUcitaneRecenzije() {
		try {
			List<Recenzija> recenzije = so.getLista();
			so.izvrsavanje(new Recenzija());
			recenzije = so.getLista();
			assertNotNull(recenzije);
			assertFalse(recenzije.isEmpty());
			assertEquals(1, recenzije.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
