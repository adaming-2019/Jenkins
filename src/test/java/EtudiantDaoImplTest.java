import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IEtudiantDao;
import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class EtudiantDaoImplTest {

	// injecter un objet de type EtudiantDaoImpl
	@Autowired
	private IEtudiantDao etuDao;

	Formateur fIn;

	@Before
	public void setUp() {
		this.fIn = new Formateur();
		// on donne un id au formateur sinon il nous dira qu'il n'existe pas
		fIn.setId(1);
	}

	@Test
	@Transactional(readOnly = true)
	@Rollback(true)
	public void testGetAllEtudiantSize() {

		int attendu = 3;

		List<Etudiant> liste = etuDao.getAllEtudiants(fIn);
		int resultObtenu = liste.size();

		// ou : int obtenu=etuDao.getAllEtudiants(fIn).size();

		assertEquals(attendu, resultObtenu);
	}

	@Test
	@Transactional(readOnly = true)
	@Rollback(true)
	public void testGetAllEtudiant() {

		String resultAttendu = "mrch";

		List<Etudiant> liste = etuDao.getAllEtudiants(fIn);

		String resultObtenu = liste.get(0).getNom();

		assertEquals(resultAttendu, resultObtenu);

	}

	@Test
	@Transactional(readOnly = true)
	@Rollback(true)
	public void testGetById() {
		Etudiant etu = new Etudiant();
		etu.setId(4);

		Etudiant eObtenu = etuDao.getEtudiantById(etu);

		assertNotNull(eObtenu);
	}

	@Test
	@Transactional(readOnly = true)
	@Rollback(true)
	public void getEtudiantById() {

		Etudiant etu = new Etudiant();
		String resultAttendu="mrch";
		etu.setId(4);


		String eObtenu = etuDao.getEtudiantById(etu).getNom();

		assertEquals(resultAttendu, eObtenu);

	}
	
	@Test
	@Transactional
	public void testAddEtudiant() {
		
		List<Etudiant> listeAvant=etuDao.getAllEtudiants(fIn);
		int avant=listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(fIn);
		etuDao.addEtudiant(etu);
		
		List<Etudiant> listeApres=etuDao.getAllEtudiants(fIn);
		int apres=listeApres.size();
		
		assertEquals(avant+1, apres);
	}
	
	@Test
	@Transactional
	public void testAddEtudiantNom() {
		Etudiant expected=new Etudiant("Mrch", "Nana", null);
		expected = etuDao.addEtudiant(expected);
		
		Etudiant result=etuDao.getEtudiantById(expected);
		
		assertEquals(expected.getNom(), result.getNom());
	}
	
	@Test
	@Transactional
	public void testDeleteEtudiant() {
		
		List<Etudiant> listeAvant=etuDao.getAllEtudiants(fIn);
		int avant=listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(fIn);
		etu.setId(4);
		etu=etuDao.getEtudiantById(etu);
		etuDao.deleteEtudiant(etu);
		
		List<Etudiant> listeApres=etuDao.getAllEtudiants(fIn);
		int apres=listeApres.size();
		
		assertEquals(avant-1, apres);
	}
	
	@Test
	@Transactional
	public void testUpdateEtudiant() {
		
		Etudiant eIn=new Etudiant(4, "Mrch", "Nana", null);
		etuDao.updateEtudiant(eIn);
		
		Etudiant eOut=etuDao.getEtudiantById(eIn);
		
		
		assertEquals(eIn.getNom(), eOut.getNom());
		
		
	}
	
}
