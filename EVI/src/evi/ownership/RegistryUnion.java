package evi.ownership;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import evi.estates.LandLot;


@Entity
@NamedQuery(name="findRuIdByLandLotLlId", query="select r.id from RegistryUnion r join r.landLots l"
		                                      + " where l.llId = :llId")
@Table(name = "REGISTRY_UNIONS")
public class RegistryUnion {

	@Id
	@TableGenerator(name="TABLE_GENERATOR", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
    valueColumnName="SEQ_COUNT", pkColumnValue="RU_SEQ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GENERATOR")
	int id;
	
	
	@OneToMany(mappedBy="ru")    // This makes each land lot can exist in ONLY one registry union
	Set<LandLot> landLots = new HashSet<LandLot>();
	
	
	@ManyToMany(mappedBy="ru")   // This makes individual can exist in MORE than one registry union
	Set<Individual> owners = new HashSet<Individual>();
	
	@OneToOne
	@JoinColumn(name = "landregister_id")
	LandRegister lr;

	public int getId() {
		return id;
	}
	
	public Set<LandLot> getLandLots() {
		return landLots;
	}

	public void setLandLots(Set<LandLot> landLots) {
		this.landLots = landLots;
	}

	public Set<Individual> getOwners() {
		return owners;
	}

	public void setOwners(Set<Individual> owners) {
		this.owners = owners;
	}

	public LandRegister getLr() {
		return lr;
	}

	public void setLr(LandRegister lr) {
		this.lr = lr;
	}
	
}
