package org.ensah.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ensah.domains.Compte;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Professeur;
import org.ensah.service.EtudiantService;
import org.ensah.service.GuestService;
import org.ensah.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@Autowired
	GuestService guestService;
	@Autowired
	ProfesseurService professeurService;
	@Autowired
	EtudiantService etudiantService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index(Model model,Principal principal){
		model.addAttribute("title","index");
		System.out.println("Sa Marche 1");
		if(principal!=null){
			Compte compte=guestService.signin(principal.getName());
			model.addAttribute("user", getUser(compte));
		} 
		System.out.println("-----------Note Module------------------");
//		System.out.println(getNoteModule("Technlogie Destribué"));
//		model.addAttribute("test", etudiantService.getEtudiant(1L));
//		model.addAttribute("nomModule","Technlogie Destribué");
//		model.addAttribute("id",1L);
		model.addAttribute("notes",etudiantService.getEtudiant(1L).getLesNotes());
		return "index";
	}
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinGet(Model model) {
        model.addAttribute("title", "Sign in");
        return "signin";
	}
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String home(Model model,Principal principal) {
//		 model.addAttribute("title", "Home");
//		 Compte compte=guestService.signin(principal.getName());
//		 model.addAttribute("user", getUser(compte));
//        model.addAttribute("compte", compte);
//        return "home";
//	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	public Object getUser(Compte compte){
		Object user=null;
		if(compte.getRole().getRoleName().equals("ADMIN") || compte.getRole().getRoleName().equals("PROFESSEUR")){
			Professeur professeur=professeurService.getProfesseur(compte.getIduser());
			user=professeur;
		}
		else{
			Etudiant etudiant=etudiantService.getEtudiant(compte.getIduser());
			user=etudiant;
		}
		return user;
	}
	

}
