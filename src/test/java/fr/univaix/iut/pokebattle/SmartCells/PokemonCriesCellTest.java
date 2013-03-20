package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonCriesCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonCriesCellTest {

    PokemonCriesCell cell = new PokemonCriesCell();

    @Test
    public void testSalut() {
        assertEquals("Carpe Carpe Magicarpe !", cell.ask(new PokeBot(), new Tweet("Salut!")));
    }

    @Test
    public void testNotSalut() {
        assertEquals("Carpe Carpe Magicarpe !", cell.ask(new PokeBot(), new Tweet("au revoir")));
    }

}
