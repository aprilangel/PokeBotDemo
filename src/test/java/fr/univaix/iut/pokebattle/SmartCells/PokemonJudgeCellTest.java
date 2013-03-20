package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.smartcells.PokemonJudgeCell;

import org.junit.Test;

public class PokemonJudgeCellTest {

	PokemonJudgeCell cell = new PokemonJudgeCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot(), new Tweet("Salut!")));
    }
	
	@Test
	public void testJudge() {
		PokeBot bot = new PokeBot();
		assertEquals(null, cell.ask(bot, new Tweet("Sarkon","-10pv /cc @pcreux")));
		
		bot.Judge = "PhoenixWright";
    	
    	assertEquals(null, cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @pcreux")));
    	
    	bot.Owner = "IAmGod";
    	
    	assertEquals(" ", cell.ask(bot, new Tweet("PhoenixWright","-10pv /cc @IAmGod")));
    	
    	assertEquals(90,bot.PV);

	}

}
