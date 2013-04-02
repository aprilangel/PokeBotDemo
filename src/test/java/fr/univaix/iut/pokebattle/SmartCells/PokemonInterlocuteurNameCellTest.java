package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonInterlocuteurNameCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonInterlocuteurNameCellTest {

	PokemonInterlocuteurNameCell cell = new PokemonInterlocuteurNameCell();

    @Test
    public void testNoName() {
        assertEquals(null, cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("Salut!")));
    }

    @Test
    public void testWithName() {
        assertEquals("@godlike Carpe Carpe Magicarpe !", cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("godlike","WHO IS AFRAID OF THE BIG BLACK ?")));
    }

}
