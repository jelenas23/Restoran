package so.StavkaNarudzbine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Recenzija;
import domen.Restoran;
import domen.StavkaNarudzbine;

class UcitajSveStavkeNarudzbineSOTest {

	UcitajSveStavkeNarudzbineSO so;
	
	@BeforeEach
	void setUp() throws Exception {
		so=new UcitajSveStavkeNarudzbineSO();
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
	public void testUspesnoUcitaneSveStavkeNarudzbine() {
		try {
			List<StavkaNarudzbine> stavke = so.getLista();
			so.izvrsavanje(new StavkaNarudzbine());
			stavke = so.getLista();
			assertNotNull(stavke);
			assertFalse(stavke.isEmpty());
			assertEquals(2, stavke.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
