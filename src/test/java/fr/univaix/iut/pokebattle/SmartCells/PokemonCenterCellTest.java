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
import fr.univaix.iut.pokebattle.smartcell.PokemonCenterCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonCenterCellTest {

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
    	DatabaseOperation.DELETE_ALL.execute(dbUnitConnection, dataset);
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
        bot = new JPAPokemon (entityManager, "MagicarpeShiny");
    }
	
	
	PokemonCenterCell cell = new PokemonCenterCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(bot, new Tweet("Salut!")));
    }
	
    @Test
    public void testGoHeal() {
    	bot.setOwner("Tenshi");
    	bot.setNurse(null);
    	bot.setFighting(null);
        assertEquals("@InfirmiereTravis let's heal /cc @Tenshi", cell.ask(bot, new Tweet("InfirmiereTravis","come in the #pokecenter /cc @Tenshi")));
        assertEquals("InfirmiereTravis", bot.getNurse());
    }
    
    @Test
    public void testAlreadyHeal() {
    	bot.setOwner("Tenshi");
    	bot.setNurse("DolanNurse");
    	bot.setFighting(null);
    	assertEquals("@DolanPlz already in a #pokecenter /cc @Tenshi", cell.ask(bot, new Tweet("DolanPlz","come in the #pokecenter /cc @Tenshi")));
    	assertEquals("DolanNurse", bot.getNurse());
    }
    
    @Test
    public void testBattleNoHeal() {
    	bot.setOwner("Tenshi");
    	bot.setNurse(null);
    	bot.setFighting("LilianeBettencourt");
    	assertEquals("@Azerty only cowards flee from fight! /cc @Tenshi", cell.ask(bot, new Tweet("Azerty","come in the #pokecenter /cc @Tenshi")));
    	assertEquals(null, bot.getNurse());
    }
	 
}
