package zosia.tasks.example.ent.dao;

import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.model.Ent_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntDao {
    private final EntityManagerFactory emf;

    public EntDao(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Ent> getEntsFromCopse(Copse copse) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ent> query = builder.createQuery(Ent.class);
        Root<Ent> goblin = query.from(Ent.class);
        query.where(builder.equal(goblin.get(Ent_.copse), copse));
        query.select(goblin);
        List<Ent> goblins = em.createQuery(query).getResultList();
        em.close();
        return goblins;
    }

    public void add(Ent ent){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ent = em.merge(ent);
        ent.getCopse().getEnts().add(ent);
        em.persist(ent);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Ent ent) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ent = em.merge(ent);
        ent.getCopse().getEnts().remove(ent);
        em.remove(ent);
        em.getTransaction().commit();
        em.close();
    }
}
