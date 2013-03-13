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
}
