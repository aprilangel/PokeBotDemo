package fr.univaix.iut.pokebattle.smartcell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.pokedex.DataObjectAttack;
import fr.univaix.iut.pokebattle.pokedex.DataObjectPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		String toname;
		
		DataObjectAttack[] Attack = null;
        
	    public String ask(PokeBot bot, Tweet question) {
	    	
	    	// Initialiser les attaques si on ne les as pas !
	    	if (Attack == null)
	    	{
				Gson gson = new Gson();

		        BufferedReader br = new BufferedReader(
		                               new InputStreamReader(PokemonAttackCell.class.getClassLoader().getResourceAsStream("pokedex.json")));

		        //convert the json string back to object
		        DataObjectPokemon[] obj = gson.fromJson(br, DataObjectPokemon[].class);
		        
		        for(int i = 0; i < obj.length; ++i)
		        {
		        	if (obj[i].getNom().equals("Magicarpe"))
		        	{
		        		Attack = obj[i].getAttaques();
		        		break;
		        	}
		        }
	    	}
	    	
	    	// Gerer les #attack
	    	if (question.getText().contains("#attack")) {

	    		if(question.getScreenName().equals(bot.getOwner())) {
	    			
	    			try {
	    				Pattern PNames = Pattern.compile("@([^ ]+)");
	    				Pattern PHash = Pattern.compile("@([^ ]+)");
	    				Matcher MNames = PNames.matcher(question.getText());
	    				Matcher MHash = PHash.matcher(question.getText());
	    				
	    				target = MNames.group(0);
	    				toname = MNames.group(1);
	    				bot.setJudge(MNames.group(2));
	    				skill = MHash.group(1);
	    				
	    				
	    				
	    				/* 
	    				 * Old way avec .split et .substring
	    				 * 
			    		String[] mots = question.getText().split(" ");
			    		for (int i = 0; i < mots.length; ++i) {
							if (mots[i].equals("#attack")){
								skill = mots[i+1];
								target = mots[i+2];
								toname = mots[i+4];
								bot.Judge = mots[i+5].substring(1);
	
							}		
						}
						*/
	    			}
	    			catch (Exception e) {
	    				return null;
	    			}			
	    			bot.setIsFighting(1);
	    			for (int i = 0; i < Attack.length ; ++i)
	    			{
	    				
	    				if (Attack[i].getNom().equals(skill.substring(1)))
	    				{
	    					return  target+" #attack "+skill+"! /cc "+toname+" @"+question.getScreenName()+" @"+bot.getJudge();
	    				}
	    				
	    			}
		    		return "@"+question.getScreenName()+" o_O ? /cc "+toname+" @"+bot.getJudge()+" "+target;
		    	}
	    		
	    		else if (bot.getOwner() == null)
	    			return "@" + question.getScreenName() + " I have no owner";
	    		
	    		else
	    			return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();

	    	}	
	    	return null;
	    }
}
