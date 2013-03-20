package fr.univaix.iut.pokebattle.smartcells;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonPokeballCell implements SmartCell {

	Twitter twitter = TwitterFactory.getSingleton();
	
    public String ask(PokeBot bot, Tweet question) {
if (question.getText().contains("Pokeball!")) {
    		if(bot.Owner == null) {
	    		bot.Owner = question.getScreenName();
	    		String newStatus = "#pokebattle - #pokemon - Owner: @" + question.getScreenName() + " - Craignez mon courroux";
	    		try {
					twitter.updateProfile("MagicarpeShiny", null, null, newStatus);
				} 
	    		catch (TwitterException e) 
	    		{
					e.printStackTrace();
				}
    		}
    		return "@" + question.getScreenName() + " my owner is @" + bot.Owner;
    	}
    		
    	return null;
    }
    
}