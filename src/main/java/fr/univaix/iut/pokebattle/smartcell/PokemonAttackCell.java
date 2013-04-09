package fr.univaix.iut.pokebattle.smartcell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.Timer;
import com.google.gson.Gson;
import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.pokedex.DataObjectAttack;
import fr.univaix.iut.pokebattle.pokedex.DataObjectPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		String toname;
		
		
        
	    public String ask(final PokeBot bot, Tweet question) {
    	
	    	final Timer t = new Timer(3000, null);
	    	t.addActionListener(
	    		new ActionListener () 
		    	{
					public void actionPerformed(ActionEvent ae) 
					{
					   if (bot.getPv() >= (bot.getPvmax() - bot.getPvmax()/10)) {
						   bot.setPv(bot.getPvmax());
					   }
					   else
					   {
						   bot.setPv(bot.getPv()+bot.getPvmax()/10);
						   t.restart();
					   }
					}
				}
	    	);
	    	
	    	// Gerer les #attack
	    	if (question.getText().contains("#attack")) {

	    		if(question.getScreenName().equals(bot.getOwner())) {
	    			
	    			// Extraction des différents mots
	    			try {
	    				
	    				/* TODO
	    				 * A corriger
	    				 * Méthode des Pattern meilleur mais ne fonctionne pas
	    				
	    				Pattern PNames = Pattern.compile("@([^ ]+)");
	    				Pattern PHash = Pattern.compile("@([^ ]+)");
	    				Matcher MNames = PNames.matcher(question.getText());
	    				Matcher MHash = PHash.matcher(question.getText());
	    				
	    				target = MNames.group(1);
	    				toname = MNames.group(2);
	    				bot.setJudge(MNames.group(3));
	    				skill = MHash.group(2);
	    				
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

	    			// Si on a pas planté, on est en combat contre toname
	    			bot.setFighting(toname);
	    			
	    			// Combat donc reset de la regen
	    			t.restart();
	    			
	    			// Vérification de l'existence de l'attaque
	    			if(skill.equals(bot.getAtk1()))
	    			{				
	    				bot.setPp1(bot.getPp1()-1);
	    			}
	    			else if(skill.equals(bot.getAtk2()))
	    			{				
	    				bot.setPp2(bot.getPp2()-1);
	    			}	    				
	    			else if(skill.equals(bot.getAtk3()))
	    			{				
	    				bot.setPp3(bot.getPp3()-1);
	    			}	    			
	    			else if(skill.equals(bot.getAtk4()))
	    			{				
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
