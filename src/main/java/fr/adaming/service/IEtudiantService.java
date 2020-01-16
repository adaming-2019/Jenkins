package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

public interface IEtudiantService {
	
	public List<Etudiant> getAllEtudiants(Formateur fIn);
	
	public Etudiant getEtudiantById(Etudiant eIn);
	
	public Etudiant addEtudiant(Etudiant eIn, Formateur fIn);
	
	public boolean deleteEtudiant(Etudiant eIn, Formateur fIn);
	
	public boolean updateEtudiant(Etudiant eIn,Formateur fIn);

}
