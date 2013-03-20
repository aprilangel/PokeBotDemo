package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonCriesCell;
import fr.univaix.iut.pokebattle.smartcells.PokemonWinCell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonWinCellTest {

    PokemonWinCell cell = new PokemonWinCell();

    @Test
    public void testBool() {
		PokeBot b = new PokeBot();
		b.IsFighting=true;
		cell.ask(b, new Tweet("PhWright","#Win"));
        assertEquals(false, b.IsFighting);
    }

}
