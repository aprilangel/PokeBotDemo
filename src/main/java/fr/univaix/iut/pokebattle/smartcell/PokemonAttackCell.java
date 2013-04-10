package fr.univaix.iut.pokebattle.smartcell;

import java.util.Date;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		private String skill;
		private String target;
		private String toname;
		
		public String ask(final JPAPokemon bot, Tweet question) {
    	
	    	// Gerer les #attack
	    	if (question.getText().contains("#attack")) {

	    		if(question.getScreenName().equals(bot.getOwner())) {
	    			
	    			// Extraction des différents mots
	    			try {
	    				
	    				/* TODO
	    				 * Méthode des Pattern meilleur mais ne fonctionne pas
	    				 */
	    				
			    		String[] mots = question.getText().split(" ");
			    		for (int i = 0; i < mots.length; ++i) {
							if (mots[i].equals("#attack")){
								skill = mots[i+1].substring(1);
								target = mots[i+2];
								toname = mots[i+4];
								bot.setJudge(mots[i+5].substring(1));
	
							}		
						}
					
	    			}
	    			catch (Exception e) {
	    				return null;
	    			}			

	    			// Récuperer la date de la dernière attaque
	    			long lDateTime = new Date().getTime();
	    		    bot.setLastAtk(lDateTime);
	    			
	    			
	    			// Si on a pas planté, on est en combat contre toname
	    			bot.setFighting(toname);
	    			
	    			// Vérification de l'existence de l'attaque
	    			if(skill.equals(bot.getAtk1()))
	    			{	
	    				if(bot.getPp1()<= 0)
		    			{
		    				return "#" + skill + " n'a plus de PP. @" + question.getScreenName();
		    			}
	    				bot.setPp1(bot.getPp1()-1);
	    				
	    			}
	    			else if(skill.equals(bot.getAtk2()))
	    			{	
	    				if(bot.getPp2()<= 0)
		    			{
		    				return "#" + skill + " n'a plus de PP. @" + question.getScreenName();
		    			}
	    				bot.setPp2(bot.getPp2()-1);
	    				
	    			}	    				
	    			else if(skill.equals(bot.getAtk3()))
	    			{	
	    				if(bot.getPp3()<= 0)
		    			{
		    				return "#" + skill + " n'a plus de PP. @" + question.getScreenName();
		    			}
	    				bot.setPp3(bot.getPp3()-1);
	    				
	    			}	    			
	    			else if(skill.equals(bot.getAtk4()))
	    			{	
	    				if(bot.getPp4()<= 0)
		    			{
		    				return "#" + skill + " n'a plus de PP. @" + question.getScreenName();
		    			}
	    				bot.setPp4(bot.getPp4()-1);
	    				
	    			}
	    			else
	    			{
	    			return "@"+question.getScreenName()+" o_O ? /cc "+toname+" @"+bot.getJudge()+" "+target;
	    			}
	    			
	    		return  target+" #attack #"+skill+"! /cc "+toname+" @"+question.getScreenName()+" @"+bot.getJudge();
	    		}
   		
	    		// Si le pokemon n'as pas d'owner
	    		else if (bot.getOwner() == null) {
	    			return "@" + question.getScreenName() + " I have no owner";
	    		}
	    		// Si ce n'est pas l'owner qui demande l'attaque
	    		else {
	    			return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();
	    		}
	    	}
	    	
	    	// Ce n'est pas une #attack
	    	return null;
	    	
	    } // ask()
	    
} // PokemonAttackCell
