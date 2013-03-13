package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonInterlocuteurNameCell implements SmartCell {

    public String ask(Tweet question) {
    	if (question.getScreenName() != null) 
    		return "@" + question.getScreenName() + " Carpe Carpe Magicarpe !";
    	return null;
    }
    
}
