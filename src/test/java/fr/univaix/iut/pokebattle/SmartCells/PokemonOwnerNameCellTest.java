package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonOwnerNameCell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonOwnerNameCellTest {

	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();

    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new Tweet("Salut!")));
    }

    @Test
    public void testNoOwner() {
        assertEquals("@EpicSaxGuy I have no owner", cell.ask(new Tweet("EpicSaxGuy","Who is your owner?")));
    }

    @Test
    public void testOwner() {
    	cell.Owner = "albang_";
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(new Tweet("EpicSaxGuy","Who is your owner?")));
    }
}
