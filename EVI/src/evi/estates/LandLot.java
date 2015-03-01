package evi.estates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import evi.ownership.RegistryUnion;

/**
 * Entity implementation class for Entity: Planet
 *
 */
@Entity
@Table(name = "LAND_LOTS")
@NamedQuery(name="findLandLotIdByLLId", query="select l.id from LandLot l where l.llId = :llId")
public class LandLot implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique=true, nullable=false, length=12)
	private String llId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CadastralMunicipality cm;
	
	//Area in square meters
	long area;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="registryunion_id")
	private RegistryUnion ru;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="landlot_id")
	List<Building> buildings = new ArrayList<Building>();
	
	public LandLot() {
	}
	
	public LandLot(String name, CadastralMunicipality cm) {
		this.llId = name;
		this.cm = cm;
	}
	
	public LandLot(String name, CadastralMunicipality cm, long radius) {
		this.llId = name;
		this.area = radius;
	}
	
	public int getId() {
		return id;
	}

	public String getLlId() {
		return llId;
	}

	public void setLlid(String llId) {
		this.llId = llId;
	}

	public long getArea() {
		return area;
	}

	public CadastralMunicipality getCm() {
		return cm;
	}

	public void setCm(CadastralMunicipality cm) {
		this.cm = cm;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public RegistryUnion getRu() {
		return ru;
	}

	public void setRu(RegistryUnion ru) {
		this.ru = ru;
	}
	
	public List<Building> getBuildings() {
		return buildings;
	}
	

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cm == null) ? 0 : cm.hashCode());
		result = prime * result + ((llId == null) ? 0 : llId.hashCode());
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
		LandLot other = (LandLot) obj;
		if (cm != other.cm)
			return false;
		if (llId == null) {
			if (other.llId != null)
				return false;
		} else if (!llId.equals(other.llId))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

}
