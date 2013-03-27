package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonWinCell implements SmartCell {
			
		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getText().contains("#Win")) {
	    		bot.IsFighting = false;
	    		return " ";
	    	}	
	    	return null;
	    }
}
