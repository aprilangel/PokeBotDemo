package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonOwnerNameCell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonOwnerNameCellTest {

	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();

    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot(), new Tweet("Salut!")));
    }

    @Test
    public void testNoOwner() {
        assertEquals("@EpicSaxGuy I have no owner", cell.ask(new PokeBot(), new Tweet("EpicSaxGuy","Who is your owner?")));
    }

    @Test
    public void testOwner() {
    	PokeBot bot = new PokeBot();
    	bot.Owner = "albang_";
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your owner?")));
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your Owner?")));
    }
}
