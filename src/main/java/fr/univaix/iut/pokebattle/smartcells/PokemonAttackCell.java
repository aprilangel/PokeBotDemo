package fr.univaix.iut.pokebattle.smartcells;

import twitter4j.Twitter;
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
		User targetowner;
		Twitter twitter = TwitterFactory.getSingleton();
		
		
	    public String ask(PokeBot bot, Tweet question) {			
	    	if (question.getText().contains("#attack")) {
	    		if(bot.Owner == question.getScreenName()) {
		    		String[] mots = question.getText().split(" ");
		    		for (int i = 0; i < mots.length; ++i) {
						if (mots[i].equals("#attack")){
							skill = mots[i+1];
							target = mots[i+2];					

						}		
					}
		    		
		    		// Get Enemy Owner Name
		    		try {
						targetowner = twitter.showUser(target);
						String TabS[] = targetowner.getDescription().split(" ");
						
						int i = 0;
						while (!TabS[i].equals("Owner:"))
							++i;
						toname = "@"+TabS[++i].substring(1)+" ";
					} 
		    		catch (TwitterException e) {
						// TODO Auto-generated catch block
						toname = "";
					}
		    		
		    		
		    		String commande = target+" #attack "+skill+"! /cc "+toname+"@"+question.getScreenName();
		    		return commande;
		    	}
	    		else if (bot.Owner == null)
	    			return "@" + question.getScreenName() + " I have no owner";
	    		return "@" + question.getScreenName() + " my owner is @" + bot.Owner; 
	    	}	
	    	return null;
	    }
}
