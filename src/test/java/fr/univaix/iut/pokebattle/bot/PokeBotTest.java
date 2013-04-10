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
        assertEquals("Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("Salut")));
    }
    
    @Test
    public void testInterlocuteur() {
        assertEquals("@sexmachine Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("sexmachine","Hello bitches")));
    }
    
    @Test
    public void testNoOwner() {
        pokeBot.testJPA().setOwner(null);
        assertEquals("@jeanpierrecoffe I have no owner #PokeBattle", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
    }
    
    @Test
    public void testYesOwner() {
        pokeBot.testJPA().setOwner("jeanpierrecoffe");
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
    }
    
    @Test
    public void testPokeballNoOwner() {
    	pokeBot.testJPA().setOwner(null);
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("jeanpierrecoffe","Pokeball!")));
    }
    
    @Test
    public void testPokeballWithOwner() {
    	pokeBot.testJPA().setOwner("jeanpierrecoffe");
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
    }
    
    @Test
    public void testPokeballandOwner() {
    	pokeBot.testJPA().setOwner(null);
        assertEquals("@jeanpierrecoffe I have no owner #PokeBattle", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("jeanpierrecoffe","Pokeball!")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Owner?")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe #PokeBattle", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
    }
    
    @Test
    public void testAttackNoOwner() {
    	pokeBot.testJPA().setOwner(null);
    	assertEquals("@Sarkon I have no owner #PokeBattle", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    }
    
    @Test
    public void testAttackWrongOwner() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	assertEquals("@Sarkon my owner is @Tenshi #PokeBattle", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    }
    
    @Test
    public void testAttackOwner() {
    	pokeBot.testJPA().setOwner("Tenshi");
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    }
    
	@Test
	public void testAttackUnknown() {	
		pokeBot.testJPA().setOwner("Tenshi");
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}
	
	@Test
	public void testAttackKnown() {	
		pokeBot.testJPA().setOwner("Tenshi");
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}
    	
	@Test
	public void testAttackJudge() {
		pokeBot.testJPA().setOwner("Tenshi");
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("PhoenixWright",pokeBot.testJPA().getJudge());
	}
	
	@Test
	public void testGlobalAttackPokeballOwner()	{
    	pokeBot.testJPA().setOwner(null);
    	pokeBot.testJPA().setJudge(null);
    	assertEquals("@Sarkon I have no owner #PokeBattle", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@Tenshi my owner is @Tenshi #PokeBattle", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Sarkon my owner is @Tenshi #PokeBattle", pokeBot.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("PhoenixWright",pokeBot.testJPA().getJudge());
    }
    
	@Test
	public void testJudgeDamage() {
		pokeBot.testJPA().setJudge("PhoenixWright");
		pokeBot.testJPA().setPv(100);
		assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @Tenshi")));
    	assertEquals(90,pokeBot.testJPA().getPv());
	}
	
	@Test
	public void testWrongJudgeDamage() {
		pokeBot.testJPA().setJudge("PhoenixWright");
		pokeBot.testJPA().setPv(100);
		assertEquals("@PhoenixWrong Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("PhoenixWrong","-10pv /cc @Tenshi")));
    	assertEquals(100,pokeBot.testJPA().getPv());
	}
	
	@Test
	public void testJudgeDumbthings() {
		pokeBot.testJPA().setJudge("PhoenixWright");
		assertEquals("@PhoenixWright Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("PhoenixWright","#ILikeTrains")));
	}
	
	@Test
	public void testJudgeKO() {
		pokeBot.testJPA().setOwner("IAmGod");
		pokeBot.testJPA().setFighting("@VilainMéchant");
		pokeBot.testJPA().setJudge("PhoenixWright");
		pokeBot.testJPA().setPv(100);
		assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod #PokeBattle", pokeBot.ask(new Tweet("PhoenixWright","-125pv /cc @Tenshi")));
    	assertEquals(0,pokeBot.testJPA().getPv());
	}
	
	@Test
	public void testJudgeWin() {
		pokeBot.testJPA().setFighting("@VilainMéchant");
		pokeBot.testJPA().setJudge("PhoenixWright");
		assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","#Win")));
    	assertEquals(null,pokeBot.testJPA().getFighting());
	}
	
	@Test
	public void testNotJudgeWin() {
		pokeBot.testJPA().setFighting("@VilainMéchant");
		pokeBot.testJPA().setJudge("PhoenixWright");
		assertEquals("@PhoenixWrong Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("PhoenixWrong","#Win")));
    	assertEquals("@VilainMéchant",pokeBot.testJPA().getFighting());
	}
	
	@Test
	public void testJudgeFight() {
		pokeBot.testJPA().setOwner(null);
		pokeBot.testJPA().setJudge(null);
		pokeBot.testJPA().setPv(100);
		assertEquals("@Sarkon Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("Sarkon","-10pv /cc @pcreux")));
		assertEquals("@Tenshi my owner is @Tenshi #PokeBattle", pokeBot.ask(new Tweet("Tenshi","Pokeball!")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny #PokeBattle", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@PhoenixWrong Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
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
		assertEquals("@Sarkon Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("Sarkon","-10pv /cc @pcreux")));
		pokeBot.testJPA().setJudge("PhoenixWright");
    	assertEquals("@PhoenixWrong Carpe Carpe Magicarpe ! #PokeBattle", pokeBot.ask(new Tweet("PhoenixWrong","-10pv /cc @pcreux")));
    	pokeBot.testJPA().setOwner("IAmGod");
    	assertEquals(null, pokeBot.ask(new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	assertEquals(90,pokeBot.testJPA().getPv());
    	pokeBot.testJPA().setFighting("@VilainMéchant");
    	assertEquals("#KO /cc @PhoenixWright @VilainMéchant @IAmGod #PokeBattle", pokeBot.ask(new Tweet("PhoenixWright","-1337pv /cc @IAmGod")));
    	assertEquals(0,pokeBot.testJPA().getPv());

	}
	
	@Test
	public void testStatLevel() {
		pokeBot.testJPA().setLevel(1);
		assertEquals("@Sarkon Je suis niveau 1 #PokeBattle", pokeBot.ask( new Tweet ("Sarkon","@MagicarpeShiny Quel est ton #stat #level ?")));
	}
	
    @Test
    public void testStatXP() {
    	pokeBot.testJPA().setExp(0);
    	assertEquals("@Sarkon J'ai 0 points d'experience #PokeBattle", pokeBot.ask( new Tweet ("Sarkon","@MagicarpeShiny Quel est ton #stat #XP ?")));
    }
    
    @Test
    public void testStatPV() {
    	pokeBot.testJPA().setPv(100);
    	pokeBot.testJPA().setPvmax(100);
    	assertEquals("@Sarkon PV : 100/100 #PokeBattle", pokeBot.ask( new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #stat #PV ?")));
    }
    
    @Test
    public void testStatInconnu() {
    	assertEquals("@Sarkon Magi Magi ? #PokeBattle", pokeBot.ask( new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #stat #PP ?")));
    }
    
    @Test
    public void testPP() {
    	pokeBot.testJPA().setPp1(25);
    	assertEquals("@Sarkon Trempette - PP : 25/40 #PokeBattle", pokeBot.ask( 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #PP #Trempette ?")));
    }
    
    @Test
    public void testPuissance() {
    	assertEquals("@Sarkon Trempette - Puissance : 35 #PokeBattle", pokeBot.ask( 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #Puissance #Charge ?")));
    }
    
    @Test
    public void testPrecision() {
    	assertEquals("@Sarkon Trempette - Precision : 95 #PokeBattle", pokeBot.ask( 
    			new Tweet ("Sarkon","@MagicarpeShiny Combien as-tu de #statAttack #Precision #Charge ?")));
    }
    
}
