package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonOwnerNameCell implements SmartCell {
	
   public String ask(PokeBot bot, Tweet question) {
    	if (question.getText().contains("owner?") || question.getText().contains("Owner?")) 
    		if (bot.Owner != null)
    			return "@" + question.getScreenName() + " my owner is @" + bot.Owner;
    		else
    			return "@" + question.getScreenName() + " I have no owner";
    	return null;
    }
    
}
