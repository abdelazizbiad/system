package org.ensah.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Inscription {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Pattern(regexp = "[12][0-9]{3}",message="invalide forme")
	private String annee;
	@NotEmpty
	@Pattern(regexp = "[0][65][0-9]{8}",message="invalid numéro")
	private String Teleparent;
	@NotEmpty
	private  String categoriePere;
	@NotEmpty
	private String categorieMere;
	@NotEmpty
	private String provinceParent;
	@NotEmpty
	private String adresseParent;
	@ManyToOne
	@JoinColumn(name="id_etudiant")
	private Etudiant etudiant;
	@ManyToOne
	@JoinColumn(name="id_niveau")
	private Niveau niveau;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String année) {
		this.annee = année;
	}

	public String getTeleparent() {
		return Teleparent;
	}
	public void setTeleparent(String teleparent) {
		Teleparent = teleparent;
	}
	public String getCategoriePere() {
		return categoriePere;
	}
	public void setCategoriePere(String categoriePere) {
		this.categoriePere = categoriePere;
	}
	public String getCategorieMere() {
		return categorieMere;
	}
	public void setCategorieMere(String categorieMere) {
		this.categorieMere = categorieMere;
	}
	public String getProvinceParent() {
		return provinceParent;
	}
	public void setProvinceParent(String provinceParent) {
		this.provinceParent = provinceParent;
	}
	public String getAdresseParent() {
		return adresseParent;
	}
	public void setAdresseParent(String adresseParent) {
		this.adresseParent = adresseParent;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
	
	
	
	
	
}
