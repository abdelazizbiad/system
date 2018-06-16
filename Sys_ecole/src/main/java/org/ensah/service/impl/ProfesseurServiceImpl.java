package org.ensah.service.impl;

import org.ensah.domains.Element;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Note;
import org.ensah.domains.Professeur;
import org.ensah.repos.ElementRepos;
import org.ensah.repos.EtudiantRepos;
import org.ensah.repos.NoteRepos;
import org.ensah.repos.ProfesseurRepos;
import org.ensah.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfesseurServiceImpl implements ProfesseurService{
	@Autowired
	ProfesseurRepos professeurRepos;
	@Autowired
	EtudiantRepos etudiantRepos;
	@Autowired
	ElementRepos elementRepos;
	@Autowired
	NoteRepos noteRepos;
	@Override
	public Professeur getProfesseur(Long id) {
		
		return professeurRepos.findOne(id);
	}
	@Override
	public Professeur getProfBycin(String cin){
		return professeurRepos.findByCin(cin);
	}
	@Override
	public void addNote(Note note, Long idEtudiant, Long idElement,Long idProf) {
		Professeur prof=professeurRepos.findOne(idProf);
		Etudiant etu=etudiantRepos.findOne(idEtudiant);
		Element element=elementRepos.findOne(idElement);
		if (prof == null){
			throw new RuntimeException("Professeur introuvable, impossible d'ajouter Note.");
		}
		if (etu == null){
			throw new RuntimeException("Etudiant introuvable, impossible d'ajouter Note.");
		}
		if (element == null){
			throw new RuntimeException("Element introuvable, impossible d'ajouter Note.");
		}
		if(note.getNote_SN()<12){
			if(note.getNote_SR()<note.getNote_SN()){
				note.setMoyenne(note.getNote_SN());
			}else{
				note.setMoyenne(note.getNote_SR());
			}
		}else{
			note.setMoyenne(note.getNote_SN());
		}
		note.setProfesseur(prof);
		note.setEtudiant(etu);
		note.setElement(element);;
	 noteRepos.save(note);
		
	}
	@Override
	public void deleNote(Long id) {
		Note note=noteRepos.findOne(id);
		if(note == null)
			throw new RuntimeException("Note introuvable, suppression échoué.");
		noteRepos.delete(note);
		
	}
}
