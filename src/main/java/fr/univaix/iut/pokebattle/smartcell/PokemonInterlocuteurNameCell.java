package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonInterlocuteurNameCell implements SmartCell {

    public String ask(JPAPokemon bot, Tweet question) {
    	
    	// Renvoyer le nom de la personne qui nous parle si il existe
    	if (question.getScreenName() != null) {
    		return "@" + question.getScreenName() + " Carpe Carpe Magicarpe !";
    	}
    	return null;
    }
    
}
