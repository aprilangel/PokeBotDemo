package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;


public class PokemonWinCell implements SmartCell {
			
		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getText().contains("#Win")) {
	    		bot.IsFighting = false;
	    		return " ";
	    	}	
	    	return null;
	    }
}
