package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.Tweet;
import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.smartcells.PokemonAttackCell;

import org.junit.Test;

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

}
