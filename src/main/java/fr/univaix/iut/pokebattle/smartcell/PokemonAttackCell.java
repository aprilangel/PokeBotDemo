package fr.univaix.iut.pokebattle.smartcell;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import scala.App;
import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.pokedex.DataObjectAttack;
import fr.univaix.iut.pokebattle.pokedex.DataObjectPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		String toname;
		
		DataObjectAttack[] Attack;
		
		public PokemonAttackCell() {
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
	    			for (int i = 0; i < Attack.length ; ++i)
	    			{
	    				
	    				if (Attack[i].getNom().equals(skill.substring(1)))
	    				{
	    					return  target+" #attack "+skill+"! /cc "+toname+" @"+question.getScreenName()+" @"+bot.Judge;
	    				}
	    				
	    			}
		    		return target+" o_O ? /cc "+toname+" @"+question.getScreenName()+" @"+bot.Judge;
		    	}
	    		
	    		else if (bot.Owner == null)
	    			return "@" + question.getScreenName() + " I have no owner";
	    		
	    		else
	    			return "@" + question.getScreenName() + " my owner is @" + bot.Owner;

	    	}	
	    	return null;
	    }
}
