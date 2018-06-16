package org.ensah.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Niveau {
	@Id
	@GeneratedValue
	private Long id;
	private String label;
	private String alias;
	@OneToMany(mappedBy="niveau",cascade=CascadeType.ALL)
	List<Module> lesModules;
	@ManyToOne
	@JoinColumn(name="id_Filier")
	private Filiere filiere;
	@OneToMany(mappedBy="niveau")
	private List<Inscription> lesInscriptions;
	
	public Long getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public List<Module> getLesModules() {
		return lesModules;
	}
	public void setLesModules(List<Module> lesModules) {
		this.lesModules = lesModules;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public List<Inscription> getLesInscriptions() {
		return lesInscriptions;
	}
	public void setLesInscriptions(List<Inscription> lesInscriptions) {
		this.lesInscriptions = lesInscriptions;
	}
	
	
}
