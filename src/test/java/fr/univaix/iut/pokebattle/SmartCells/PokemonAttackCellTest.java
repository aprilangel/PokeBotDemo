package fr.univaix.iut.pokebattle.SmartCells;

import fr.univaix.iut.pokebattle.Tweet;
import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.smartcells.PokemonAttackCell;

import org.junit.Test;

public class PokemonAttackCellTest {

	PokemonAttackCell cell = new PokemonAttackCell();
	
    @Test
    public void testNull() {
        assertEquals(null, cell.ask(new Tweet("Salut!")));
    }
	
	@Test
	public void testCarambar() {
		assertEquals("@bulbizare1 #attack #foudre! /cc @Sarkon", cell.ask(new Tweet("Sarkon","#attack #foudre @bulbizare1")));
	}

}
