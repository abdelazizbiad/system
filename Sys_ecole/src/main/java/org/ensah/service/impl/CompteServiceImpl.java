package org.ensah.service.impl;

import org.ensah.domains.Compte;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Professeur;
import org.ensah.repos.CompteRepos;
import org.ensah.repos.EtudiantRepos;
import org.ensah.repos.ProfesseurRepos;
import org.ensah.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class CompteServiceImpl implements CompteService{
	@Autowired
	CompteRepos compteRepos;
	@Autowired
	EtudiantRepos etudiantRepos;
	@Autowired
	ProfesseurRepos professeurRepos;
	
	@Override
	public void updateCompte(Compte compte){
		compte.setRole(compteRepos.findOne(compte.getId()).getRole());
		compte.setUsername(compteRepos.findOne(compte.getId()).getUsername());
		compteRepos.save(compte);
	}

	@Override
	public void changePicture(Compte compte, String originalFilename) {
		Compte compte2=compteRepos.findOne(compte.getId());
		if(compte2==null)
			throw new RuntimeException("Compte Introuvale,Echoué Ajout Picture");
		if(compte2.getRole().getRoleName().equals("ETUDIANT")){
			Etudiant etudiant=etudiantRepos.findOne(compte.getIduser());
			if(etudiant==null)
				throw new RuntimeException("Etudiant Introuvale,Echoué Ajout Picture");
			etudiant.setPhoto(originalFilename);
			etudiantRepos.save(etudiant);
		}else{
			Professeur professeur=professeurRepos.findOne(compte.getIduser());
			if(professeur==null)
				throw new RuntimeException("Professeur Introuvale,Echoué Ajout Picture");
			professeur.setPhoto(originalFilename);
			professeurRepos.save(professeur);	
		}
		
	}
}
