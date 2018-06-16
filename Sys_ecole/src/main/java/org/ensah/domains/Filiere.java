package org.ensah.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Filiere {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	@OneToMany(mappedBy="filier",cascade = CascadeType.ALL)
	private List<Module> lesmodules;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Module> getLesmodules() {
		return lesmodules;
	}
	public void setLesmodules(List<Module> lesmodules) {
		this.lesmodules = lesmodules;
	}
	
}
