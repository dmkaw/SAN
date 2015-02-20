package kemod.planets;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Planet
 *
 */
@Entity
public class Planet implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String name;
	
	public Planet() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	private static final long serialVersionUID = 1L;

   
}
