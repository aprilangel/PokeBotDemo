package fr.univaix.iut.pokebattle.smartcells;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		String toname;
		
		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getText().contains("#attack")) {

	    		if(question.getScreenName().equals(bot.Owner)) {
	    			
		    		String[] mots = question.getText().split(" ");
		    		for (int i = 0; i < mots.length; ++i) {
						if (mots[i].equals("#attack")){
							skill = mots[i+1];
							target = mots[i+2];
							toname = mots[i+4];

						}		
					}
		    		
		    		String commande = target+" #attack "+skill+"! /cc "+toname+" @"+question.getScreenName();
		    		return commande;
		    	}
	    		
	    		else if (bot.Owner == null)
	    			return "@" + question.getScreenName() + " I have no owner";
	    		
	    		else
	    			return "@" + question.getScreenName() + " my owner is @" + bot.Owner;

	    	}	
	    	return null;
	    }
}
