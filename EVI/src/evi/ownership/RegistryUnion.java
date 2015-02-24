package evi.ownership;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import evi.estates.LandLot;


@Entity
public class RegistryUnion {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;
	
	
	@OneToMany(mappedBy="ru")    // This makes each planet can exist in ONLY one registry union
	List<LandLot> landLots = new ArrayList<LandLot>();
	
	
	@ManyToMany(mappedBy="ru")   // This makes owner can exist in MORE than one registry union
	List<Individual> owners = new ArrayList<Individual>();

	public List<LandLot> getLandLots() {
		return landLots;
	}

	public void setLandLots(List<LandLot> landLots) {
		this.landLots = landLots;
	}

	public List<Individual> getOwners() {
		return owners;
	}

	public void setOwners(List<Individual> owners) {
		this.owners = owners;
	}

	public int getId() {
		return id;
	}
	
	
}
