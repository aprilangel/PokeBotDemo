package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class PokemonCriesCell implements SmartCell {

    public String ask(PokeBot bot, Tweet question) {
    	Pattern p = Pattern.compile("@([^ ]+) ");
    	Matcher m = p.matcher(question.getText());
    	if(m.matches())
    	{
    		String nomPokemon = m.group(1);
    		return nomPokemon + "Carpe Carpe Magicarpe !";
    	}
        return null;
    }

}
