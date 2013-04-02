package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonCriesCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonCriesCellTest {

    PokemonCriesCell cell = new PokemonCriesCell();

    @Test
    public void testSalut() {
        assertEquals("Carpe Carpe Magicarpe !", cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("Salut!")));
    }

    @Test
    public void testNotSalut() {
        assertEquals("Carpe Carpe Magicarpe !", cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("au revoir")));
    }

}
