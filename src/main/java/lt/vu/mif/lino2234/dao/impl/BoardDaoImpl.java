package lt.vu.mif.lino2234.dao.impl;

import lt.vu.mif.lino2234.dao.BoardDao;
import lt.vu.mif.lino2234.entities.Board;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
@Named(value = "boardDao")
public class BoardDaoImpl implements BoardDao {

    @Inject
    private EntityManager em;

    @Override
    public Board save(Board entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public Board update(Board entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Board entity = em.find(Board.class, id);
        em.remove(entity);
    }

    @Override
    public List<Board> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Board> cq = cb.createQuery(Board.class);
        Root<Board> root = cq.from(Board.class);
        CriteriaQuery<Board> all = cq.select(root);
        TypedQuery<Board> query = em.createQuery(all);
        return query.getResultList();
    }
}
