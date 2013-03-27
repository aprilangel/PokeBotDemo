package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		String toname;
		
		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getText().contains("#attack")) {

	    		if(question.getScreenName().equals(bot.Owner)) {
	    			
	    			try {
			    		String[] mots = question.getText().split(" ");
			    		for (int i = 0; i < mots.length; ++i) {
							if (mots[i].equals("#attack")){
								skill = mots[i+1];
								target = mots[i+2];
								toname = mots[i+4];
								bot.Judge = mots[i+5].substring(1);
	
							}		
						}
	    			}
	    			catch (Exception e) {
	    				return null;
	    			}
		    		bot.IsFighting = true;
		    		String commande = target+" #attack "+skill+"! /cc "+toname+" @"+question.getScreenName()+" @"+bot.Judge;
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
