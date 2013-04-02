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
        assertEquals(null, cell.ask(new PokeBot("MagicarpeShiny"), new Tweet("Salut!")));
    }
	
	@Test
	public void testAttack() {
		PokeBot bot = new PokeBot("MagicarpeShiny");
		assertEquals("@Sarkon I have no owner", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
		
		bot.setOwner("Tenshi");
    	
    	assertEquals("@Sarkon my owner is @Tenshi", cell.ask(bot, new Tweet("Sarkon","#attack #foudre @bulbizare1")));
    	assertEquals("@NoctaliShiny #attack #Trempette! /cc @aStrangeCookie @Tenshi @PhoenixWright", cell.ask(bot, new Tweet("Tenshi","#attack #Trempette @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
    	assertEquals("@Tenshi o_O ? /cc @aStrangeCookie @PhoenixWright @NoctaliShiny", bot.ask(new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright")));
	}

	@Test
    public void testBool() {
		PokeBot b = new PokeBot("MagicarpeShiny");
		b.setOwner("Tenshi");
		assertEquals(0, b.getIsFighting());
		cell.ask(b, new Tweet("Tenshi","#attack #foudre @NoctaliShiny /cc @aStrangeCookie @PhoenixWright"));
        assertEquals(1, b.getIsFighting());
    }
	
}
