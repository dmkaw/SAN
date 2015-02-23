package kemod.ownership;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="individuals")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Individual {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	
	@ManyToMany
	@JoinColumn(name="registryunion_id")
	List<RegistryUnion> ru;
}
