package org.ensah.domains;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Etudiant {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Pattern(regexp = "[0-9]{10}",message="invalid CNE")
	private String cne;
	@NotEmpty
	@Size(min=7,max=8,message="Invalid Size")
	private String cin;
	private String nom;
	private String prenom;
	private String photo;
	private String sexe;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	private String lieuNaissance;
	@OneToMany(mappedBy="etudiant",cascade=CascadeType.ALL)
	private List<Absence> lesAbsences;
	@OneToMany(mappedBy="etudiant",cascade=CascadeType.ALL)
	private List<Inscription> lesInscriptions;
	@OneToMany(mappedBy="etudiant",cascade=CascadeType.ALL)
	private List<Note> lesNotes;
	@Email
	private String email;
	private String pays;
	@Transient
	private Inscription lastInscription;
	@NotEmpty
	@Pattern(regexp = "[12][0-9]{3}",message="invalide forme")
	private String anneeBac;
	@NotEmpty
	private String typeBac;
	@NotEmpty
	private String mentionBac;
	@NotEmpty
	private String lycee;
	@NotEmpty
	private String academieBac;
	private boolean hendicape;
	
	public List<Note> getLesNotes() {
		return lesNotes;
	}
	public void setLesNotes(List<Note> lesNotes) {
		this.lesNotes = lesNotes;
	}
	public String getAnneeBac() {
		return anneeBac;
	}
	public void setAnneeBac(String anneeBac) {
		this.anneeBac = anneeBac;
	}
	public String getTypeBac() {
		return typeBac;
	}
	public void setTypeBac(String typeBac) {
		this.typeBac = typeBac;
	}
	public String getMentionBac() {
		return mentionBac;
	}
	public void setMentionBac(String mentionBac) {
		this.mentionBac = mentionBac;
	}
	public String getLycee() {
		return lycee;
	}
	public void setLycee(String lycee) {
		this.lycee = lycee;
	}
	public String getAcademieBac() {
		return academieBac;
	}
	public void setAcademieBac(String academieBac) {
		this.academieBac = academieBac;
	}
	public boolean isHendicape() {
		return hendicape;
	}
	public void setHendicape(boolean hendicape) {
		this.hendicape = hendicape;
	}
	public Inscription getLastInscription() {
		Inscription Aide=lesInscriptions.get(0);
		for(int i=1;i<lesInscriptions.size();i++){
			if(Integer.parseInt(lesInscriptions.get(i).getAnnee())>Integer.parseInt(lesInscriptions.get(i-1).getAnnee())){
				Aide=lesInscriptions.get(i);
			}
		}
		return Aide;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public List<Inscription> getLesInscriptions() {
		return lesInscriptions;
	}
	public void setLesInscriptions(List<Inscription> lesInscriptions) {
		this.lesInscriptions = lesInscriptions;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public List<Absence> getLesAbsences() {
		return lesAbsences;
	}
	public void setLesAbsences(List<Absence> lesAbsences) {
		this.lesAbsences = lesAbsences;
	}
	public void addAbsence(Absence absence){
		this.lesAbsences.add(absence);
	}
	
}
