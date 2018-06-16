package org.ensah.controllers;



import java.io.File;
import java.io.FileInputStream;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.ensah.domains.Compte;
import org.ensah.domains.Element;
import org.ensah.domains.Etudiant;
import org.ensah.domains.Professeur;
import org.ensah.repos.ModulRepos;
import org.ensah.service.AdminAbsenceService;
import org.ensah.service.CompteService;
import org.ensah.service.EtudiantService;
import org.ensah.service.GuestService;
import org.ensah.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CompteController {
	@Autowired
	GuestService guestService;
	@Autowired 
	ProfesseurService professeurService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired 
	CompteService compteService;
	@Autowired
	AdminAbsenceService adminAbsenceService;
	@Autowired
	ModulRepos moduleRepos;
	@Value("${dirimages}")
	private String dirImages;
	@RequestMapping(value="/mesInformations")
	public String mesInformatios(Model model,Principal principal){
		 model.addAttribute("title", "Informations | "+principal.getName());
		 Compte compte=guestService.signin(principal.getName());
		 model.addAttribute("user", getUser(compte));
		 if(compte.getRole().getRoleName().equals("ADMIN")){
			 Professeur professeur=professeurService.getProfesseur(compte.getIduser());
			 List<Element> elements=adminAbsenceService.getElementByidProf(compte.getIduser());
			 model.addAttribute("user", professeur);
			 model.addAttribute("element", elements);
			 model.addAttribute("roleUser","ADMIN");
		 }
		 else if(compte.getRole().getRoleName().equals("PROFESSEUR")){
			 Professeur professeur=professeurService.getProfesseur(compte.getIduser());
			 model.addAttribute("user", professeur);
			 model.addAttribute("roleUser","PROFESSEUR");
		 }
		else{
			 Etudiant etudiant=etudiantService.getEtudiant(compte.getIduser());
			 
			 model.addAttribute("user", etudiant);
			 model.addAttribute("roleUser","ETUDIANT");
		 }
		return "mesInformations";
	}
	@RequestMapping(value="/changePassword",method=RequestMethod.GET)
	public String changePassword(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		model.addAttribute("compte", compte);
		model.addAttribute("ancienPass",compte.getPassword());
		return "changePassword";
	}
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public String changePassword(Model model,Principal principal,
			@Valid Compte compte,
		BindingResult b 
		,@RequestParam String confirmPassword){

		
		if(b.hasErrors()){
			model.addAttribute("ancienPass",guestService.signin(principal.getName()).getPassword());
			model.addAttribute("user", getUser(guestService.signin(principal.getName())));
			return "changePassword";
		}
		if(!compte.getPassword().equals(confirmPassword)){
			model.addAttribute("ancienPass",guestService.signin(principal.getName()).getPassword());
			model.addAttribute("user", getUser(guestService.signin(principal.getName())));
			model.addAttribute("error","Confirm Password Don't match new Password");
			return "changePassword";
		}
		compteService.updateCompte(compte);
		return "redirect:/logout";
	}
	@RequestMapping(value="/changePicture",method=RequestMethod.GET)
	public String changePicture(Model model,Principal principal){
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		return "changePicture";
	}
	@RequestMapping(value="/changePicture",method=RequestMethod.POST)
	public String changePicture(Model model,Principal principal,
			@RequestParam(name="photo")MultipartFile file) throws Exception{
		Compte compte=guestService.signin(principal.getName());
		model.addAttribute("user", getUser(compte));
		if(!(file.isEmpty())){
			compteService.changePicture(compte,file.getOriginalFilename());
			file.transferTo(new File(dirImages+file.getOriginalFilename()));
			return "redirect:/";
			
		}
		model.addAttribute("error",true);
		return "changePicture";
	}
	@RequestMapping(value="/getPicture",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPicture(String nomPhoto) {
		try{
			File file=new File(dirImages+nomPhoto);
			return IOUtils.toByteArray(new FileInputStream(file));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return new byte[0];
	}
	//****************A construire***************//
	
//	public float test(Etudiant etd,Module module){
//		System.out.println("----------Appelle Test-----------");
////		System.out.println("---------nom Module-----"+nomModule);
//		float aide=0;
//		int k=0;
//		System.out.println("Nom"+etd.getNom());
////		try{
////		Etudiant etd=etudiantService.getEtudiant(1L);
////		System.out.println(etd.getId());
////		}catch(Exception e){
////			System.out.println(e.getMessage());
////		}
////		System.out.println("Sa Marche");
////		Module module=moduleRepos.findByNom(nomModule);
//////		Module module=this.element.getModule();
////		System.out.println("Sa Marche");
////		}catch(Exception e){
////			System.out.println(e.getMessage());
////		}
//////		if(module==null)
//////			throw new RuntimeException("Module Introuvable");
//////		if(etd==null)
//////			throw new RuntimeException("Etudiant Introuvable");
//		List<Element> leselements=module.getLesElements();
//		k=leselements.size();
//		for(int i=0;i<leselements.size();i++){
//			List<Note> lesNotes=leselements.get(i).getLesNotes();
//			System.out.println("Size les notes:"+lesNotes.size());
//			String annee=lesNotes.get(0).getAnnee();
//			aide=lesNotes.get(0).getMoyenne();
//			if(lesNotes.size()>1)
//				System.out.println(lesNotes.get(1).getAnnee());
//			for(int j=0;j<lesNotes.size();j++){
//				System.out.println("----------------");
//				System.out.println(annee);
//				if(lesNotes.get(j).getEtudiant().equals(etd)){
//					if(lesNotes.get(j).getAnnee().equals(annee)){
//						aide+=lesNotes.get(j).getMoyenne();
//					}else{
//						annee=lesNotes.get(j).getAnnee();
//						aide=lesNotes.get(j).getMoyenne();
//					}
//					
//					System.out.println(aide);
//				}else{
//					annee=lesNotes.get(j).getAnnee();
//					aide=lesNotes.get(j).getMoyenne();
//				}
//			}
//		}
//		return aide/k;
//	}
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
