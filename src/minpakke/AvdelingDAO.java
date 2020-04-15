package minpakke;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingDAO {
	private static EntityManagerFactory emf;
	
	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
	}
	
	public Avdeling finnAvdelingMedId(int id) {
		
		EntityManager em = emf.createEntityManager();
		
		Avdeling a = null;
		
		try {
			a = em.find(Avdeling.class, id);
			
			
		} finally {
			em.close();
		}
		
		return a;
	}
	public void skrivUtAlle(int id) {



		EntityManager em = emf.createEntityManager();
		
		String queryString1 = "SELECT a FROM Ansatt a " + 
				"WHERE a.avdeling = :avdeling" 
//				+ " AND a.avdeling.sjef NOT LIKE :sjef";  
//				+" AND SELECT k from Avdeling k "
//				+ "WHERE k.avdelingId = :avdelingId AND k.sjef.avdelingId = :a"
;


		Avdeling a = em.find(Avdeling.class, id);
		
		List<Ansatt> ansatte = null;

		try {
			
			
			TypedQuery<Ansatt> query = em.createQuery(queryString1, Ansatt.class);
			query.setParameter("avdeling", a);
	

			
			ansatte = query.getResultList();

			for (Ansatt e : ansatte) {
				if( e == a.getSjef()) {
					System.out.println("Sjef " + e.toString());
				} else {
				System.out.println(e.toString());
				}
			}

			

		} finally {
			em.close();
		}

	}
	public void leggTil(Avdeling p, Ansatt sjef) {
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			
			tx.begin();
			em.persist(p);
			sjef.setAvdeling(p);
			em.merge(sjef);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}
}
