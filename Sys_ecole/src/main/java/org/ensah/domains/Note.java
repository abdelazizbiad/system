package org.ensah.domains;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Note {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Pattern(regexp = "[12][0-9]{3}",message="invalide forme")
	private String annee;
	@Min(0)
	@Max(20)
	private float note_SN;
	@Min(0)
	@Max(20)
	private float note_SR;
	private float moyenne;
	@ManyToOne
	@JoinColumn(name="id_element")
	private Element element;
	@ManyToOne
	@JoinColumn(name="id_prof")
	private Professeur professeur;
	@ManyToOne
	@JoinColumn(name="id_etudiant")
	private Etudiant etudiant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public float getNote_SN() {
		return note_SN;
	}
	public void setNote_SN(float note_SN) {
		this.note_SN = note_SN;
	}
	public float getNote_SR() {
		return note_SR;
	}
	public void setNote_SR(float note_SR) {
		this.note_SR = note_SR;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public Professeur getProfesseur() {
		return professeur;
	}
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public double getNoteModule(){
//		System.out.println("---------nom Module-----"+nomModule);
		double aide=0;
		int k=0;
//		Module module=moduleRepos.findByNom(nomModule);
//		Etudiant etd=etudiantService.getEtudiant(1L);
		Module module=this.element.getModule();
//		if(module==null)
//			throw new RuntimeException("Module Introuvable");
//		if(etd==null)
//			throw new RuntimeException("Etudiant Introuvable");
		List<Element> leselements=module.getLesElements();
		for(int i=0;i<leselements.size();i++){
			List<Note> lesNotes=leselements.get(i).getLesNotes();
			
			for(int j=0;j<lesNotes.size();j++){
				String annee=lesNotes.get(0).getAnnee();
				if(lesNotes.get(j).getEtudiant().equals(this.etudiant) && lesNotes.get(j).getAnnee().equals(annee)){
					annee=lesNotes.get(j).getAnnee();
					k++;
					aide+=lesNotes.get(j).getMoyenne();
				}else{
					k=1;
					aide=lesNotes.get(0).getMoyenne();
				}
			}
		}
		return aide/k;
	}
	
}
