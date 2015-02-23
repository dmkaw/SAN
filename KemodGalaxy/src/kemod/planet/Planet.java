package kemod.planet;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kemod.ownership.RegistryUnion;

/**
 * Entity implementation class for Entity: Planet
 *
 */
@Entity
public class Planet implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="registryunion_id")
	private RegistryUnion ru;
	
	public Planet() {
	}
	
	public Planet(String name) {
		this.name = name;
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

	
	public RegistryUnion getRu() {
		return ru;
	}

	public void setRu(RegistryUnion ru) {
		this.ru = ru;
	}

	private static final long serialVersionUID = 1L;

}
