package buildWeek.DAO;

import buildWeek.Entity.DistributoreAutomatico;
import buildWeek.Entity.Rivenditore;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RivenditoreDao {

    private EntityManager em;

    public RivenditoreDao(EntityManager em) {
        this.em = em;
    }

    public void save(List<Rivenditore> rivenditori) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (Rivenditore rivenditore : rivenditori) {
            em.persist(rivenditore);
        }
        et.commit();
    }

    public Rivenditore getRivenditoreById (int id) {
        return em.find(Rivenditore.class, id);
    }

    public void delete (int id) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        Rivenditore rivenditore = getRivenditoreById(id);

        if (rivenditore != null) {
            em.remove(rivenditore);
        } else {
            System.out.println("Rivenditore non attivo");
        }

        et.commit();
    }

}
