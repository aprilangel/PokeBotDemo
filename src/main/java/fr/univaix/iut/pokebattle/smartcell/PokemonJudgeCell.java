package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCell implements SmartCell {

		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getScreenName() != null && question.getScreenName().equals(bot.getJudge())) {
	    		
	    		try {
		    		String[] mots = question.getText().split(" ");

		    		int damage = 0;
		    		//String ownercheck = null;
		    		for (int i = 0; i < mots.length - 1 ; ++i )
					{
		    			if (mots[i+1].equals("/cc"))
		    			{
		    				//ownercheck = mots[2].substring(1);
		    				damage = Integer.parseInt(mots[i].substring(0,mots[i].length()-2));
		    			}
					}
					
					
					
					//if (!ownercheck.equals(bot.getOwner()))
					//	return null;
					
					
					
					if (bot.getPv() + damage <= 0) 
					{
						bot.setPv(0);
						return "#KO /cc @" + bot.getJudge() + " @" + question.getScreenName() 
						+ " @" + bot.getOwner();
					}
					else
						bot.setPv(bot.getPv()+damage);
					
					return " ";
					
    			}
    			catch (Exception e) {
    				return null;
    			}
	    		
	    	}
	    	
	    	return null;
	    }
	    	

}
