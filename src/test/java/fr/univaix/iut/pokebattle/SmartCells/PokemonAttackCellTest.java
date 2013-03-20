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
	public void testCarambar() {
		assertEquals("@NoctaliShiny #attack #foudre! /cc @aStrangeCookie @Tenshi", cell.ask(new PokeBot(), new Tweet("Tenshi","#attack #foudre @NoctaliShiny")));
	}

}
