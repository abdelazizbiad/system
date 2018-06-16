package org.ensah.service.impl;

import org.ensah.domains.Etudiant;
import org.ensah.repos.EtudiantRepos;
import org.ensah.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService{
	@Autowired
	private EtudiantRepos etudiantRepos;
	@Override
	public Etudiant getEtudiant(Long id){
		Etudiant etd=etudiantRepos.findOne(id);
		if(etd==null)
			throw new RuntimeException("ETUDIANT NOT FOUND");
		return etd;
	}
}
