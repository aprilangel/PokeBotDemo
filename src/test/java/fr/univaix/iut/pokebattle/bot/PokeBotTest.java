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

import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Integration tests checking the PokeBot
 * behavior. We just test some cases to make sure that the
 * PokeBot is using smartcell properly.
 */
public class PokeBotTest {
    

	private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;
    private static PokeBot pokeBot;

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
        pokeBot = new PokeBot (entityManager, "MagicarpeShiny");
    }
    
    
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
        pokeBot.testJPA().setOwner(null);
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
    }
    
    @Test
    public void testPokeball() {
    	pokeBot.testJPA().setOwner(null);
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Cuillère")));
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","Pokeball!")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","owner?")));
    }
    @Test
    public void testAttack() {
    	pokeBot.testJPA().setOwner(null);
    	pokeBot.testJPA().setJudge(null);
    	pokeBot.testJPA().setNurse(null);
    	assertEquals("@Sarkon I have no owner", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@Tenshi my owner is @Tenshi", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Sarkon my owner is @Tenshi", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("PhoenixWright",pokeBot.testJPA().getJudge());


    }
	@Test
	public void testJudge() {
		pokeBot.testJPA().setOwner(null);
		pokeBot.testJPA().setJudge(null);
		pokeBot.testJPA().setPv(100);
		assertEquals("@Sarkon Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Sarkon","-10pv /cc @pcreux")));
		assertEquals("@Tenshi my owner is @Tenshi", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@PhoenixWrong Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
		assertEquals("PhoenixWright",pokeBot.testJPA().getJudge());
		assertEquals("Tenshi",pokeBot.testJPA().getOwner());
		assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @Tenshi")));
    	assertEquals(90,pokeBot.testJPA().getPv());

	}
	
	@Test
	public void testKO() {
		pokeBot.testJPA().setOwner(null);
		pokeBot.testJPA().setJudge(null);
		pokeBot.testJPA().setPv(100);
		assertEquals("@Sarkon Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Sarkon","-10pv /cc @pcreux")));
		pokeBot.testJPA().setJudge("PhoenixWright");
    	assertEquals("@PhoenixWrong Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
    	pokeBot.testJPA().setOwner("IAmGod");
    	assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	assertEquals(90,pokeBot.testJPA().getPv());
    	pokeBot.testJPA().setFighting("@VilainMéchant");
    	assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod", pokeBot.ask(new Tweet("PhoenixWright","-1337pv /cc @IAmGod")));
    	assertEquals(0,pokeBot.testJPA().getPv());

	}
	
	@Test
	public void testStatLevel() {
		pokeBot.testJPA().setLevel(1);
		assertEquals("1", pokeBot.ask( new Tweet ("@MagicarpeShiny Quel est ton #stat #level ?")));
	}
	
    @Test
    public void testStatXP() {
    	pokeBot.testJPA().setExp(0);
    	assertEquals("0", pokeBot.ask( new Tweet ("@MagicarpeShiny Quel est ton #stat #XP ?")));
    }
    
    @Test
    public void testStatPV() {
    	pokeBot.testJPA().setPv(100);
    	pokeBot.testJPA().setPvmax(100);
    	assertEquals("100/100", pokeBot.ask( new Tweet ("@MagicarpeShiny Combien as-tu de #stat #PV ?")));
    }
    
    @Test
    public void testStatInconnu() {
    	assertEquals("Magi Magi ?", pokeBot.ask( new Tweet ("@MagicarpeShiny Combien as-tu de #stat #PP ?")));
    }
    
    @Test
    public void testGoHeal() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse(null);
    	pokeBot.testJPA().setFighting(null);
        assertEquals("@InfirmiereTravis let's heal /cc @Tenshi", pokeBot.ask(new Tweet("InfirmiereTravis","come in the #pokecenter /cc @Tenshi")));
        assertEquals("InfirmiereTravis", pokeBot.testJPA().getNurse());
    }
    
    @Test
    public void testAlreadyHeal() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse("DolanNurse");
    	pokeBot.testJPA().setFighting(null);
    	assertEquals("@DolanPlz already in a #pokecenter /cc @Tenshi", pokeBot.ask(new Tweet("DolanPlz","come in the #pokecenter /cc @Tenshi")));
    	assertEquals("DolanNurse", pokeBot.testJPA().getNurse());
    }
    
    @Test
    public void testBattleNoHeal() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse(null);
    	pokeBot.testJPA().setFighting("LilianeBettencourt");
    	assertEquals("@Azerty only cowards flee from fight! /cc @Tenshi", pokeBot.ask( new Tweet("Azerty","come in the #pokecenter /cc @Tenshi")));
    	assertEquals(null, pokeBot.testJPA().getNurse());
    }
    
    @Test
	public void testNurseAttack() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse("InfirmiereTravis");
		assertEquals("@Tenshi I am healing right now", pokeBot.ask( new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
		assertEquals("@Tenshi I am healing right now", pokeBot.ask( new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}
    
    @Test
    public void testHealingDone() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse("MamaLuigi");
    	pokeBot.testJPA().setFighting(null);
    	pokeBot.testJPA().setPv(71);
    	pokeBot.testJPA().setPvmax(100);
       	assertEquals("@MamaLuigi I feel good! /cc @Tenshi", pokeBot.ask( new Tweet("MamaLuigi","is restored to full health /cc @Tenshi")));
       	assertEquals(null, pokeBot.testJPA().getNurse());
       	assertEquals(100, pokeBot.testJPA().getPv());
    }
    
    @Test
    public void testHighNurse() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	pokeBot.testJPA().setNurse("Medic");
    	pokeBot.testJPA().setFighting(null);
    	pokeBot.testJPA().setPv(71);
    	pokeBot.testJPA().setPvmax(100);
       	assertEquals("@ScoutIsSpy you cannot heal me /cc @Tenshi", pokeBot.ask( new Tweet("ScoutIsSpy","is restored to full health /cc @Tenshi")));
       	assertEquals("Medic", pokeBot.testJPA().getNurse());
       	assertEquals(71, pokeBot.testJPA().getPv());
    }
    
}
