package buildWeek.DAO;

import buildWeek.Entity.Biglietto;
import buildWeek.Entity.DistributoreAutomatico;
import buildWeek.Entity.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DistributoreAutomaticoDao {

    private EntityManager em;

    public DistributoreAutomaticoDao(EntityManager em) {
        this.em = em;
    }

    public void save(List<DistributoreAutomatico> distributoreAutomatic) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (DistributoreAutomatico distributore : distributoreAutomatic) {
            em.persist(distributore);
        }

        et.commit();
    }

    public DistributoreAutomatico getDistributoreById (int id) {
        return em.find(DistributoreAutomatico.class, id);
    }

    public void delete (int id) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        DistributoreAutomatico distributoreAutomatico = getDistributoreById(id);

        if (distributoreAutomatico != null) {
            em.remove(distributoreAutomatico);
        } else {
            System.out.println("Biglietto non presente");
        }

        et.commit();
    }

}
