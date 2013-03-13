package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonInterlocuteurNameCell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonInterlocuteurNameCellTest {

	PokemonInterlocuteurNameCell cell = new PokemonInterlocuteurNameCell();

    @Test
    public void testNoName() {
        assertEquals(null, cell.ask(new Tweet("Salut!")));
    }

    @Test
    public void testWithName() {
        assertEquals("@godlike Carpe Carpe Magicarpe !", cell.ask(new Tweet("godlike","WHO IS AFRAID OF THE BIG BLACK ?")));
    }

}
