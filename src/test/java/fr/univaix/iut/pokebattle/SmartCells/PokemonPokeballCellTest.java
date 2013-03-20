package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonPokeballCell;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonPokeballCellTest {

	PokemonPokeballCell cell = new PokemonPokeballCell();

    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot(), new Tweet("Salut!")));
    }

    @Test
    public void testOwner() {
    	PokeBot bot = new PokeBot ();
    	assertEquals("@EpicSaxGuy my owner is @EpicSaxGuy", cell.ask(bot, new Tweet("EpicSaxGuy","Pokeball!")));
    	assertEquals("@xXx_JacquesChirac_xXx my owner is @EpicSaxGuy", cell.ask(bot, new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
  }
}
