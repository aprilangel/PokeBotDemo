package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonJudgeCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCellTest {

	PokemonJudgeCell cell = new PokemonJudgeCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("Salut!")));
    }
	
	@Test
	public void testJudge() {
		PokeBot bot = new PokeBot("MagicarpeShiny");
		assertEquals(null, cell.ask(bot, new Tweet("Sarkon","-10pv /cc @pcreux")));
		
		bot.setJudge("PhoenixWright");
    	
    	assertEquals(null, cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @pcreux")));
    	
    	bot.setOwner("IAmGod");
    	
    	assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	
    	assertEquals(90,bot.getPv());

	}

}
