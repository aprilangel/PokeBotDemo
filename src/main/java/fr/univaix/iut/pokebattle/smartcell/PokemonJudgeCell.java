package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCell implements SmartCell {

		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getScreenName() != null && question.getScreenName().equals(bot.getJudge())) {
	    		
	    		try {
		    		String[] mots = question.getText().split(" ");

					int damage = Integer.parseInt(mots[0].substring(0,mots[0].length()-2));
					
					String ownercheck = mots[2].substring(1);
					
					if (!ownercheck.equals(bot.getOwner()))
						return null;
					
					
					
					if (bot.getPv() <= damage) 
					{
						bot.setPv(0);
						return "#KO /cc @" + bot.getJudge() + " @" + question.getScreenName() 
						+ " @" + bot.getOwner();
					}
					else
						bot.setPv(bot.getPv()-damage);
					
					return " ";
					
    			}
    			catch (Exception e) {
    				return null;
    			}
	    		
	    	}
	    	
	    	return null;
	    }
	    	

}
