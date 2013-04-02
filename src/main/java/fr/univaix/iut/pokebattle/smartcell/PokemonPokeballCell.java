package fr.univaix.iut.pokebattle.smartcell;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonPokeballCell implements SmartCell {

	Twitter twitter = TwitterFactory.getSingleton();
	
    public String ask(PokeBot bot, Tweet question) {
if (question.getText().contains("Pokeball!")) {
    		if(bot.getOwner() == null) {
	    		bot.setOwner(question.getScreenName());
	    		String newStatus = "#pokebattle - #pokemon - Owner: @" + question.getScreenName() + " - Craignez mon courroux";
	    		try {
					twitter.updateProfile("MagicarpeShiny", null, null, newStatus);
				} 
	    		catch (TwitterException e) 
	    		{
					e.printStackTrace();
				}
    		}
    		return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();
    	}
    		
    	return null;
    }
    
}