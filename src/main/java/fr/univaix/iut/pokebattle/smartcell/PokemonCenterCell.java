package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonCenterCell implements SmartCell {
	
	public PokemonCenterCell() {
		
	}
	
	public String ask(JPAPokemon bot, Tweet question) {

		// Extraction des mots
    	Pattern p = Pattern.compile("come in the #pokecenter");
    	Matcher m = p.matcher(question.getText());
    	
    	// Si le message contient "come in the #pokecenter"
    	if(m.find())
    	{
    		if (bot.getFighting() != null) {
    			return "@" + question.getScreenName() + " only cowards flee from fight! /cc @" + bot.getOwner();
    		}
    		else if (bot.getNurse() != null) {
    			return "@" + question.getScreenName() + " already in a #pokecenter /cc @" + bot.getOwner();
    		}
    		else {
    			bot.setNurse(question.getScreenName());
    			return "@" + question.getScreenName() + " let's heal /cc @" + bot.getOwner();
    		}
    	}	
	return null;


	}
}