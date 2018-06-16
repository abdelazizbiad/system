package org.ensah.service;

import org.ensah.domains.Compte;

public interface CompteService {
	public void updateCompte(Compte compte);
	public void changePicture(Compte compte, String originalFilename);
}
