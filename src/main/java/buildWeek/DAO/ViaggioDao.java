package buildWeek.DAO;

import buildWeek.Entity.Viaggio;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ViaggioDao {

    private EntityManager em;

    public ViaggioDao(EntityManager em) {
        this.em = em;
    }

    public void save(Viaggio viaggio) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(viaggio);
        et.commit();
    }

    public Viaggio getAbbonamentoById (int id) {
        return em.find(Viaggio.class, id);
    }

    public void delete (int id) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        Viaggio viaggio = getAbbonamentoById(id);

        if (viaggio != null) {
            em.remove(viaggio);
        } else {
            System.out.println("Abbonamento non presente");
        }

        et.commit();
    }

}
