package fr.univaix.iut.pokebattle.smartcell;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

	/**
	 * Reply to all.
	 */
public class PokemonPokeballCell implements SmartCell {

	private Twitter twitter = TwitterFactory.getSingleton();
	
    public String ask(JPAPokemon bot, Tweet question) {

    	// Tentative de capture ?
    	if (question.getText().contains("Pokeball!")) {
    		
    		// Le pokémon ne dois pas déjà avoir son maître
    		if(bot.getOwner() == null) {
    			
    			// Enregistrement de l'owner
	    		bot.setOwner(question.getScreenName());
	    		
	    		// Mise a jour du status
	    		String newStatus = "#pokebattle - #pokemon - Owner: @" + question.getScreenName() + " - Level: " + bot.getLevel();

	    		try {
					twitter.updateProfile("MagicarpeShiny", null, null, newStatus);
				} 
	    		catch (TwitterException e) 
	    		{
	    			System.err.println("Error in PokemonPokeballCell.java : \n"+e);
				}
    		}
    		
    		// Le vol, c'est mal
    		return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();
    	}
    		
    	return null;
    }
    
}