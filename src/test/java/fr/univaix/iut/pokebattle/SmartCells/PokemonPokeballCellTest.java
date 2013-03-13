package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.Tweet;
import fr.univaix.iut.pokebattle.smartcells.PokemonPokeballCell;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokemonPokeballCellTest {

	PokemonPokeballCell cell = new PokemonPokeballCell();

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
    	assertEquals("@EpicSaxGuy my owner is @EpicSaxGuy", cell.ask(new Tweet("EpicSaxGuy","Pokeball!")));
    	assertEquals("@EpicSaxGuy my owner is @EpicSaxGuy", cell.ask(new Tweet("EpicSaxGuy","Who is your owner?")));
    
    	assertEquals("@xXx_JacquesChirac_xXx my owner is @EpicSaxGuy", cell.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
    	assertEquals("@xXx_JacquesChirac_xXx my owner is @EpicSaxGuy", cell.ask(new Tweet("xXx_JacquesChirac_xXx","Who is your owner?")));
  }
}
