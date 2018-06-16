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
public class Element {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	@ManyToOne
	@JoinColumn(name="id_module")
	private Module module;
	@OneToMany(mappedBy="element",cascade=CascadeType.ALL)
	private List<Note> lesNotes;
	
	public List<Note> getLesNotes() {
		return lesNotes;
	}
	public void setLesNotes(List<Note> lesNotes) {
		this.lesNotes = lesNotes;
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
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
}
