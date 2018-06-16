package org.ensah.controllers;



import java.security.Principal;

import org.ensah.domains.Compte;
import org.ensah.service.EtudiantService;
import org.ensah.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EtudiantController {
	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(value="/etudiant/AbsenceEtudiant")
	public String AbsenceEtudiant(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("Etudiant", etudiantService.getEtudiant(compte.getIduser()));
		return "etudiant/ListAbsenceEtudiant";
	}
	@RequestMapping(value="/etudiant/noteEtudiant")
	public String noteEtudiant(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("comptecontroller", new CompteController());
		model.addAttribute("notes", etudiantService.getEtudiant(compte.getIduser()).getLesNotes());
		return "etudiant/listNoteEtudiant";
	}
	public Object getUser(Compte compte){
		Object user=null;
			user=etudiantService.getEtudiant(compte.getIduser());
			
		return user;
	}
}
