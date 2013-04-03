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
		
	    	Pattern p = Pattern.compile("#stat #([^ ]+) ");
	    	Matcher m = p.matcher(question.getText());
	    	if(m.find())
	    	{
	    		String TypeStat = m.group(1);
	    		if (TypeStat.equals("level"))
	    			return bot.level;
	    		else if (TypeStat.equals("XP"))
	    			return bot.XP;
	    		else if (TypeStat.equals("PV"))
	    			return bot.PV + "/" + bot.PVmax;
	    		else return "Magi Magi ?";
	    	}
	    	
    		Pattern p1 = Pattern.compile("#statAttack #([^ ]+) ");
	    	Matcher m1 = p1.matcher(question.getText());
	    	if(m1.find())
	    	{
	    		
	    		String TypeStatAttack = m1.group(1);
	    		
	    		Pattern p2 = Pattern.compile(TypeStatAttack + " " + "#([^ ]+) ");
		    	Matcher m2 = p2.matcher(question.getText());
		    	String Skill = m2.group(1);
		    	return Skill;
		    	/* if (m2.find())
		    	{
			    	for (int i = 0; i < Attack.length ; ++i)
					{
						
						if (Attack[i].getNom().equals(m2.group(1)))
						{
							PPmax = Attack[i].getPp();
						}
						
						if (Attack[i].getNom().equals(m2.group(1)))
						{
							Puissance = Attack[i].getPuissance();
						}
						
						if (Attack[i].getNom().equals(m2.group(1)))
						{
							Precision = Attack[i].getPrecision();
						}
					}
		    		if (TypeStatAttack.equals("PP"))
		    			return bot.PP + "/" + PPmax;
		    		else if (TypeStatAttack.equals("Puissance"))
		    			return Puissance;
		    		else if (TypeStatAttack.equals("Precision"))
		    			return Precision;
		    		else return "Magi Magi ?";
		    	} 
		    	return null; */
	    	}
	    	return null;

	}
}