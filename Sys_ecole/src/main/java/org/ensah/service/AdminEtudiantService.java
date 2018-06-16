package org.ensah.service;
import java.util.List;

import org.ensah.domains.Etudiant;
public interface AdminEtudiantService {
	public List<Etudiant> getEtudiants();
	public Etudiant getEtudiantbyCne(String cne);
	public Etudiant getEtudiant(Long id);
	public List<Etudiant> getEtudiantMotCle(String motCle);
	void deleteetudiant(Long idetudiant);
}
