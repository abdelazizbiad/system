package org.ensah.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.ensah.domains.Absence;
import org.ensah.domains.Compte;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Note;
import org.ensah.service.AdminAbsenceService;
import org.ensah.service.AdminEtudiantService;
import org.ensah.service.GuestService;
import org.ensah.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfesseurController {
	@Autowired
	private AdminEtudiantService adminEtudiantService;
	@Autowired
	private AdminAbsenceService adminAbsenceService;
	 @Autowired
	 private GuestService guestService;
	 @Autowired
	 private ProfesseurService professeurService;
	@RequestMapping(value="/professeur/addAbsence",method=RequestMethod.GET)
	public String addAbsence(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("title", "Saisie Absence");
	     model.addAttribute("absence", new Absence());
	     model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
		return "professeur/AbsenceForm";
	}
	@RequestMapping(value="/professeur/addAbsence",method=RequestMethod.POST)
	public String addAbsence(Model model,@Valid Absence absence,BindingResult bindingResult,@RequestParam Long idElement,
			@RequestParam String CNE,Principal principal){
		model.addAttribute("title", "Saisie Absence");
		 Compte compte=guestService.signin(principal.getName());
		 model.addAttribute("user", getUser(compte));
		 Etudiant etudiant=adminEtudiantService.getEtudiantbyCne(CNE);
		 if(bindingResult.hasErrors()){
			 model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
			 return "professeur/AbsenceForm";
		 }
		if (idElement == null){
			model.addAttribute("erreurelement", true);
			model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
         	return "professeur/AbsenceForm";
         	}
		 if(CNE==""){
			 model.addAttribute("erreurCne", true);
			 model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
			 return "professeur/AbsenceForm";
		 }
		 if(etudiant==null){
			 model.addAttribute("Erreuretd",true);
			 model.addAttribute("Select", "--Select Item--");
			 model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
			 return "professeur/AbsenceForm";
		 }
			 
		 adminAbsenceService.addAbsence(absence,compte.getIduser(), etudiant.getId(), idElement);
		return "redirect:/professeur/ProfesseurAbsenceList";
	}
	@RequestMapping(value="/professeur/ProfesseurAbsenceList")
	public String showAbsences(Model model,@RequestParam(name="motCle",defaultValue="")String motCle,
			Principal principal){
		 Compte compte=guestService.signin(principal.getName());
		model.addAttribute("Professeur", professeurService.getProfesseur(compte.getIduser()));
		model.addAttribute("user", getUser(compte));
		return "professeur/ProfesseurAbsenceList";
	}
	@RequestMapping(value="/professeur/delete")
	public String deleteAbsence(Long id){
		adminAbsenceService.deleteAbsence(id);
		return "redirect:/professeur/ProfesseurAbsenceList";
	}
	@RequestMapping(value="/professeur/addNote",method=RequestMethod.GET)
	public String addNote(Model model,Principal principal){
		model.addAttribute("title", "Saisie Note");
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
		model.addAttribute("note",new Note());
		return "professeur/noteForm";
	}
	@RequestMapping(value="/professeur/addNote",method=RequestMethod.POST)
	public String addNote(Model model,Principal principal,
			@Valid Note note,BindingResult bindingResult,
			@RequestParam String cne,@RequestParam Long idElement){
		model.addAttribute("title", "Saisie Absence");
		 Compte compte=guestService.signin(principal.getName());
		 model.addAttribute("user", getUser(compte));
		 Etudiant etudiant=adminEtudiantService.getEtudiantbyCne(cne);
		 if(bindingResult.hasErrors()){
			 model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
			 return "professeur/noteForm";
		 }
		if (idElement == null){;
			model.addAttribute("erreurelement", true);
			model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
        	return "professeur/noteForm";
        	}
		 if(etudiant==null){
			 model.addAttribute("Erreuretd",true);
			 model.addAttribute("affectations", professeurService.getProfesseur(compte.getIduser()).getLesAffectations());
			 return "professeur/noteForm";
		 }
		professeurService.addNote(note,etudiant.getId(),idElement,compte.getIduser());
		return "redirect:/professeur/ProfesseurNoteList";
	}
	@RequestMapping(value="/professeur/ProfesseurNoteList")
	public String showNotes(Model model,@RequestParam(name="motCle",defaultValue="")String motCle,
			Principal principal){
		 Compte compte=guestService.signin(principal.getName());
		model.addAttribute("Professeur", professeurService.getProfesseur(compte.getIduser()));
		model.addAttribute("user", getUser(compte));
		return "professeur/professeuNoteList";
	}
	@RequestMapping(value="/professeur/deleteNote")
	public String delteNote(Long id){
		professeurService.deleNote(id);
		return "redirect:/professeur/ProfesseurNoteList";
	}
	public Object getUser(Compte compte){
		Object user=null;
		 	user=professeurService.getProfesseur(compte.getIduser());
		return user;
	}
}
