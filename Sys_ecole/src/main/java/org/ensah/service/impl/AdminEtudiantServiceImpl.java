package org.ensah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.ensah.domains.Compte;
import org.ensah.domains.Etudiant;
import org.ensah.repos.EtudiantRepos;
import org.ensah.repos.CompteRepos;
import org.ensah.service.AdminEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminEtudiantServiceImpl implements AdminEtudiantService{
	
	@Autowired
	EtudiantRepos etudiantRepos;
	@Autowired
	CompteRepos compteRepos;
	@Override
	public List<Etudiant> getEtudiants() {
		List<Etudiant> list=etudiantRepos.findAll();
		if(list==null)
			list=new ArrayList<Etudiant>();
		return list;
	}
	@Override
	public Etudiant getEtudiantbyCne(String cne) {
		Etudiant etd=etudiantRepos.findByCne(cne);
		if (etd == null){
			return null;
		}
		return etd;
	}
	@Override
	public Etudiant getEtudiant(Long id) {
		Etudiant etd=etudiantRepos.findOne(id);
		if (etd == null){
			throw new RuntimeException("Etudiant introuvable");
		}
		return etd;
	}
	@Override
	public List<Etudiant> getEtudiantMotCle(String motCle){
		return etudiantRepos.findEtudiantsWordkey(motCle);
	}
	@Override
	public void deleteetudiant(Long idetudiant) {
		Etudiant etudiant=etudiantRepos.findOne(idetudiant);
		if(etudiant == null)
			throw new RuntimeException("Etudiant introuvable, suppression échoué.");
		List<Compte> comptes=compteRepos.findByIdUser(etudiant.getId());
		if(comptes.size()==0)
			throw new RuntimeException("Compte introuvable, suppression échoué.");
		for(int i=0;i<comptes.size();i++){
			if(comptes.get(i).getRole().getRoleName().equals("ETUDIANT")){
				compteRepos.delete(comptes.get(i));
				break;
			}
		}
		etudiantRepos.delete(etudiant);
		
	}

}
