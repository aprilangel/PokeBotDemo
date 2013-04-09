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

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonJudgeCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCellTest {

	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
    private static PokeBot bot;

    @BeforeClass
    public static void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        entityManagerFactory = Persistence.createEntityManagerFactory("pokebattlePU");
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
        bot = new PokeBot (entityManager, "MagicarpeShiny");
    }
	
	
	
	PokemonJudgeCell cell = new PokemonJudgeCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(bot, new Tweet("Salut!")));
    }
	
	@Test
	public void testJudge() {
		assertEquals(null, cell.ask(bot, new Tweet("Sarkon","-10pv /cc @pcreux")));
		
		bot.setJudge("PhoenixWright");
    	
    	assertEquals(null, cell.ask(bot, new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
    	
    	bot.setOwner("IAmGod");
    	
    	assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	
    	assertEquals(90,bot.getPv());

	}
	
	@Test
	public void testKO() {
		bot.setPv(100);
		
		assertEquals(null, cell.ask(bot, new Tweet("Sarkon","-10pv /cc @pcreux")));
		
		bot.setJudge("PhoenixWright");
    	
    	assertEquals(null, cell.ask(bot, new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
    	
    	bot.setOwner("IAmGod");
    	
    	assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	
    	assertEquals(90,bot.getPv());
    	
    	bot.setFighting("@VilainMéchant");
    	
    	assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod", cell.ask(bot, new Tweet("PhoenixWright","-1337pv /cc @IAmGod")));
    	
    	assertEquals(0,bot.getPv());

	}
	
    @Test
    public void testWin() {
		bot.setFighting("AHAHAH");
		bot.setJudge("PhoenixWright");
		cell.ask(bot, new Tweet("PhWright","#Win"));
        assertEquals("AHAHAH", bot.getFighting());
		cell.ask(bot, new Tweet("PhoenixWright","#Win"));
        assertEquals(null, bot.getFighting());
    }

}
