package fr.univaix.iut.pokebattle.jpa;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class DAOPokebotJPATest {

    private static EntityManager entityManager;
    private static FlatXmlDataSet dataset;
    private static DatabaseConnection dbUnitConnection;
    private static EntityManagerFactory entityManagerFactory;

    private DAOPokebot dao = new DAOPokebotJPA(entityManager);

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
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception {
        //Clean the data from previous test and insert new data test.
        DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
    }


    @Test
    public void testFindAll() throws Exception {
        List<Pokebot> pokemons = dao.findAll();
        assertThat(pokemons.get(0).getNom()).isEqualTo("PikachuShiny");
        assertThat(pokemons.get(1).getNom()).isEqualTo("MagicarpeShiny");
    } 

    @Test
    public void testGetById() throws Exception {
        assertThat(dao.getById("PikachuShiny").getNom()).isEqualTo("PikachuShiny");
        assertThat(dao.getById("MagicarpeShiny").getNom()).isEqualTo("MagicarpeShiny");
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(dao.getById("PikachuShiny"));
        assertThat(dao.getById("PikachuShiny")).isNull();
    }

    @Test
    public void testInsert() throws Exception {
        Pokebot raichu = new Pokebot("RaichuLol");
        dao.insert(raichu);
        assertThat(dao.getById("RaichuLol").getNom()).isEqualTo("RaichuLol");
    }

    @Test
    public void testUpdate() throws Exception {
        Pokebot pikachu = dao.getById("PikachuShiny");
        assertThat(pikachu.getPv()).isGreaterThan(50);
        pikachu.setPv(30);
        dao.update(pikachu);
        assertThat(dao.getById("PikachuShiny").getPv()).isLessThan(50);
    }
}