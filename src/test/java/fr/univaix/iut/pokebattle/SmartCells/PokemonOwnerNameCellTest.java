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
import fr.univaix.iut.pokebattle.smartcell.PokemonOwnerNameCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonOwnerNameCellTest {

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
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception { 
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
        bot = new PokeBot (entityManager, "MagicarpeShiny");
    }
	
	
	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();

    @Test
    public void testNull() {
        assertEquals(null, cell.ask(bot, new Tweet("Salut!")));
    }

    @Test
    public void testNoOwner() {
    	bot.setOwner(null);
        assertEquals("@EpicSaxGuy I have no owner", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your owner?")));
    }

    @Test
    public void testOwner() {
    	bot.setOwner("albang_");
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your owner?")));
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your Owner?")));
    }
}
