package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
	 * Reply to all.
	 */
public class PokemonOwnerNameCell implements SmartCell {
	
   public String ask(PokeBot bot, Tweet question) {
	   
	   // Dis moi gentil pokémon, qui c'est ton maitre ?
	   if (question.getText().contains("owner?") || question.getText().contains("Owner?")) { 
		   if (bot.getOwner() != null) {
			   return "@" + question.getScreenName() + " my owner is @" + bot.getOwner();
		   }
		   else {
			   return "@" + question.getScreenName() + " I have no owner";
		   }
	   }
	   return null;
   	}
    
}
