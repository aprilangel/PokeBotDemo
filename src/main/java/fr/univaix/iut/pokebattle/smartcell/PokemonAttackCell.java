package fr.univaix.iut.pokebattle.smartcell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokemonAttackCell implements SmartCell {
		private String skill;
		private String target;
		private String toname;
		
		// Constante de divison pour la régen de pv (ici, 1/10 des PV Max)
		private final int kREGEN = 10;
		
        
	    public String ask(final JPAPokemon bot, Tweet question) {
    	
	    	final Timer t = new Timer(3000, null);
	    	t.addActionListener(
	    		new ActionListener () 
		    	{
					public void actionPerformed(ActionEvent ae) 
					{
					   if (bot.getPv() >= (bot.getPvmax() - bot.getPvmax() / kREGEN)) {
						   bot.setPv(bot.getPvmax());
					   }
					   else
					   {
						   bot.setPv(bot.getPv()+ (bot.getPvmax() / kREGEN));
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
