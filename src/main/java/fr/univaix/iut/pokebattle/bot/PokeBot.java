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
	

	// Variables de communication JPA
	private EntityManagerFactory emf = null;
    private EntityManager em = null;
	private Pokebot data;
	private DAOPokebotJPA jpa = null;
	
	
	public PokeBot (String nom)
	{
		emf = Persistence.createEntityManagerFactory("pokebattlePU");
        em = emf.createEntityManager();
        jpa = new DAOPokebotJPA(em);
        data = jpa.getById(nom);
	} // Pokebot ()
	
	// For test purpose
	public PokeBot (EntityManager emarg, String nom)
	{

        this.em = emarg;
        jpa = new DAOPokebotJPA(emarg);
        data = jpa.getById(nom);
	} // Pokebot
		
	
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

	public void setFighting(String fighting) {
		data.setFighting(fighting);
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
	
	public String getAtk1() {
		return data.getAtk1();
	}

	public void setAtk1(String atk1) {
		data.setAtk1(atk1);
		jpa.update(data);
	}
	
	public String getAtk2() {
		return data.getAtk2();
	}

	public void setAtk2(String atk2) {
		data.setAtk2(atk2);
		jpa.update(data);
	}
	
	public String getAtk3() {
		return data.getAtk3();
	}

	public void setAtk3(String atk3) {
		data.setAtk3(atk3);
		jpa.update(data);
	}

	public String getAtk4() {
		return data.getAtk4();
	}

	public void setAtk4(String atk4) {
		data.setAtk4(atk4);
		jpa.update(data);
	}
	 
	public int getPp1() {
		return data.getPp1();
	}

	public void setPp1(int pp1) {
		data.setPp1(pp1);
		jpa.update(data);
	}
	
	public int getPp2() {
		return data.getPp2();
	}

	public void setPp2(int pp2) {
		data.setPp2(pp2);
		jpa.update(data);
	}
	
	public int getPp3() {
		return data.getPp3();
	}

	public void setPp3(int pp3) {
		data.setPp3(pp3);
		jpa.update(data);
	}

	public int getPp4() {
		return data.getPp4();
	}

	public void setPp4(int pp4) {
		data.setPp4(pp4);
		jpa.update(data);
	}
	
    /**
     * List of smartcell the questions go through to
     * find an answer.
     */
    private final SmartCell[] smartCells = new SmartCell[]
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
