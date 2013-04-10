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
import fr.univaix.iut.pokebattle.smartcell.PokemonJudgeCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCellTest {

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
	
	
	
	PokemonJudgeCell cell = new PokemonJudgeCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(bot, new Tweet("Salut!")));
    }
	
	@Test
	public void testNotJudge() {
		assertEquals(null, cell.ask(bot, new Tweet("Sarkon","-10pv /cc @pcreux")));
	}
	
	@Test
	public void testJudge() {
		bot.setJudge("PhoenixWright");
		assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	assertEquals(90,bot.getPv());
	}
	
	@Test
	public void testJudgeNotOrder() {
		bot.setJudge("PhoenixWright");
		assertEquals(null, cell.ask(bot, new Tweet("PhoenixWright","#ILikeTrains")));
	}
	
	
	@Test
	public void testKOOneShot() {
		bot.setPv(100);
		bot.setJudge("PhoenixWright");
		bot.setFighting("@VilainMéchant");
		bot.setOwner("IAmGod");
		assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod", cell.ask(bot, new Tweet("PhoenixWright","-1337pv /cc @IAmGod")));
    	assertEquals(0,bot.getPv());
	}
	
	@Test
	public void testKOMultipleShot() {
		bot.setPv(100);
		bot.setJudge("PhoenixWright");
		bot.setFighting("@VilainMéchant");
		bot.setOwner("IAmGod");
		assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-42pv /cc @IAmGod")));
		assertEquals(58,bot.getPv());
    	assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod", cell.ask(bot, new Tweet("PhoenixWright","-69pv /cc @IAmGod")));
    	assertEquals(0,bot.getPv());
	}
	
    @Test
    public void testNotJudgeWin() {
		bot.setFighting("AHAHAH");
		bot.setJudge("PhoenixWright");
		cell.ask(bot, new Tweet("PhWright","#Win"));
        assertEquals("AHAHAH", bot.getFighting());
    }
    
    @Test
    public void testJudgeWin() {
		bot.setFighting("AHAHAH");
		bot.setJudge("PhoenixWright");
		cell.ask(bot, new Tweet("PhoenixWright","#Win"));
        assertEquals(null, bot.getFighting());
    }
    
	@Test
	public void testJudgeXp() {
		bot.setExp(0);
		bot.setJudge("PhoenixWright");
    	assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","tu es trop fort tu as #Win +69xp")));
    	assertEquals(69,bot.getExp());
	}
	
	@Test
	public void testNotJudgeXp() {
		bot.setExp(0);
		bot.setJudge("PhoenixWright");
		assertEquals(null, cell.ask(bot, new Tweet("PhoenixWrong","olol tu l'as OS donc tu #Win +69xp")));
		assertEquals(0,bot.getExp());
	}

}
