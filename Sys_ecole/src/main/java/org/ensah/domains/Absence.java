package org.ensah.domains;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Absence {
	@Id
	@GeneratedValue
	private Long id;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@NotNull
	private Date heure_debut_seance;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@NotNull
	private Date heure_fin_seance;
	
	private boolean justification;
	@ManyToOne
	@JoinColumn(name = "id_etudiant")
	private Etudiant etudiant;
	@ManyToOne
	@JoinColumn(name="id_element")
	private Element element;
	@ManyToOne
	@JoinColumn(name="id_prof")
	private Professeur prof;
	@Transient
	private int nbHeure;
	
	public int getNbHeure() {
		Date d1=getHeure_debut_seance();
		Date d2=getHeure_fin_seance();
     long ms = d2.getTime() - d1.getTime() ;
     this.nbHeure=(int)((ms /1000 )/3600);
		return nbHeure;
	}
	public void setNbHeure(int nbHeure) {
		this.nbHeure = nbHeure;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getHeure_debut_seance() {
		return heure_debut_seance;
	}
	public void setHeure_debut_seance(Date heure_debut_seance) {
		this.heure_debut_seance = heure_debut_seance;
	}
	public Date getHeure_fin_seance() {
		return heure_fin_seance;
	}
	public void setHeure_fin_seance(Date heure_fin_seance) {
		this.heure_fin_seance = heure_fin_seance;
	}
	public boolean isJustification() {
		return justification;
	}
	public void setJustification(boolean justification) {
		this.justification = justification;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public Professeur getProf() {
		return prof;
	}
	public void setProf(Professeur prof) {
		this.prof = prof;
	}
	
	
}
