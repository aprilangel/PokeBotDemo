package fr.univaix.iut.pokebattle.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Pokebot.FIND_ALL, query = "SELECT p FROM Pokebot p"),
})
public class Pokebot implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3228507168752805839L;

	public static final String FIND_ALL = "findAllPokemon";
    
    @Id
    private String nom;

    private String espece;
    private String owner;
    private String judge;
    private int pv;
    private int pvmax;
    private int isFighting;
	private int exp;
    private int level;
    
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIsFighting() {
		return isFighting;
	}

	public void setIsFighting(int isFighting) {
		this.isFighting = isFighting;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Pokebot() {
		super();
	}
	
	public Pokebot(String nom,String espece,String owner,String judge,int pv,int pvmax,int isFighting,int exp,int level)
	{
		super();
		this.nom = nom;
		this.espece = espece;
		this.owner = owner;
		this.judge = judge;
		this.pv = pv;
		this.pvmax = pvmax;
		this.isFighting = isFighting;
		this.exp = exp;
		this.level = level;
	}
	
	
	public Pokebot(String name) {
        this.nom = name;
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	public int getPvmax() {
		return pvmax;
	}

	public void setPvmax(int pvmax) {
		this.pvmax = pvmax;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((espece == null) ? 0 : espece.hashCode());
		result = prime * result + exp;
		result = prime * result + isFighting;
		result = prime * result + ((judge == null) ? 0 : judge.hashCode());
		result = prime * result + level;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + pv;
		result = prime * result + pvmax;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokebot other = (Pokebot) obj;
		if (espece == null) {
			if (other.espece != null)
				return false;
		} else if (!espece.equals(other.espece))
			return false;
		if (exp != other.exp)
			return false;
		if (isFighting != other.isFighting)
			return false;
		if (judge == null) {
			if (other.judge != null)
				return false;
		} else if (!judge.equals(other.judge))
			return false;
		if (level != other.level)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (pv != other.pv)
			return false;
		if (pvmax != other.pvmax)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pokebot [nom=" + nom + ", espece=" + espece + ", owner="
				+ owner + ", judge=" + judge + ", pv=" + pv + ", pvmax="
				+ pvmax + ", isFighting=" + isFighting + ", exp=" + exp
				+ ", level=" + level + "]";
	}




    
}