package org.ensah.service;


import java.util.List;

import org.ensah.domains.Affectation;
import org.ensah.domains.Element;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Inscription;
import org.ensah.domains.Niveau;
import org.ensah.domains.Professeur;
import org.ensah.domains.Role;
public interface AdminService {
	public List<Niveau> getNiveaus();

	void addEtudiant(Inscription inscription, Etudiant etudiant, Long idNiveau);

	List<Role> getRoles();

	Professeur addProfesseur(Professeur professeur, Long idRole);

	List<Element> getElements();

	List<Professeur> getProfesseurs();

	Affectation addAffectation(Affectation affectation, Long idProf, Long idElement);

	List<Professeur> getProfesseursWordKey(String wordKey);

	void deleteProfesseur(Long idProfesseur);
}
