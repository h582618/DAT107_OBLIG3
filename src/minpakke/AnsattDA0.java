package minpakke;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDA0 {

	private static EntityManagerFactory emf;

	public AnsattDA0() {
		emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
	}

	public Ansatt finnAnsattMedId(int ansattid) {

		EntityManager em = emf.createEntityManager();

		Ansatt p = null;

		try {
			p = em.find(Ansatt.class, ansattid);
		} finally {
			em.close();
		}
		return p;

	}

	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		String queryString = "Select a From Ansatt a " + "WHERE a.brukernavn = :brukernavn";

		EntityManager em = emf.createEntityManager();

		Ansatt p = null;

		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			p = query.getSingleResult();

		} finally {
			em.close();
		}
		return p;

	}

	public void skrivUtAlle() {

		String queryString = "Select a From Ansatt a";

		EntityManager em = emf.createEntityManager();

		List<Ansatt> ansatte = null;

		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			ansatte = query.getResultList();

			for (Ansatt e : ansatte) {
				System.out.println(e.toString());
			}

		} finally {
			em.close();
		}

	}

	public void oppdaterStilling(String stilling, int ansattId) {

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			Ansatt a = em.find(Ansatt.class, ansattId);
			
			tx.begin();
			
			if (a != null) {
				a.setStilling(stilling);
				em.merge(a);
			}

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	public void oppdaterAvdeling(Avdeling Avdeling, int ansattId) {

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			Ansatt a = em.find(Ansatt.class, ansattId);
			
			tx.begin();
			
			if (a != null && a.getAvdeling().getSjef() != a) {
				a.setAvdeling(Avdeling);
				em.merge(a);
			} else {
				System.out.println("Kan ikke bytte avdeling siden " + a.getNavn() + " allerede er sjef");
			}

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	
	
	
	public void oppdaterLonn(Integer maanadslonn, int ansattId) {

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			Ansatt a = em.find(Ansatt.class, ansattId);
			
			tx.begin();
			
			if (a != null) {
				a.setMaanadslonn(maanadslonn);
				em.merge(a);
			}

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public void leggTil(Ansatt p) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			
			tx.begin();
			em.persist(p);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}
}
