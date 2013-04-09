package fr.univaix.iut.pokebattle.smartcell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import fr.univaix.iut.pokebattle.bot.PokeBot;
import fr.univaix.iut.pokebattle.pokedex.DataObjectAttack;
import fr.univaix.iut.pokebattle.pokedex.DataObjectPokemon;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class PokemonStatCell implements SmartCell {
	
	DataObjectAttack[] Attack;
	String PPmax;
	String Puissance;
	String Precision;
	
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
				Attack = obj[i].getAttaques();
				break;
			}
		}
	}
	
	public String ask(PokeBot bot, Tweet question) {
		
	

		// Extraction des mots
    	Pattern p = Pattern.compile("#stat #([^ ]+) ");
    	Matcher m = p.matcher(question.getText());
    	
    	// Si le message contient #stats #<stat demandée>
    	if(m.find())
    	{
    		// Extraction de la stat demandée
    		String TypeStat = m.group(1);
    		
    		if (TypeStat.equals("level")) {
    			return ""+bot.getLevel();
    		}
    		else if (TypeStat.equals("XP")) {
    			return ""+bot.getExp();
    		}
    		else if (TypeStat.equals("PV")) {
    			return bot.getPv() + "/" + bot.getPvmax();
    		}
    		// Stat inconnue
    		else {
    			return "Magi Magi ?";
    		}
    	}
    	Pattern p1 = Pattern.compile("#statAttack #([^ ]+) #([^ ]+)");
    	Matcher m1 = p1.matcher(question.getText());
    	if(m1.find())
    	{
    		
    		String TypeStatAttack = m1.group(1);

	    	String Skill = m1.group(2);
	    	
	    	
    	
	    	for (int i = 0; i < Attack.length ; ++i)
			{
				
				if (Attack[i].getNom().equals(Skill))
				{
					PPmax = Attack[i].getPp();
					Puissance = Attack[i].getPuissance();
					Precision = Attack[i].getPrecision();
				}
			}
	    	if (TypeStatAttack.equals("PP"))
	    	{
		    	if(Skill.equals(bot.getAtk1())) return bot.getPp1() + "/" + PPmax;
		    	
		    	if(Skill.equals(bot.getAtk2())) return bot.getPp2() + "/" + PPmax;
		    	
		    	if(Skill.equals(bot.getAtk3())) return bot.getPp3() + "/" + PPmax;
		        
		    	if(Skill.equals(bot.getAtk4())) return bot.getPp4() + "/" + PPmax;
	    	}
    		else if (TypeStatAttack.equals("Puissance"))
    			return Puissance;
    		else if (TypeStatAttack.equals("Precision"))
    			return Precision;
    		else return "Magi Magi ?";
	    		
	}
	return null;


	}
}