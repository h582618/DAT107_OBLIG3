package minpakke;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Prosjekt_AnsatteDAO {

	private static EntityManagerFactory emf;

	public Prosjekt_AnsatteDAO() {
		emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
	}

	public Prosjekt_Ansatte finnProsjekt_A(int ansattId, int prosjektId) {
		String queryString = "Select a From Prosjekt_Ansatte a " + "Where a.prosjekt = :prosjektid "
				+ "AND a.ansatt = :ansattid";

		EntityManager em = emf.createEntityManager();
		Prosjekt_Ansatte prosjetkA = null;

		

		try {
			Prosjekt p = em.find(Prosjekt.class, prosjektId);
			Ansatt a = em.find(Ansatt.class, ansattId);
			TypedQuery<Prosjekt_Ansatte> query = em.createQuery(queryString, Prosjekt_Ansatte.class);
			query.setParameter("prosjektid", p);
			query.setParameter("ansattid", a);
			prosjetkA = query.getSingleResult();

		} catch (Throwable e) {
			System.out.println("Prosjektet " + prosjektId + " med ansattId " + ansattId + " eksisterer ikke");
		} finally {
			em.close();

		}
		return prosjetkA;
	}


	public void leggTilAnsattTilProsjekt(int id, Prosjekt_Ansatte a) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		Prosjekt p = em.find(Prosjekt.class, id);
		
		try {

			tx.begin();
			a.setProsjekt(p);
			a.getAnsatt().addProsjekt(a);
			em.merge(a);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}

	public void registrerTimer(int timer, Prosjekt_Ansatte a) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			a.leggTilTimer(timer);
			em.merge(a);
			tx.commit();
	
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}

	public void skrivUtProsjekt(int id) {

		String queryString = "Select a From Prosjekt_Ansatte a " + "Where a.prosjekt = :prosjektid";

		EntityManager em = emf.createEntityManager();
		Prosjekt p = em.find(Prosjekt.class, id);
		List<Prosjekt_Ansatte> ansatte = null;

		try {
			TypedQuery<Prosjekt_Ansatte> query = em.createQuery(queryString, Prosjekt_Ansatte.class);
			query.setParameter("prosjektid", p);
			ansatte = query.getResultList();

			System.out.println(p.toString());

			for (Prosjekt_Ansatte e : ansatte) {
				System.out.println(e.toString());
			}

		} finally {
			em.close();
		}
	}
}
