package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.smartcells.PokemonCriesCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonCriesCellTest {

    PokemonCriesCell cell = new PokemonCriesCell();

    @Test
    public void testSalut() {
        assertEquals("@nedseb Pika pika", cell.ask(null /* TODO */,new Tweet("nedseb", "Salut!")));
    }

    @Test
    public void testNotSalut() {
        assertEquals("@nedseb Pika pika", cell.ask(null /* TODO */,new Tweet("nedseb", "au revoir")));
    }

}
