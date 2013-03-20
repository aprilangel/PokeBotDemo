package fr.univaix.iut.pokebattle.smartcells;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonPokeballCell implements SmartCell {

	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();
	Twitter twitter = TwitterFactory.getSingleton();
	
    public String ask(Tweet question) {
    	if (question.getText().contains("owner?")) 
    		return cell.ask(question);
    	
    	if (question.getText().contains("Pokeball!")) {
    		if(cell.Owner == null){
    		cell.Owner = question.getScreenName();
    		String newStatus = "#pokebattle - #pokemon - Owner: @" + question.getScreenName() + " - Craignez mon courroux";
    		try {
				twitter.updateProfile("MagicarpeShiny", null, null, newStatus);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
    		}
    		return cell.ask(new Tweet(question.getScreenName(),"owner?"));
    	}
    		
    	return null;
    }
    
}