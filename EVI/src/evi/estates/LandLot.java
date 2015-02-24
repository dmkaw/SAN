package evi.estates;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import evi.ownership.RegistryUnion;

/**
 * Entity implementation class for Entity: Planet
 *
 */
@Entity
@NamedQuery(name="findLandLotIdByLLId", query="select l.id from LandLot l where l.llid = :llid")
public class LandLot implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique=true, nullable=false)
	private String llid;
	
	@Column(nullable = false)
	private CadastralMunicipality cm;
	
	//Area in square meters
	long area;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="registryunion_id")
	private RegistryUnion ru;
	
	public LandLot() {
	}
	
	public LandLot(String name, CadastralMunicipality cm) {
		this.llid = name;
		this.cm = cm;
	}
	
	public LandLot(String name, CadastralMunicipality cm, long radius) {
		this.llid = name;
		this.area = radius;
	}
	
	public int getId() {
		return id;
	}

	public String getLlid() {
		return llid;
	}

	public void setLlid(String llid) {
		this.llid = llid;
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

	private static final long serialVersionUID = 1L;

}
