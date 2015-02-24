package evi.ownership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="institutions")
@PrimaryKeyJoinColumn(name="individuals_id")
public class Institution extends Individual{

	@Column(nullable = false)
	String name;
	
	@Column(nullable = false)
	String institutionNumber;
	
	String address;
	
	public Institution(){}
	
	public Institution(String name, String iNumber){
		this.name = name;
		if (iNumber.length() != 9) throw new IllegalArgumentException("Institution Number have to be 9 digits");
		this.institutionNumber = iNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		if (institutionNumber.length() != 9) throw new IllegalArgumentException("Institution Number have to be 9 digits");
		this.institutionNumber = institutionNumber;
	}
	
}
