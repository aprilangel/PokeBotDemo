package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonInterlocuteurNameCell implements SmartCell {

    public String ask(PokeBot bot, Tweet question) {
    	if (question.getScreenName() != null) 
    		return "@" + question.getScreenName() + " Carpe Carpe Magicarpe !";
    	return null;
    }
    
}
