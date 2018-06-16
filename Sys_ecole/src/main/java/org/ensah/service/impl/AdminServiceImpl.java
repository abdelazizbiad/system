package org.ensah.service.impl;

import java.util.List;

import org.ensah.domains.Affectation;
import org.ensah.domains.Compte;
import org.ensah.domains.Element;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Inscription;
import org.ensah.domains.Niveau;
import org.ensah.domains.Professeur;
import org.ensah.domains.Role;
import org.ensah.repos.AffectationRepos;
import org.ensah.repos.CompteRepos;
import org.ensah.repos.ElementRepos;
import org.ensah.repos.EtudiantRepos;
import org.ensah.repos.InscriptionRepos;
import org.ensah.repos.NiveauRepos;
import org.ensah.repos.ProfesseurRepos;
import org.ensah.repos.RoleRepos;
import org.ensah.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private NiveauRepos niveauRepos;
	@Autowired 
	private InscriptionRepos inscriptionRepos;
	@Autowired
	private EtudiantRepos etudiantRepos;
	@Autowired
	private CompteRepos compteRepos;
	@Autowired
	private RoleRepos roleRepos;
	@Autowired
	private ProfesseurRepos professeurRepos;
	@Autowired
	private ElementRepos elementRepos;
	@Autowired
	private AffectationRepos affectationRepos;
	@Override
	public List<Niveau> getNiveaus(){
		return niveauRepos.findAll();
	}
	@Override
	public List<Element> getElements(){
		return elementRepos.findAll();
	}
	@Override
	public List<Professeur> getProfesseurs(){
		return professeurRepos.findAll();
	}
	@Override
	public void addEtudiant(Inscription inscription,Etudiant etudiant,Long idNiveau){
		etudiantRepos.save(etudiant);
		Etudiant etd=etudiantRepos.findByCne(etudiant.getCne());
		Niveau niveau=niveauRepos.findOne(idNiveau);
		Role role=roleRepos.findByRoleName("ETUDIANT");
		if(niveau==null)
			throw new RuntimeException("Niveau Introuvable");
		if(etd==null)
			throw new RuntimeException("Etudiant Introuvable");
		if(role==null)
			throw new RuntimeException("Role Introuvable");
		inscription.setEtudiant(etd);
		inscription.setNiveau(niveau);
		
		Compte compte=new Compte();
		compte.setUsername(etd.getCne());
		compte.setPassword(etd.getCne());
		compte.setIduser(etd.getId());
		compte.setRole(role);
		compte.setEnabled(true);
		compteRepos.save(compte);
		inscriptionRepos.save(inscription);
	}
	@Override
	public List<Role> getRoles(){
		return roleRepos.findAll();
	}
	@Override
	public Professeur addProfesseur(Professeur professeur,Long idRole){
		Professeur prof=professeurRepos.save(professeur);
		Role role=roleRepos.findOne(idRole);
		if(prof==null)
			throw new RuntimeException("Professeur Introuvable");
		if(role==null)
			throw new RuntimeException("Role Introuvable");
		Compte compte=new Compte();
		compte.setEnabled(true);
		compte.setUsername(professeur.getCin());
		compte.setPassword(professeur.getCin());
		compte.setRole(role);
		compte.setIduser(prof.getId());
		compteRepos.save(compte);
		return prof;
	}
	@Override
	public Affectation addAffectation(Affectation affectation,Long idProf,Long idElement){
		Professeur prof=professeurRepos.findOne(idProf);
		if(prof==null)
			throw new RuntimeException("Professeur Introuvable");
		Element element=elementRepos.findOne(idElement);
		if(element==null)
			throw new RuntimeException("Element Introuvable");
		affectation.setProfesseur(prof);
		affectation.setElement(element);
		return affectationRepos.save(affectation);
	}
	@Override
	public List<Professeur> getProfesseursWordKey(String wordKey){
		return professeurRepos.findProfesseursWordkey(wordKey);
	}
	@Override
	public void deleteProfesseur(Long idProfesseur) {
		Professeur professeur=professeurRepos.findOne(idProfesseur);
		if(professeur == null)
			throw new RuntimeException("Professeur introuvable, suppression échoué.");
		List<Compte> comptes=compteRepos.findByIdUser(professeur.getId());
		if(comptes.size()==0)
			throw new RuntimeException("Comptes introuvable, suppression échoué.");
		for(int i=0;i<comptes.size();i++){
			if(comptes.get(i).getRole().getRoleName().equals("PROFESSEUR")){
				compteRepos.delete(comptes.get(i));
				break;
			}
		}
		professeurRepos.delete(professeur);
		
	}
}
