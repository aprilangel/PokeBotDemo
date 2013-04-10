package fr.univaix.iut.pokebattle.smartcell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import fr.univaix.iut.pokebattle.jpa.JPAPokemon;
import fr.univaix.iut.pokebattle.pokedex.DataObjectAttack;
import fr.univaix.iut.pokebattle.pokedex.DataObjectPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCell implements SmartCell {
	
	private DataObjectAttack[] attack;
	private String ppmax;
	private String puissance;
	private String precision;
	
	public PokemonStatCell() {
		Gson gson = new Gson();
		
		BufferedReader br = new BufferedReader(
		                       new InputStreamReader(PokemonAttackCell.class.getClassLoader().getResourceAsStream("pokedex.json")));
		
		//convert the json string back to object
		DataObjectPokemon[] obj = gson.fromJson(br, DataObjectPokemon[].class);
		
		for(int i = 0; i < obj.length; ++i)
		{
			if (obj[i].getNom().equals("Magicarpe"))
			{
				attack = obj[i].getAttaques();
				break;
			}
		}
	}
	
	public String ask(JPAPokemon bot, Tweet question) {
		
	

		// Extraction des mots
    	Pattern p = Pattern.compile("#stat #([^ ]+) ");
    	Matcher m = p.matcher(question.getText());
    	
    	// Si le message contient #stats #<stat demandée>
    	if(m.find())
    	{
    		// Extraction de la stat demandée
    		String typeStat = m.group(1);
    		
    		if (typeStat.equals("level")) {
    			return "@"+question.getScreenName()+" Je suis niveau "+bot.getLevel();
    		}
    		else if (typeStat.equals("XP")) {
    			return "@"+question.getScreenName()+" J'ai "+bot.getExp()+" points d'experience";
    		}
    		else if (typeStat.equals("PV")) {
    			return "@"+question.getScreenName()+" PV : "+bot.getPv() + "/" + bot.getPvmax();
    		}
    		// Stat inconnue
    		else {
    			return "@"+question.getScreenName()+" Magi Magi ?";
    		}
    	}
    	Pattern p1 = Pattern.compile("#statAttack #([^ ]+) #([^ ]+)");
    	Matcher m1 = p1.matcher(question.getText());
    	if(m1.find())
    	{
    		
    		String typeStatattack = m1.group(1);

	    	String skill = m1.group(2);
	    	
	    	
    	
	    	for (int i = 0; i < attack.length ; ++i)
			{
				
				if (attack[i].getNom().equals(skill))
				{
					ppmax = attack[i].getPp();
					puissance = attack[i].getPuissance();
					precision = attack[i].getPrecision();
				}
			}
	    	if (typeStatattack.equals("PP"))
	    	{
		    	if(skill.equals(bot.getAtk1())) { return "@"+question.getScreenName()+" "+bot.getAtk1()+" - PP : "+bot.getPp1() + "/" + ppmax; }
		    	
		    	if(skill.equals(bot.getAtk2())) { return "@"+question.getScreenName()+" "+bot.getAtk2()+" - PP : "+bot.getPp2() + "/" + ppmax; }
		    	
		    	if(skill.equals(bot.getAtk3())) { return "@"+question.getScreenName()+" "+bot.getAtk3()+" - PP : "+bot.getPp3() + "/" + ppmax; }
		        
		    	if(skill.equals(bot.getAtk4())) { return "@"+question.getScreenName()+" "+bot.getAtk4()+" - PP : "+bot.getPp4() + "/" + ppmax; }
	    	}
    		else if (typeStatattack.equals("Puissance")) {
    			return "@"+question.getScreenName()+" "+bot.getAtk1()+" - Puissance : "+puissance;
    		}
    		else if (typeStatattack.equals("Precision")) {
    			return "@"+question.getScreenName()+" "+bot.getAtk1()+" - Precision : "+precision;
    		}
    		else {
    			return "@"+question.getScreenName()+" Magi Magi ?";
    		}
	    		
	}
	return null;


	}
}