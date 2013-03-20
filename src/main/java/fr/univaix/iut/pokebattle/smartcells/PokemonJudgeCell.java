package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

public class PokemonJudgeCell implements SmartCell {

		
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	if (question.getScreenName() != null && question.getScreenName().equals(bot.Judge)) {
	    		
	    		try {
		    		String[] mots = question.getText().split(" ");

					int damage = Integer.parseInt(mots[0].substring(0,mots[0].length()-2));
					
					String ownercheck = mots[2].substring(1);
					
					if (!ownercheck.equals(bot.Owner))
						return null;
					
					bot.PV += damage;
					
					return " ";

    			}
    			catch (Exception e) {
    				return null;
    			}
	    		
	    	}
	    	
	    	return null;
	    }
	    	

}
