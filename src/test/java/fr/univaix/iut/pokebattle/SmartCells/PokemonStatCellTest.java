package fr.univaix.iut.pokebattle.SmartCells;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.PokemonStatCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCellTest {

    PokemonStatCell cell = new PokemonStatCell();
	
	@Test
	public void testLevel() {
		assertEquals("1", cell.ask (new PokeBot (), 
				new Tweet ("@MagicarpeShiny Quel est ton #stat #level ?")));
	}
	
    @Test
    public void testXP() {
    	assertEquals("0", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Quel est ton #stat #XP ?")));
    }
    
    @Test
    public void testPV() {
    	assertEquals("100/100", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #stat #PV ?")));
    }
    
    @Test
    public void testInconnu() {
    	assertEquals("Magi Magi ?", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #stat #PL ?")));
    }
    
    
//Tests pour les attaques
    
    
    @Test
    public void testPP() {
    	assertEquals("35/35", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #statAttack #PP #Charge ?")));
    }
    
    @Test
    public void testPuissance() {
    	assertEquals("35", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #statAttack #Puissance #Charge ?")));
    }
    
    @Test
    public void testPrecision() {
    	assertEquals("95", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #statAttack #Precision #Charge ?")));
    }
    
    @Test
    public void testInconnu2() {
    	assertEquals("Magi Magi ?", cell.ask (new PokeBot (), 
    			new Tweet ("@MagicarpeShiny Combien as-tu de #stat #PP ?")));
    }

    
// Fin des tests
}
