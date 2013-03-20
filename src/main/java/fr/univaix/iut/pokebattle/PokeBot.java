package fr.univaix.iut.pokebattle;

import fr.univaix.iut.pokebattle.smartcells.*;


public class PokeBot implements Bot {
    /**
     * List of SmartCells the questions go through to
     * find an answer.
     */
	
	public String Owner = null;
	
    final SmartCell[] smartCells = new SmartCell[]{
    		new PokemonAttackCell(),
    		new PokemonPokeballCell(),
    		new PokemonOwnerNameCell(),
    		new PokemonInterlocuteurNameCell(),
    		new PokemonCriesCell(),
            
    };

    /**
     * Ask something to BoBot, it will respond to you.
     *
     * @param question The question you ask.
     * @return An answer... or null if it doesn't get it.
     */
    @Override
    public String ask(Tweet question) {
        for (SmartCell cell : smartCells) {
            String answer = cell.ask(this,question);
            if (answer != null)
                return answer;
        }
        return null;
    }

}
