package kemod.ownership;

import javax.persistence.Entity;


@Entity
public class Institution extends Individual{

	String name;
	
	public Institution(){}
	
	public Institution(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
