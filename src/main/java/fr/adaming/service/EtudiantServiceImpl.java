package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IEtudiantDao;
import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

	// transformation de l'association UML en java
	private IEtudiantDao etuDao;

	@Autowired
	public void setEtuDao(IEtudiantDao etuDao) {
		this.etuDao = etuDao;
	}

	@Override
	public List<Etudiant> getAllEtudiants(Formateur fIn) {
		// appel de la méthode dao
		return etuDao.getAllEtudiants(fIn);
	}

	@Override
	public Etudiant getEtudiantById(Etudiant eIn) {
		// appel de la méthode dao
		return etuDao.getEtudiantById(eIn);
	}

	@Override
	public Etudiant addEtudiant(Etudiant eIn, Formateur fIn) {
		//stocker le formateur dans l'étudiant
		eIn.setFormateur(fIn);
		//appel de la méthode Dao
		return etuDao.addEtudiant(eIn);
	}

	@Override
	public boolean deleteEtudiant(Etudiant eIn, Formateur fIn) {
		Etudiant eOut = etuDao.getEtudiantById(eIn);
		
		if (eOut!=null && fIn.getId()==eOut.getFormateur().getId()){
			//appel de la methode Dao pour supprimer l'etu
			etuDao.deleteEtudiant(eOut);
			return true; 
		}
		return false;
	}

	@Override
	public boolean updateEtudiant(Etudiant eIn, Formateur fIn) {
		// recuperer l'etudiant
		Etudiant eOut = etuDao.getEtudiantById(eIn);
		
		if (eOut!=null && fIn.getId()==eOut.getFormateur().getId()){
			
			// modifier l'objet récupéré avec les nouvelles valeurs en java
			eOut.setNom(eIn.getNom());
			eOut.setPrenom(eIn.getPrenom());
			eOut.setDn(eIn.getDn());
			
			//appel de la methode Dao pour supprimer l'etu
			etuDao.updateEtudiant(eOut);
			return true; 
		}
		return false;
	}

}
