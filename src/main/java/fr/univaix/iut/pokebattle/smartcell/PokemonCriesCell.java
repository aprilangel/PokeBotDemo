package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class PokemonCriesCell implements SmartCell {

    public String ask(JPAPokemon bot, Tweet question) {
    	// Cri de guerre du Magicarpe viril et vaillant !
    	return "Carpe Carpe Magicarpe !";
    }

}
