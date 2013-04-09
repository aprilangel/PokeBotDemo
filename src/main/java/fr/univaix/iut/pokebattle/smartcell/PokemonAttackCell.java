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
		
		DataObjectAttack[] Attack = null;
		
        
	    public String ask(final PokeBot bot, Tweet question) {
	    	
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
		        	
		        	if (obj[i].getNom().equals(bot.getEspece()))
		        	{
		        		Attack = obj[i].getAttaques();
		        		break;
		        	}
		        	
		        }
		        
	    	}

	    	
	    	final Timer t = new Timer(3000, null);
	    	t.addActionListener(
	    		new ActionListener () 
		    	{
					public void actionPerformed(ActionEvent ae) 
					{
					   if (bot.getPv() >= (bot.getPvmax() - bot.getPvmax()/10))
						   bot.setPv(bot.getPvmax());
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
								skill = mots[i+1];
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
	    			for (int i = 0; i < Attack.length ; ++i)
	    			{
	    				// Si l'attaque existe
	    				if (Attack[i].getNom().equals(skill.substring(1)))
	    				{
	    					return  target+" #attack "+skill+"! /cc "+toname+" @"+question.getScreenName()+" @"+bot.getJudge();
	    				}
	    				
	    			}
	    			
	    			// Sinon
		    		return "@"+question.getScreenName()+" o_O ? /cc "+toname+" @"+bot.getJudge()+" "+target;
		    	}
	    		
	    		// Si le pokemon n'as pas d'owner
	    		else if (bot.getOwner() == null)
	    			return "@" + question.getScreenName() + " I have no owner";
	    		
	    		// Si ce n'est pas l'owner qui demande l'attaque
	    		else
	    			return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();

	    	}
	    	
	    	// Ce n'est pas une #attack
	    	return null;
	    	
	    } // ask()
	    
} // PokemonAttackCell
