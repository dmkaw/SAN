package kemod.ownership;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import kemod.planet.Planet;


@Entity
public class RegistryUnion {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;
	
	
	@OneToMany(mappedBy="ru")    // This makes each planet can exist in ONLY one registry union
	List<Planet> planet;
	
	
	@ManyToMany(mappedBy="ru")   // This makes owner can exist in MORE than one registry union
	List<Individual> owner;

	public List<Planet> getPlanet() {
		return planet;
	}

	public void setPlanet(List<Planet> planet) {
		this.planet = planet;
	}

	public List<Individual> getOwner() {
		return owner;
	}

	public void setOwner(List<Individual> owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}
	
	
}
