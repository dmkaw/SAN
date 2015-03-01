package evi.ownership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LAND_REGISTERS")
public class LandRegister {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique = true)
	private String landRegister;
	
	@OneToOne(mappedBy = "lr")
	private RegistryUnion ru;
	
	public LandRegister(){
		
	}
	
	public LandRegister(String landRegister){
		this.landRegister = landRegister;
	}

	public int getId() {
		return id;
	}
	
	public String getLandRegister() {
		return landRegister;
	}

	public void setLandRegister(String landRegister) {
		this.landRegister = landRegister;
	}

	public RegistryUnion getRu() {
		return ru;
	}

	public void setRu(RegistryUnion ru) {
		this.ru = ru;
	}

}
