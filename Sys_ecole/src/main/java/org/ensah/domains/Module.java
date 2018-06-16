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
public class Module {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	@OneToMany(mappedBy="module",cascade = CascadeType.ALL)
	private List<Element> lesElements;
	@ManyToOne
	@JoinColumn(name="id_Filiere")
	private Filiere filier;
	@ManyToOne 
	@JoinColumn(name="id_niveau")
	private Niveau niveau;
	
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
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
	public List<Element> getLesElements() {
		return lesElements;
	}
	public void setLesElements(List<Element> lesElements) {
		this.lesElements = lesElements;
	}
	public Filiere getFilier() {
		return filier;
	}
	public void setFilier(Filiere filier) {
		this.filier = filier;
	}
	
}
