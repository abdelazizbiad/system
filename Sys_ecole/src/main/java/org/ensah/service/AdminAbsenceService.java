package org.ensah.service;

import java.util.List;

import org.ensah.domains.Absence;
import org.ensah.domains.Element;

public interface AdminAbsenceService {
	
	public Absence addAbsence(Absence absence,Long idProf,Long idEtudiant, Long idElement);
	public List<Absence> getAllAbsence();
	public Absence updateAbsence(Absence absence);
	public void deleteAbsence(Long idAbsence);
	public List<Element> getElementByidProf(Long idProf);
	public List<Absence> getAbsenceMotCle(String motCle);
	
}
