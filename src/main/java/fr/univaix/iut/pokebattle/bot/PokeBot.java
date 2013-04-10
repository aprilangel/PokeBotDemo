package fr.univaix.iut.pokebattle.bot;

import javax.persistence.EntityManager;
import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.smartcell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonCenterCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonCriesCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonInterlocuteurNameCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonJudgeCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonOwnerNameCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonPokeballCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonStatCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokeBot implements Bot {
	

	// Variables de communication JPA
	private JPAPokemon jpa = null;
	
	public JPAPokemon testJPA ()
	{
		return jpa;
	}
	
	
	public PokeBot (String nom)
	{
		jpa = new JPAPokemon (nom);
	} // Pokebot ()
	
	// For test purpose
	public PokeBot (EntityManager emarg, String nom)
	{
		jpa = new JPAPokemon (emarg, nom);
	} // Pokebot
		

	
    /**
     * List of smartcell the questions go through to
     * find an answer.
     */
    private final SmartCell[] smartCells = new SmartCell[]
    {
    		new PokemonJudgeCell(),
    		new PokemonCenterCell(),
    		new PokemonStatCell(),
    		new PokemonAttackCell(),
    		new PokemonPokeballCell(),
    		new PokemonOwnerNameCell(),
    		new PokemonInterlocuteurNameCell(),
    		new PokemonCriesCell()
            
    };

    /**
     * Ask something to Bot, it will respond to you.
     *
     * @param question The question you ask.
     * @return An answer... or null if it doesn't get it.
     */
    @Override
    public String ask(Tweet question) {
        for (SmartCell cell : smartCells) {
            String answer = cell.ask(jpa,question);
            if (answer != null)
            {
            	// Réponse spéciale quand le Pokémon ne dois pas répondre
            	if (answer.equals(" ")) {
            		return null;
            	}
            	return answer;
            }
        }
        return null;
    }

}
