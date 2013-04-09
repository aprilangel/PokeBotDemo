package fr.univaix.iut.pokebattle.bot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univaix.iut.pokebattle.jpa.DAOPokebotJPA;
import fr.univaix.iut.pokebattle.jpa.Pokebot;
import fr.univaix.iut.pokebattle.smartcell.PokemonAttackCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonCriesCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonInterlocuteurNameCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonJudgeCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonOwnerNameCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonPokeballCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonStatCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;


public class PokeBot implements Bot {
	

	/*
	 * Anciennes variables, maintenant inutiles car remplacées par le fr.univaix.iut.pokebattle.jpa.Pokebot
	 * 
	public boolean IsFighting = false;
	public String Owner = null;
	public int PV = 100;
	public int PVmax = 100;
	public String Judge = null;
	public String espece = "Magicarpe";
	public String level = "1"; 
	public String XP = "0";
	 *
	 */
	
	EntityManagerFactory emf = null;
    EntityManager em = null;
	public Pokebot data;
	DAOPokebotJPA jpa = null;
	
	public PokeBot (String nom)
	{
		emf = Persistence.createEntityManagerFactory("pokebattlePU");
        em = emf.createEntityManager();
        jpa = new DAOPokebotJPA(em);
        data = jpa.getById(nom);
	}
	
	// For test purpose
	public PokeBot (EntityManager emarg, String nom)
	{

        this.em = emarg;
        jpa = new DAOPokebotJPA(emarg);
        data = jpa.getById(nom);
	}
		
	/*
	 * Getter et setters pour communiquer entre la base de données et les modifications du Pokebot
	 */
	
	
	public String getNom() {
		return data.getNom();
	}

	public void setNom(String nom) {
		data.setNom(nom);
		jpa.update(data);
	}

	public String getFighting() {
		return data.getFighting();
	}

	public void setFighting(String Fighting) {
		data.setFighting(Fighting);
		jpa.update(data);
	}

	public int getExp() {
		return data.getExp();
	}

	public void setExp(int exp) {
		data.setExp(exp);
		jpa.update(data);
	}

	public int getLevel() {
		return data.getLevel();
	}

	public void setLevel(int level) {
		data.setLevel(level);
		jpa.update(data);
	}

	public String getOwner() {
		return data.getOwner();
	}

	public void setOwner(String owner) {
		data.setOwner(owner);
		jpa.update(data);
	}

	public String getJudge() {
		return data.getJudge();
	}

	public void setJudge(String judge) {
		data.setJudge(judge);
		jpa.update(data);
	}

	public int getPv() {
		return data.getPv();
	}

	public void setPv(int pv) {
		data.setPv(pv);
		jpa.update(data);
	}

	public String getEspece() {
		return data.getEspece();
	}

	public void setEspece(String espece) {
		data.setEspece(espece);
		jpa.update(data);
	}

	public int getPvmax() {
		return data.getPvmax();
	}

	public void setPvmax(int pvmax) {
		data.setPvmax(pvmax);
		jpa.update(data);
	}
	
	

	
    /**
     * List of smartcell the questions go through to
     * find an answer.
     */
    final SmartCell[] smartCells = new SmartCell[]
    {
    		new PokemonJudgeCell(),
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
