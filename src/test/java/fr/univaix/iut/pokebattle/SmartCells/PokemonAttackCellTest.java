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
import fr.univaix.iut.pokebattle.smartcell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCellTest {

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
	
	
	PokemonAttackCell cell = new PokemonAttackCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(bot, new Tweet("Salut!")));
    }
	
	@Test
	public void testAttackNoOwner() {
		bot.setOwner(null);
		assertEquals("@Sarkon I have no owner", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
	}
	
	@Test
	public void testAttackWrongOwner() {	
		bot.setOwner("Tenshi");
    	assertEquals("@Sarkon my owner is @Tenshi", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
	}
	
	@Test
	public void testAttackUnknown() {	
		bot.setOwner("Tenshi");
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", cell.ask(bot, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}
	
	@Test
	public void testNurseAttack() {
		bot.setOwner("Tenshi");
		bot.setNurse("InfirmiereTravis");
		assertEquals("@Tenshi I am healing right now", cell.ask(bot, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
		assertEquals("@Tenshi I am healing right now", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}
	
	@Test
    public void testNoMorePP() {
		bot.setPp1(0);
		bot.setOwner("Tenshi");
		assertEquals(0, bot.getPp1());
		assertEquals("#Trempette n'a plus de PP. @Tenshi", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
		assertEquals(0, bot.getPp1());
	}
	
	@Test
	public void testPPReduction() {
		bot.setPp1(12);
		bot.setOwner("Tenshi");
		assertEquals(12, bot.getPp1());
		assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
		assertEquals(11, bot.getPp1());
    }
	
	@Test
	public void testAttackKnown() {	
		bot.setOwner("Tenshi");
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}

	@Test
    public void testFighting() {
		bot.setOwner("Tenshi");
		bot.setFighting(null);
		assertEquals(null, bot.getFighting());
		cell.ask(bot, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright"));
        assertEquals("@aStrangeCookie", bot.getFighting());
    }
	
	@Test
	public void testJudge() {
		bot.setOwner("Tenshi");
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
		assertEquals("PhoenixWright",bot.getJudge());
	}


	
}
