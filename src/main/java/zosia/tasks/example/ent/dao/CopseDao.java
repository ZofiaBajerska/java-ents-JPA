package zosia.tasks.example.ent.dao;

import zosia.tasks.example.ent.model.Copse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CopseDao {

    private final EntityManagerFactory emf;

    public CopseDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Copse> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Copse> list = em.createQuery("select c from Copse c", Copse.class).getResultList();
        em.close();
        return list;
    }

    public void delete(Copse copse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(copse));
        em.getTransaction().commit();
    }

}
