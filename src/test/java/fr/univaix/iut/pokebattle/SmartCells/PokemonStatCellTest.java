package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.smartcell.PokemonStatCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCellTest {

	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
    private static JPAPokemon bot;

    @BeforeClass
    public static void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        entityManagerFactory = Persistence.createEntityManagerFactory("pokebattlePUTest");
        entityManager = entityManagerFactory.createEntityManager();

        Connection connection = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();

        dbUnitConnection = new DatabaseConnection(connection);
        //Loads the data set from a file
        dataset = new FlatXmlDataSetBuilder().build(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("pokebotDataset.xml"));
    }

    @AfterClass
    public static void finishTestFixture() throws Exception {
    	DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception { 
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
        bot = new JPAPokemon (entityManager, "MagicarpeShiny");
    }
    
    PokemonStatCell cell = new PokemonStatCell();
	
	@Test
	public void testLevel() {
		bot.setLevel(1);
		assertEquals("@Sarkon Je suis niveau 1", cell.ask (bot, 
				new Tweet ("Sarkon","@MagicarpeShiny Quel est ton #stat #level ?")));
	}
	
    @Test
    public void testXP() {
    	bot.setExp(0);
    	assertEquals("@Sarkon J'ai 0 points d'experience", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Quel est ton #stat #XP ?")));
    }
    
    @Test
    public void testPV() {
    	bot.setPv(100);
    	bot.setPvmax(100);
    	assertEquals("@Sarkon PV : 100/100", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #stat #PV ?")));
    }
    

    
    @Test
    public void testInconnu() {

    	assertEquals("@Sarkon Magi Magi ?", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #stat #PL ?")));
    }
    
    
//Tests pour les attaques
    
    
    @Test
    public void testPP() {
    	assertEquals("@Sarkon Trempette - PP : 25/40", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #PP #Trempette ?")));
    }
    
    @Test
    public void testPuissance() {
    	assertEquals("@Sarkon Trempette - Puissance : 35", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #Puissance #Charge ?")));
    }
    
    @Test
    public void testPrecision() {
    	assertEquals("@Sarkon Trempette - Precision : 95", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #Precision #Charge ?")));
    }
    
    @Test
    public void testInconnu2() {
    	assertEquals("@Sarkon Magi Magi ?", cell.ask (bot, 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #stat #PP ?")));

    }

    
// Fin des tests
}
