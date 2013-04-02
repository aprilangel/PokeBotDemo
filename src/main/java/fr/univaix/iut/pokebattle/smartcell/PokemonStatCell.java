package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCell implements SmartCell {
    
	public String ask(PokeBot bot, Tweet question) {
	
		if (question.getText().contains("#stat"))
		{	
	    	Pattern p = Pattern.compile("#([^ ]+) ");
	    	Matcher m = p.matcher(question.getText());
	    	if(m.matches())
	    	{
	    		String TypeStat = m.group(2);
	    		if (TypeStat.equals("level"))
	    			return null;
	    		else if (TypeStat.equals("XP"))
	    			return null;
	    		else if (TypeStat.equals("PV"))
	    			return null;
	    	}
	        return null;
    
		}
		return null;

	}
}