package fr.univaix.iut.pokebattle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Integration tests checking the PokeBot
 * behavior. We just test some cases to make sure that the
 * PokeBot is using SmartCells properly.
 */
public class PokeBotTest {
    PokeBot pokeBot = new PokeBot();

    @Test
    public void testSalut() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Salut")));
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("This is not a question.")));
   }
    
    @Test
    public void testInterlocuteur() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("This is sparta !")));
        assertEquals("@sexmachine Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("sexmachine","Hello bitches")));
    }
    
    @Test
    public void testOwner() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Cuillère")));
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
    }
    
    @Test
    public void testPokeball() {
        assertEquals("Carpe Carpe Magicarpe !", pokeBot.ask(new Tweet("Cuillère")));
        assertEquals("@jeanpierrecoffe I have no owner", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","Pokeball!")));
        assertEquals("@jeanpierrecoffe my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("jeanpierrecoffe","owner?")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","Pokeball!")));
        assertEquals("@xXx_JacquesChirac_xXx my owner is @jeanpierrecoffe", pokeBot.ask(new Tweet("xXx_JacquesChirac_xXx","owner?")));
    }
    @Test
    public void testAttack() {    
    	assertEquals("@NoctaliShiny #attack #foudre! /cc @aStrangeCookie @Tenshi", pokeBot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny")));

    }
}
