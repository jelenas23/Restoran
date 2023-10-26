package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import domen.OpstiDomenskiObjekat;
/**
 * DbBroker je klasa pomocu koje se ostvaruje komunikacija sa bazom podataka.
 * U okviru klase implementirane su CRUD operacije pomocu kojih se vrsi manipulacija nad podacima iz baze podataka.
 * DbBroker je singlton sto znaci da se u toku zivotnog veka aplikacije kreira samo jedna instanca ove klase.
 * @author Jelena
 */
public class DbBroker {
	/**
	 * Staticka instanca klase DBBroker.
	 */
	private static DbBroker instance;
	/**
	 * Instanca klase Connection za uspostavljanje konekcije sa bazom podataka.
	 */
	private Connection connection;
	/**
	 * Instanca klase Properties u kojoj se nalaze podaci neophodni za uspostavljanje konekcije sa bazom podataka.
	 */
	private Properties properties;
	/**
	 * Neparametrizovani konstruktor u okviru koga se vrsi uspostavljanje konekcije sa bazom podataka.
	 */
	private DbBroker() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream("config/dbconfig.properties"));
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Vraca instancu klase Connection , za uspostavljanje konekcije sa bazom podataka.
	 * 
	 * @return instanca za uspostavljanje konekcije sa bazom podataka kao Connection.
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * Vraca instancu klase DbBroker. Kako je DbBroker singlton,
	 * u toku zivotnog veka aplikacije kreira se samo jedna instanca klase , proverava se
	 * da li je instanca klase null, jer ako jeste kreira se nova instanca klase DbBroker, a u suprotnom
	 * se salje postojeca.
	 *  
	 * @return instanca kao DbBroker.
	 */
	public static DbBroker getInstance() {
		if (instance == null) {
			instance = new DbBroker();
		}
		return instance;
	}
	/**
	 * Vraca listu ciji su elementi tipa OpstiDomenskiObjekat, koja je dobijena izvrsavanjem SELECT upita nad tabelom u bazi.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi informacije neophodne za izvrsavanje SELECT upita.
	 * 
	 * @return lista sa elementima tipa OpstiDomenskiObjekat.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom izvrsavanja SELECT upita.
	 */
	public ArrayList<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat odo) throws SQLException {
		String upit = "SELECT * FROM " + odo.nazivTabele() + " " + odo.alijas() + " " + odo.join() + " " + odo.uslov();
		System.out.println(upit);
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(upit);
		return odo.vratiListu(rs);
	}
	/**
	 * Vrsi dodavanje novog objekta u bazu podataka, pomocu INSERT upita.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi informacije neophodne za izvrsavanje INSERT upita.
	 * 
	 * @return ps nakon izvrsavanja upita kao PreparedStatement.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom izvrsavanja INSERT upita.
	 */
	public PreparedStatement insert(OpstiDomenskiObjekat odo) throws SQLException {
		String upit = "INSERT INTO " + odo.nazivTabele() + " " + odo.koloneZaInsert() + " VALUES("
				+ odo.vrednostiZaInsert() + ")";
		System.out.println(upit);
		PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();
		return ps;
	}
	/**
	 * Vrsi izmenu podataka koji se nalaze u bazi, pomocu UPDATE upita.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi informacije neophodne za izvrsavanje UPDATE upita.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom izvrsavanja UPDATE upita.
	 */
	public void update(OpstiDomenskiObjekat odo) throws SQLException {
		String upit = "UPDATE " + odo.nazivTabele() + " SET " + odo.vrednostiZaUpdate() + " WHERE "
				+ odo.vrednostZaPrimarniKljuc();
		System.out.println(upit);
		Statement s = connection.createStatement();
		s.executeUpdate(upit);
	}
	/**
	 * Vrsi brisanje objekata tj. redova u tabeli u bazi podataka, pomocu DELETE upita.
	 * 
	 * @param odo kao OpstiDomenskiObjekat koji sadrzi informacije neophodne za izvrsavanje DELETE upita.
	 * 
	 * @throws SQLException ukoliko dodje do greske prilikom izvrsavanja DELETE upita.
	 */
	public void delete(OpstiDomenskiObjekat odo) throws SQLException {
		String upit = "DELETE FROM " + odo.nazivTabele() + " WHERE " + odo.vrednostZaPrimarniKljuc();
		System.out.println(upit);
		Statement s = connection.createStatement();
		s.executeUpdate(upit);
	}
	
}
