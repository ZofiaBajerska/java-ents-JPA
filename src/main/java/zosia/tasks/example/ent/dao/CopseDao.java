package zosia.tasks.example.ent.dao;

import zosia.tasks.example.ent.model.Copse;

import zosia.tasks.example.ent.model.Copse_;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.model.Ent_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
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

    public List<Copse> findEmpty(){

        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Copse> query = cb.createQuery(Copse.class);
        Root<Copse> root = query.from(Copse.class);
        query.where(cb.isEmpty(root.get(Copse_.ents)));
        List<Copse> list = em.createQuery(query).getResultList();
        em.close();
        return list;
    }

    public void delete(Copse copse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(copse));
        em.getTransaction().commit();
    }

    public void add(Copse copse){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        copse = em.merge(copse);
        em.persist(copse);
        em.getTransaction().commit();
        em.close();
    }
}
