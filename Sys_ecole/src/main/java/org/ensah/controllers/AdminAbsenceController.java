package org.ensah.controllers;


import java.security.Principal;


import org.ensah.domains.Compte;
import org.ensah.service.AdminAbsenceService;
import org.ensah.service.GuestService;
import org.ensah.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminAbsenceController {
	@Autowired
	private AdminAbsenceService adminAbsenceService;
	 @Autowired
	 private GuestService guestService;
	 @Autowired
	 private ProfesseurService professeurService;

	@RequestMapping(value="/admin/ListAbsence",method=RequestMethod.GET)
	public String showAbsences(Model model,@RequestParam(name="motCle",defaultValue="")String motCle,
			Principal principal){
//		model.addAttribute("Absences", adminAbsenceService.getAllAbsence());
		model.addAttribute("title", "List des Absences");
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("Absences", adminAbsenceService.getAbsenceMotCle("%"+motCle+"%"));
		model.addAttribute("motCle", motCle);
		return "admin/AbsenceList";
	}
	@RequestMapping(value="/admin/delete")
	public String deleteAbsence(Long id){
		adminAbsenceService.deleteAbsence(id);
		return "redirect:/admin/ListAbsence";
	}
	public Object getUser(Compte compte){
		Object user=null;
		 	user=professeurService.getProfesseur(compte.getIduser());
		return user;
	}
}
