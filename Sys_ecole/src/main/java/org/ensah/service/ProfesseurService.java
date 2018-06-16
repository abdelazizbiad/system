package org.ensah.service;

import org.ensah.domains.Note;
import org.ensah.domains.Professeur;

public interface ProfesseurService {
	public Professeur getProfesseur(Long id);

	Professeur getProfBycin(String cin);

	public void addNote(Note note, Long idEtudiant, Long idElement,Long idProf);

	public void deleNote(Long id);
}
