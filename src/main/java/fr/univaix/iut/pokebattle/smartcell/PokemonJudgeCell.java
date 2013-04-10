package fr.univaix.iut.pokebattle.smartcell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonJudgeCell implements SmartCell {

		
	    public String ask(JPAPokemon bot, Tweet question) {
	    	
	    	if (question.getScreenName() != null && question.getScreenName().equals(bot.getJudge())) {
	    		
	    		// Cas du #Win
		    	if (question.getText().contains("#Win")) {
		    		bot.setFighting(null);
		    		// Extraction des mots
		        	Pattern p = Pattern.compile("#Win \\+([^ ]+)+xp");
		        	Matcher m = p.matcher(question.getText());
		        	
		        	// Si le message contient #Win +[INSERT NUMBER HERE]xp
		        	if(m.find())
		        	{
		        		// Extraction de la valeur d'xp
		        		String ValeurXp = m.group(1);
		        		
		        		bot.setExp(bot.getExp()+Integer.parseInt(ValeurXp));
		        		return " ";
		        	}
		        	return " ";
		    	}
	    		
		    	// Autres messages que le Juge peux dire
	    		try {
	    			/*
	    			 * TODO
	    			 * 
	    			 * Les splits c'est mal, ici aussi, il faudra mettre des patterns
	    			 * 
	    			 */
	    			
	    			// Extraction des différents mots
		    		String[] mots = question.getText().split(" ");
		    		
		    		// Phrase trop courte
		    		if (mots.length < 3)
		    		{
		    			return null;
		    		}

		    		int damage = 0;
		    		for (int i = 0; i < mots.length - 1 ; ++i )
					{
		    			if (mots[i+1].equals("/cc"))
		    			{
		    				damage = Integer.parseInt(mots[i].substring(0,mots[i].length()-2));
		    			}
					}
		    		
					
					// Est-ce que notre vaillant pokémon va t'il succomber a ses blessures ?
					if (bot.getPv() + damage <= 0) 
					{
						bot.setPv(0);
						return "#KO /cc @" + bot.getJudge() + " " + bot.getFighting()
						+ " @" + bot.getOwner();
					}
					
					// Ou va t'il survivre ?
					else
					{
						bot.setPv(bot.getPv()+damage);
					}
						
					// Renvoyer null reviens a dire que c'est pas a cette cell de s'occuper du message
					// Dans notre cas, c'est bien cette cell, mais on a rien a répondre
					// Donc on renvoie ce message qui sera interprété comme "Ne pas répondre"
					return " ";
					
    			}
	    		
	    		// Shit happends sometimes
    			catch (Exception e) 
    			{
    				return null;
    			}
	    		
	    	}
	    	
	    	// Ce n'est pas notre juge qui nous parle
	    	return null;	
	    }
}   
