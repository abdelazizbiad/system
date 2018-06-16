package org.ensah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.ensah.domains.Absence;
import org.ensah.domains.Element;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Professeur;
import org.ensah.repos.AbsenceRepos;
import org.ensah.repos.ElementRepos;
import org.ensah.repos.EtudiantRepos;
import org.ensah.repos.ProfesseurRepos;
import org.ensah.service.AdminAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminAbsenceServiceImpl implements AdminAbsenceService{
	
	@Autowired
	AbsenceRepos absenceRepos;
	@Autowired
	EtudiantRepos etudiantRepos;
	@Autowired
	ElementRepos elementRepos;
	@Autowired
	ProfesseurRepos professeurRepos;
	@Override
	public Absence addAbsence(Absence absence,Long idProf, Long idEtudiant, Long idElement) {
		Professeur prof=professeurRepos.findOne(idProf);
		Etudiant etu=etudiantRepos.findOne(idEtudiant);
		Element element=elementRepos.findOne(idElement);
		if (prof == null){
			throw new RuntimeException("Professeur introuvable, impossible d'ajouter l'Absence.");
		}
		if (etu == null){
			throw new RuntimeException("Etudiant introuvable, impossible d'ajouter l'Absence.");
		}
		if (element == null){
			throw new RuntimeException("Element introuvable, impossible d'ajouter l'Absence.");
		}
		absence.setProf(prof);
		absence.setEtudiant(etu);
		absence.setElement(element);
//		etu.addAbsence(absence);
//		etudiantRepos.save(etu);
		return absenceRepos.save(absence);
	}

	@Override
	public List<Absence> getAllAbsence() {
		List<Absence> list= absenceRepos.findAll();
		if (list == null)
			list = new ArrayList<Absence>();
		return list;
	}

	@Override
	public Absence updateAbsence(Absence absence) {
		
		return absenceRepos.save(absence);
	}

	@Override
	public void deleteAbsence(Long idAbsence) {
		Absence absence=absenceRepos.findOne(idAbsence);
		if(absence == null)
			throw new RuntimeException("Absence introuvable, suppression échoué.");
		absenceRepos.delete(absence);
		
	}

	@Override
	public List<Element> getElementByidProf(Long idProf) {
		List<Element> list=elementRepos.findElementByidProf(idProf);
		if(list==null)
			list=new ArrayList<>();
		return list;
	}
	@Override
	public List<Absence> getAbsenceMotCle(String motCle){
		List<Absence> list= absenceRepos.findAbsencesWordkey(motCle);
		if(list==null)
			return new ArrayList<Absence>();
		return list;
	}

}
