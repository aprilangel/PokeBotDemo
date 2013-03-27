package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonAttackCellTest {

	PokemonAttackCell cell = new PokemonAttackCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new PokeBot(), new Tweet("Salut!")));
    }
	
	@Test
	public void testAttack() {
		PokeBot bot = new PokeBot();
		assertEquals("@Sarkon I have no owner", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
		
		bot.Owner = "Tenshi";
    	
    	assertEquals("@Sarkon my owner is @Tenshi", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	
    	assertEquals("@NoctaliShiny #attack #foudre! /cc @aStrangeCookie @Tenshi @PhoenixWright", cell.ask(bot, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));

	}

	@Test
    public void testBool() {
		PokeBot b = new PokeBot();
		b.Owner = "Tenshi";
		assertEquals(false, b.IsFighting);
		cell.ask(b, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright"));
        assertEquals(true, b.IsFighting);
    }
	
}
