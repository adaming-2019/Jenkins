package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

public interface IEtudiantDao {

	public List<Etudiant> getAllEtudiants(Formateur fIn);

	public Etudiant getEtudiantById(Etudiant eIn);
	
	public Etudiant addEtudiant(Etudiant eIn);

	public void deleteEtudiant(Etudiant eIn);
	
	public void updateEtudiant(Etudiant eIn);
}
