package org.ensah.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Affectation {
	@Id
	@GeneratedValue
	private Long id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_debut;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_fin;
	@ManyToOne
	@JoinColumn(name="id_prof")
	private Professeur professeur;
	@ManyToOne
	@JoinColumn(name="id_element")
	private Element element;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public Professeur getProfesseur() {
		return professeur;
	}
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	
}
