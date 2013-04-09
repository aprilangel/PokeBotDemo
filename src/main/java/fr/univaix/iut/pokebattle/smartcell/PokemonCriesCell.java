package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class PokemonCriesCell implements SmartCell {

    public String ask(JPAPokemon bot, Tweet question) {
    	/* Pattern p = Pattern.compile("([^ ]+) ");
    	Matcher m = p.matcher(question.getText());
    	if(m.find())
    	{
    		String nomPokemon = m.group(1);
    		return nomPokemon;
    	} */
    	
    	// Cri de guerre du Magicarpe viril et vaillant !
    	return "Carpe Carpe Magicarpe !";
    }

}
