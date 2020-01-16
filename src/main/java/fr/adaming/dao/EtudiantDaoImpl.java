package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Repository
public class EtudiantDaoImpl implements IEtudiantDao {

	private SessionFactory sf;

	@Autowired // cette annotation peut être appliquée à l'attribut ou bien au
				// setter
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Etudiant> getAllEtudiants(Formateur fIn) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// écrire la requête hql
		String req = "FROM Etudiant e WHERE e.formateur.id=:pIdF";

		// créer un Query
		Query query = s.createQuery(req);

		// passage des valeurs aux paramètres
		query.setParameter("pIdF", fIn.getId());

		// envoi de la requête et récupération du résultat
		List<Etudiant> listeOut = query.list();

		return listeOut;
	}

	@Override
	public Etudiant getEtudiantById(Etudiant eIn) {
		// récupérer la session en cours
		Session s = sf.getCurrentSession();

		// La méthode de la session pour récupére run etudiant by id
		Etudiant eOut=(Etudiant) s.get(Etudiant.class, eIn.getId());
		

		return eOut;
	}

	@Override
	public Etudiant addEtudiant(Etudiant eIn) {
		//récupérer la session 
		Session s=sf.getCurrentSession();
		
		//ajouter un étudiant
		s.save(eIn);
		
	
		return eIn;
	}

	@Override
	public void deleteEtudiant(Etudiant eIn) {
		// recupere la session
		Session s = sf.getCurrentSession();
		
		//Supprime l'etudiant
		s.delete(eIn);
		
	}

	@Override
	public void updateEtudiant(Etudiant eIn) {
		// recuperer la session
		Session s= sf.getCurrentSession();
		
		// modifier l'étudiant
		s.saveOrUpdate(eIn);
		
	}

}
