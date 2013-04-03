package fr.univaix.iut.pokebattle.bot;

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

import fr.univaix.iut.pokebattle.jpa.DAOPokebot;
import fr.univaix.iut.pokebattle.jpa.DAOPokebotJPA;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Integration tests checking the PokeBot
 * behavior. We just test some cases to make sure that the
 * PokeBot is using smartcell properly.
 */
public class PokeBotTest {
    

	private static PokeBot pokeBot;
/*	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
    
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
        
        
        
        pokeBot = new PokeBot(entityManager, "MagikarpShiny");
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
    }
    
    */
    
    
    @Test
    public void testSalut() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Salut")));
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("This is not a question.")));
   }
    
    @Test
    public void testInterlocuteur() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("This is sparta !")));
        assertEquals("@sexmachine Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("sexmachine","Hello bitches")));
    }
    
    @Test
    public void testOwner() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Cuillère")));
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
    }
    
    @Test
    public void testPokeball() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Cuillère")));
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","Pokeball!")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","owner?")));
    }
    @Test
    public void testAttack() {    
    	assertEquals("@Sarkon I have no owner", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@Tenshi my owner is @Tenshi", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Sarkon my owner is @Tenshi", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("PhoenixWright",pokeBot.getJudge());


    }
	@Test
	public void testJudge() {
		assertEquals("@Sarkon Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Sarkon","-10pv /cc @pcreux")));
		assertEquals("@Tenshi my owner is @Tenshi", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@PhoenixWright Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @pcreux")));
		assertEquals("PhoenixWright",pokeBot.getJudge());
		assertEquals("Tenshi",pokeBot.getOwner());
		assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @Tenshi")));
    	assertEquals(90,pokeBot.getPv());

	}
}
