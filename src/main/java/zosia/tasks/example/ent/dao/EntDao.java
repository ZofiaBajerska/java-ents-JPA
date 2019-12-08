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

    public List<Ent> findByCopse(Copse copse) {

        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ent> query = cb.createQuery(Ent.class);
        Root<Ent> root = query.from(Ent.class);
        query.select(root).where(cb.equal(root.get(Ent_.copse), copse));
        List<Ent> list = em.createQuery(query).getResultList();
        em.close();
        return list;

    }

    public void delete(Ent ent) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ent = em.merge(ent);
//        ent.getCopse().getEnt().remove(ent);
        em.remove(ent);
        em.getTransaction().commit();
    }

}
