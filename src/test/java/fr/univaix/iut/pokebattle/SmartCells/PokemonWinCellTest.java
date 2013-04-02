package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonWinCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonWinCellTest {

    PokemonWinCell cell = new PokemonWinCell();

    @Test
    public void testBool() {
		PokeBot b = new PokeBot("MagicarpeShiny");
		b.setIsFighting(1);
		cell.ask(b, new Tweet("PhWright","#Win"));
        assertEquals(0, b.getIsFighting());
    }

}
