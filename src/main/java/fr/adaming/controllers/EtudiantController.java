package fr.adaming.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;
import fr.adaming.service.IEtudiantService;

@Controller
@RequestMapping("/ecole")
public class EtudiantController {

	// Trasformation de l'association UML en JAVA
	@Autowired
	private IEtudiantService etuService;

	private Formateur formateur;

	// le setter pour l'injection de dependance
	public void setEtuService(IEtudiantService etuService) {
		this.etuService = etuService;
	}

	@PostConstruct
	public void init() {
		this.formateur = new Formateur(1, "a", "a");
	}

	// la m�thode appel�e pour convertir les valeurs des params de la requ�te en
	// objet java (entre autres la date)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// l'objet web databinder sert � faire le lien entre les params de la requete et
		// les objets java.
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);

		// la m�thode registerCustomEditor() :sert � configuer la conversion du
		// param�tre re�u
		// l'objet CustomDateEditor : sert � lier la date re�ue comme param�tre
		// de la requ�te � l'attribut de l'objet �tudiant
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));

	}

	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		// recuperer la liste des etudiants du formateur
		List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);

		return new ModelAndView("accueil", "etudiants", listeEtudiants);
	}

	// fonctionnalit� ajouter �tudiant
	@RequestMapping(value = "/afficheAdd", method = RequestMethod.GET)
	public String afficheAjouter(Model modele) {
		modele.addAttribute("eAdd", new Etudiant());
		return "ajout";

	}

	@RequestMapping(value = "/submitAdd", method = RequestMethod.POST)
	public String soumettreAjouter(ModelMap modele, @ModelAttribute("eAdd") Etudiant eIn) {
		// appel de la m�thode service
		Etudiant eOut = etuService.addEtudiant(eIn, this.formateur);

		if (eOut.getId() != 0) {
			// r�cup�rer la liste des �tudiants du formateur
			List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);

			modele.addAttribute("etudiants", listeEtudiants);
			return "accueil";

		} else {
			// redirection vers la m�thode afficheAdd pour r�afficher le formulaire et lui
			// asocier l'�tudiant comme mod�le
		}
		return "redirect:afficheAdd";
	}

	// fonctionnalit� modifier
	@RequestMapping(value = "/afficheUpdate", method = RequestMethod.GET)
	public ModelAndView afficheModifier() {
		return new ModelAndView("modif", "eUpdate", new Etudiant());
	}

	@RequestMapping(value = "/submitUpdate", method = RequestMethod.POST)
	public String soumettreModif(RedirectAttributes rda, Model modele, @ModelAttribute("eUpdate") Etudiant eIn) {

		boolean verif = etuService.updateEtudiant(eIn, this.formateur);
		if (verif) {
			return "redirect:liste";
		} else {
			rda.addFlashAttribute("msg", "l'�tudiant a modifier n'existe pas");
			return "redirect:modif";
		}

	}

	// formulaire de suppression
	@RequestMapping(value = "/afficheDelete", method = RequestMethod.GET)
	public String afficheSupprimer() {
		return "suppression";
	}

	@RequestMapping(value = "/submitDelete", method = RequestMethod.GET)
	public String soumettreSupprimer(RedirectAttributes rda, @RequestParam("pId") int idIn) {

		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		boolean verif = etuService.deleteEtudiant(eIn, this.formateur);
		if (verif) {
			return "redirect:liste";
		} else {
			// l'objet RedirectAttributes sert � transporter les attributs du
			// modele mvc lors de la redirection
			rda.addFlashAttribute("msg", "l'�tudiant a supprimer n'existe pas");
			return "redirect:afficheDelete";

		}
	}

	// formulaire de recherche
	@RequestMapping(value = "/afficheGet", method = RequestMethod.GET)
	public ModelAndView afficheRechercher() {
		return new ModelAndView("recherche", "eRech", new Etudiant());
	}

	@RequestMapping(value = "/submitGet", method = RequestMethod.GET)
	public String soumettreSRechercher(RedirectAttributes rda, @RequestParam("pId") int idIn) {

		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		Etudiant eOut = etuService.getEtudiantById(eIn);

		if (eOut != null) {

			rda.addFlashAttribute("eGet", eOut);
			return "redirect:afficheGet";
		} else {
			rda.addFlashAttribute("msg", "l'�tudiant recherch� n'existe pas");
			return "redirect:afficheGet";
		}
	}
	
	@RequestMapping(value = "/linkUpdate", method = RequestMethod.GET)
	public String getModifLien(Model modele, @RequestParam("pId") int idIn) {
		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		Etudiant eOut = etuService.getEtudiantById(eIn);
		
		modele.addAttribute("eUpdate", eOut);
		
		return "modif";
		
	}
}
