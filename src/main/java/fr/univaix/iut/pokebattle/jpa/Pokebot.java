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
    private String nurse;
    private String fighting;
    private int pv;
    private int pvmax;
	private int exp;
    private int level;
    private String atk1;
    private String atk2;
    private String atk3;
    private String atk4;
    private int pp1;
    private int pp2;
    private int pp3;
    private int pp4;
    
    public String getAtk1() {
		return atk1;
	}

	public void setAtk1(String atk1) {
		this.atk1 = atk1;
	}

	public String getAtk2() {
		return atk2;
	}

	public void setAtk2(String atk2) {
		this.atk2 = atk2;
	}

	public String getAtk3() {
		return atk3;
	}

	public void setAtk3(String atk3) {
		this.atk3 = atk3;
	}

	public String getAtk4() {
		return atk4;
	}

	public void setAtk4(String atk4) {
		this.atk4 = atk4;
	}

	public int getPp1() {
		return pp1;
	}

	public void setPp1(int pp1) {
		this.pp1 = pp1;
	}

	public int getPp2() {
		return pp2;
	}

	public void setPp2(int pp2) {
		this.pp2 = pp2;
	}

	public int getPp3() {
		return pp3;
	}

	public void setPp3(int pp3) {
		this.pp3 = pp3;
	}

	public int getPp4() {
		return pp4;
	}

	public void setPp4(int pp4) {
		this.pp4 = pp4;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFighting() {
		return fighting;
	}

	public void setFighting(String fighting) {
		this.fighting = fighting;
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
	
	public Pokebot(String nom,String espece,String owner,String judge,int pv,int pvmax,String fighting,int exp,int level)
	{
		super();
		this.nom = nom;
		this.espece = espece;
		this.owner = owner;
		this.judge = judge;
		this.pv = pv;
		this.pvmax = pvmax;
		this.fighting = fighting;
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
	
	public String getNurse() {
		return nurse;
	}

	public void setNurse(String nurse) {
		this.nurse = nurse;
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
		result = prime * result + ((atk1 == null) ? 0 : atk1.hashCode());
		result = prime * result + ((atk2 == null) ? 0 : atk2.hashCode());
		result = prime * result + ((atk3 == null) ? 0 : atk3.hashCode());
		result = prime * result + ((atk4 == null) ? 0 : atk4.hashCode());
		result = prime * result + ((espece == null) ? 0 : espece.hashCode());
		result = prime * result + exp;
		result = prime * result
				+ ((fighting == null) ? 0 : fighting.hashCode());
		result = prime * result + ((judge == null) ? 0 : judge.hashCode());
		result = prime * result + ((nurse == null) ? 0 : nurse.hashCode());
		result = prime * result + level;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + pp1;
		result = prime * result + pp2;
		result = prime * result + pp3;
		result = prime * result + pp4;
		result = prime * result + pv;
		result = prime * result + pvmax;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pokebot other = (Pokebot) obj;
		if (atk1 == null) {
			if (other.atk1 != null) {
				return false;
			}
		} 
		else if (!atk1.equals(other.atk1)) {
			return false;
		}
		if (atk2 == null) {
			if (other.atk2 != null) {
				return false;
			}
		} 
		else if (!atk2.equals(other.atk2)) {
			return false;
		}
		if (atk3 == null) {
			if (other.atk3 != null) {
				return false;
			}
		} 
		else if (!atk3.equals(other.atk3)) {
			return false;
		}
		if (atk4 == null) {
			if (other.atk4 != null) {
				return false;
			}
		} 
		else if (!atk4.equals(other.atk4)) {
			return false;
		}
		if (espece == null) {
			if (other.espece != null) {
				return false;
			}
		} 
		else if (!espece.equals(other.espece)) {
			return false;
		}
		
		if (exp != other.exp) {
			return false;
		}
		
		if (fighting == null) {
			if (other.fighting != null) {
				return false;
			}
		} 
		else if (!fighting.equals(other.fighting)) {
			return false;
		}
		
		if (judge == null) {
			if (other.judge != null) {
				return false;
			}
		} 
		else if (!judge.equals(other.judge)) {
			return false;
		}
		
		if (nurse == null) {
			if (other.nurse != null) {
				return false;
			}
		} 
		else if (!nurse.equals(other.nurse)) {
			return false;
		}
		
		if (level != other.level) {
			return false;
		}
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		
		if (pp1 != other.pp1) {
			return false;
		}
		if (pp2 != other.pp2) {
			return false;
		}
		if (pp3 != other.pp3) {
			return false;
		}
		if (pp4 != other.pp4) {
			return false;
		}
		if (pv != other.pv) {
			return false;
		}
		
		if (pvmax != other.pvmax) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pokebot [nom=" + nom + ", espece=" + espece + ", owner="
				+ owner + ", judge=" + judge + ", nurse=" + nurse + ", fighting=" + fighting
				+ ", pv=" + pv + ", pvmax=" + pvmax + ", exp=" + exp
				+ ", level=" + level + ", atk1=" + atk1 + ", atk2=" + atk2
				+ ", atk3=" + atk3 + ", atk4=" + atk4 + ", pp1=" + pp1
				+ ", pp2=" + pp2 + ", pp3=" + pp3 + ", pp4=" + pp4 + "]";
	}






    
}