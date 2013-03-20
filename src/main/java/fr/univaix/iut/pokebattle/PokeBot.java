package fr.univaix.iut.pokebattle;

import fr.univaix.iut.pokebattle.smartcells.*;


public class PokeBot implements Bot {
    /**
     * List of SmartCells the questions go through to
     * find an answer.
     */
	public boolean IsFighting = false;
	public String Owner = null;
	public int PV = 100;
	public int PVmax = 100;
	public String Judge = null;
	
    final SmartCell[] smartCells = new SmartCell[]{
    		new PokemonJudgeCell(),
    		new PokemonAttackCell(),
    		new PokemonPokeballCell(),
    		new PokemonOwnerNameCell(),
    		new PokemonInterlocuteurNameCell(),
    		new PokemonCriesCell()
            
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
            {
            	if (answer.equals(" "))
            		return null;
            	return answer;
            }
        }
        return null;
    }

}
