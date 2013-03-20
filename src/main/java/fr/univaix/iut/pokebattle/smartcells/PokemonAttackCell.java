package fr.univaix.iut.pokebattle.smartcells;

import fr.univaix.iut.pokebattle.PokeBot;
import fr.univaix.iut.pokebattle.SmartCell;
import fr.univaix.iut.pokebattle.Tweet;

public class PokemonAttackCell implements SmartCell {
		String skill;
		String target;
		
		
	    public String ask(PokeBot bot, Tweet question) {			

	    	if (question.getText().contains("#attack")) {
	    		String[] mots = question.getText().split(" ");
	    		for (int i = 0; i < mots.length; ++i) {
					if (mots[i].equals("#attack")){
						skill = mots[i+1];
						target = mots[i+2];					

					}		
				}
	    		
	    		String commande = target+" #attack "+skill+"! /cc @"+question.getScreenName();
	    		return commande;
	    	}
	    	return null;
	    }
}
