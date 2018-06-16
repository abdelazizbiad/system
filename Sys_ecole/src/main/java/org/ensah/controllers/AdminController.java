package org.ensah.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.ensah.domains.Affectation;
import org.ensah.domains.Compte;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Inscription;
import org.ensah.domains.Professeur;
import org.ensah.service.AdminEtudiantService;
import org.ensah.service.AdminService;
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
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	ProfesseurService professeurService;
	@Autowired 
	AdminEtudiantService adminEtudiantService;
	@Autowired
	GuestService guestService;
	
		
	@RequestMapping(value = "/admin/addEtudiant", method = RequestMethod.GET) 
	public String addEtudiant(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("lesNiveau", adminService.getNiveaus());
		model.addAttribute("typesBac", typesBac());
		model.addAttribute("mentionsBac", mentionsBac());
		model.addAttribute("academiesBac", academiesBac());
		model.addAttribute("provinces", provinces());
		model.addAttribute("etudiant", new Etudiant());
		model.addAttribute("inscription", new Inscription());
		return "admin/etudiantForm";
	}
	@RequestMapping(value = "/admin/addEtudiant", method = RequestMethod.POST)
	public String addEtudiant(Model model,@Valid Etudiant etudiant,BindingResult b2,@Valid Inscription inscription,
			BindingResult bindingResult,@RequestParam Long idNiveau,
			Principal principal){
		Compte compte=guestService.signin(principal.getName());
		if(bindingResult.hasErrors() || b2.hasErrors()){
			model.addAttribute("lesNiveau", adminService.getNiveaus());
			model.addAttribute("typesBac", typesBac());
			model.addAttribute("mentionsBac", mentionsBac());
			model.addAttribute("academiesBac", academiesBac());
			model.addAttribute("provinces", provinces());
			model.addAttribute("user", getUser(compte));
			return "admin/etudiantForm";
		}
		adminService.addEtudiant(inscription, etudiant, idNiveau);
		return "redirect:/admin/listetudiant";
	}
	
	@RequestMapping(value="/admin/addProfesseur",method=RequestMethod.GET)
	public String addProfesseur(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("title","Saisie Professeur");
		model.addAttribute("professeur", new Professeur());
		model.addAttribute("roles",adminService.getRoles());
		model.addAttribute("Select", "Selelctionner un role");
		return "admin/professeurForm";
	}
	@RequestMapping(value="/admin/addProfesseur",method=RequestMethod.POST)
	public String addProfesseur(Model model,@Valid Professeur professeur,
			BindingResult b,
			@RequestParam Long idRole,
			Principal principal){
			Compte compte=guestService.signin(principal.getName());
			if(b.hasErrors()){
				model.addAttribute("user", getUser(compte));
				model.addAttribute("roles",adminService.getRoles());
				model.addAttribute("Select", "Selelctionner un role");
				return "admin/professeurForm";
			}
			if(professeurService.getProfBycin(professeur.getCin())!=null){
				model.addAttribute("user", getUser(compte));
				model.addAttribute("roles",adminService.getRoles());
				model.addAttribute("Select", "Selelctionner un role");
				model.addAttribute("errorProf", true);
				return "admin/professeurForm";
			}
			if(idRole==null){
				model.addAttribute("user", getUser(compte));
				model.addAttribute("roles",adminService.getRoles());
				model.addAttribute("Select", "Selelctionner un role");
				model.addAttribute("errorRole", true);
				return "admin/professeurForm";
			}
			adminService.addProfesseur(professeur,idRole);
		return "redirect:/admin/affectation";
	}
	@RequestMapping(value="/admin/affectation",method=RequestMethod.GET)
	public String addAffectation(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("title", "Saisie Affectation");
		model.addAttribute("elements", adminService.getElements());
		model.addAttribute("profs", adminService.getProfesseurs());
		model.addAttribute("affectation", new Affectation());
		return "admin/affectationForm";
	}
	@RequestMapping(value="/admin/affectation",method=RequestMethod.POST)
	public String addAffectation(Model model,@Valid Affectation affectation
			,BindingResult b
			,@RequestParam Long idElement,
			@RequestParam Long idProf,
			@RequestParam(name="reI",defaultValue="false") boolean reI,
			Principal principal){
		Compte compte=guestService.signin(principal.getName());
		if(b.hasErrors()){
			model.addAttribute("elements", adminService.getElements());
			model.addAttribute("profs", adminService.getProfesseurs());
			model.addAttribute("user", getUser(compte));
			return "admin/affectationForm";
		}
		if(idElement==null){
			model.addAttribute("elements", adminService.getElements());
			model.addAttribute("profs", adminService.getProfesseurs());
			model.addAttribute("user", getUser(compte));
			model.addAttribute("errorElem",true);
			return "admin/affectationForm";
		}
		if(idProf==null){
			model.addAttribute("elements", adminService.getElements());
			model.addAttribute("profs", adminService.getProfesseurs());
			model.addAttribute("user", getUser(compte));
			model.addAttribute("errorProf",true);
			return "admin/affectationForm";
		}
		
		if(reI==true){
			adminService.addAffectation(affectation, idProf, idElement);
			return "redirect:/admin/affectation";
		}
		adminService.addAffectation(affectation, idProf, idElement);
		return "redirect:/admin/listProfesseur";
	}
	@RequestMapping(value="/admin/listetudiant",method=RequestMethod.GET)
	public String listetudiant(Model model,@RequestParam(name="motCle",defaultValue="")String motCle,
			Principal principal){
//		model.addAttribute("Absences", adminAbsenceService.getAllAbsence());
		model.addAttribute("title", "List des Absences");
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("Etudiants",adminEtudiantService.getEtudiantMotCle("%"+motCle+"%"));
		model.addAttribute("motCle", motCle);
		return "admin/listEtudiant";
	}
	@RequestMapping(value="/admin/listProfesseur",method=RequestMethod.GET)
	public String listProdesseurs(Model model,@RequestParam(name="motCle",defaultValue="")String motCle,
			Principal principal){
		model.addAttribute("title", "List des Professeurs");
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("Professeurs",adminService.getProfesseursWordKey("%"+motCle+"%"));
		model.addAttribute("motCle", motCle);
		return "admin/listProfesseur";
	}
	@RequestMapping(value="/admin/deleteEtudiant")
	public String deleteEtudiant(Long id){
		adminEtudiantService.deleteetudiant(id);
		return "redirect:/admin/listetudiant";
	}
	@RequestMapping(value="/admin/deleteProfesseur")
	public String deleteProfesseur(Long id){
		adminService.deleteProfesseur(id);
		return "redirect:/admin/listProfesseur";
	}
	
	public static List<String> typesBac(){
		List<String> list=new ArrayList<String>();
		list.add("Science Mathématique A");
		list.add("Science Mathématique B");
		list.add("Science physique");
		list.add("Science de vie et terre");
		list.add("Technique");
		return list;
	}
	public static List<String> mentionsBac(){
		List<String> list=new ArrayList<String>();
		list.add("T.Bien");
		list.add("Bien");
		list.add("A.Bien");
		return list;
	}
	public static List<String> academiesBac(){
		List<String> list=new ArrayList<String>();
		list.add("ACADÉMIE RABAT-SALÉ-ZMOUR-ZAIR");
		list.add("ACADÉMIE FÉS-BOULEMAN");
		list.add("ACADÉMIE BÉNI MELAL");
		return list;
	}
	public static List<String> provinces(){
		List<String> list=new ArrayList<String>();
		list.add("RABAT");
		list.add("KÉNITRA");
		list.add("CASABLANCA");
		return list;
	}
	public Object getUser(Compte compte){
		Object user=null;
		 	user=professeurService.getProfesseur(compte.getIduser());
		return user;
	}
}
