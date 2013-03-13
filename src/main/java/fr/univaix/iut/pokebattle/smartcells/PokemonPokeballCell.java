package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonPokeballCell implements SmartCell {

	PokemonOwnerNameCell cell = new PokemonOwnerNameCell();
	
    public String ask(Tweet question) {
    	if (question.getText().contains("owner?")) 
    		return cell.ask(question);
    	
    	if (question.getText().contains("Pokeball!")) {
    		cell.Owner = question.getScreenName();
    		return cell.ask(new Tweet(question.getScreenName(),"owner?"));
    	}
    		
    	return null;
    }
    
}