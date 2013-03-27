package fr.univaix.iut.pokebattle.jpa;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Pokebot.FIND_ALL, query = "SELECT p FROM Pokebot p"),
})
public class Pokebot {
    public static final String FIND_ALL = "findAllPokemon";
    @Id
    private String name;

    private String espece;
    private String owner;
    private String judge;
    private int pv = 100;
    private int pvmax = 100;
    private String isFighting = "false";
   
    protected Pokebot() {

    }

    public Pokebot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

	public String isFighting() {
		return isFighting;
	}

	public void setFighting(String isFighting) {
		this.isFighting = isFighting;
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
		result = prime * result + ((isFighting == null) ? 0 : isFighting.hashCode());
		result = prime * result + ((judge == null) ? 0 : judge.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (isFighting == null) {
			if (other.isFighting != null)
				return false;
		} else if (!isFighting.equals(other.isFighting))
			return false;
		if (judge == null) {
			if (other.judge != null)
				return false;
		} else if (!judge.equals(other.judge))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "Pokebot [name=" + name + ", espece=" + espece + ", owner="
				+ owner + ", judge=" + judge + ", pv=" + pv + ", pvmax="
				+ pvmax + ", isFighting=" + isFighting + "]";
	}


    
}