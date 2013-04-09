package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCell implements SmartCell {
    
	public String ask(PokeBot bot, Tweet question) {
		
	    	Pattern p = Pattern.compile("#stat #([^ ]+) ");
	    	Matcher m = p.matcher(question.getText());
	    	if(m.find())
	    	{
	    		String TypeStat = m.group(1);
	    		if (TypeStat.equals("level"))
	    			return ""+bot.getLevel();
	    		else if (TypeStat.equals("XP"))
	    			return ""+bot.getExp();
	    		else if (TypeStat.equals("PV"))
	    			return bot.getPv() + "/" + bot.getPvmax();
	    		else return "Magi Magi ?";
	    	}
	        return null;

	}
}