package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCell implements SmartCell {
    
	public String ask(PokeBot bot, Tweet question) {
		
		// Extraction des mots
    	Pattern p = Pattern.compile("#stat #([^ ]+) ");
    	Matcher m = p.matcher(question.getText());
    	
    	// Si le message contient #stats #<stat demandée>
    	if(m.find())
    	{
    		// Extraction de la stat demandée
    		String TypeStat = m.group(1);
    		
    		if (TypeStat.equals("level")) {
    			return ""+bot.getLevel();
    		}
    		else if (TypeStat.equals("XP")) {
    			return ""+bot.getExp();
    		}
    		else if (TypeStat.equals("PV")) {
    			return bot.getPv() + "/" + bot.getPvmax();
    		}
    		// Stat inconnue
    		else {
    			return "Magi Magi ?";
    		}
    	}
    	
        return null;

	}
}