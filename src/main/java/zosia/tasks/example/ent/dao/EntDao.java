package zosia.tasks.example.ent.dao;

import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.model.Ent_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
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
        Root<Ent> ent = query.from(Ent.class);
        query.where(builder.equal(ent.get(Ent_.copse), copse));
        query.select(ent);
        List<Ent> ents = em.createQuery(query).getResultList();
        em.close();
        return ents;
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

    public void trim(Copse copse, int from, int to){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Ent> query = cb.createCriteriaUpdate(Ent.class);
        Root<Ent> root = query.from(Ent.class);
        query.where(cb.equal(root.get(Ent_.copse), copse));
        query.where(cb.greaterThan(root.get(Ent_.height), from));
        query.set(root.get(Ent_.height), to);
        em.createQuery(query).executeUpdate();
        copse.getEnts().stream().filter(e -> e.getHeight() > from).forEach(e -> e.setHeight(to));
        em.getTransaction().commit();
        em.close();
    }
}
