package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonOwnerNameCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonOwnerNameCellTest {

	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();

    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("Salut!")));
    }

    @Test
    public void testNoOwner() {
        assertEquals("@EpicSaxGuy I have no owner", cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("EpicSaxGuy","Who is your owner?")));
    }

    @Test
    public void testOwner() {
    	PokeBot bot = new PokeBot("MagicarpeShiny");
    	bot.setOwner("albang_");
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your owner?")));
        assertEquals("@EpicSaxGuy my owner is @albang_", cell.ask(bot, new Tweet("EpicSaxGuy","Who is your Owner?")));
    }
}
