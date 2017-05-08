package lt.vu.mif.lino2234.dao.impl;

import lt.vu.mif.lino2234.dao.UserDao;
import lt.vu.mif.lino2234.entities.User;

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
@Named(value = "userDao")
public class UserDaoImpl implements UserDao {

    @Inject
    private EntityManager em;

    @Override
    public User save(User entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User update(User entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        User entity = em.find(User.class, id);
        em.remove(entity);
    }

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(root);
        TypedQuery<User> query = em.createQuery(all);
        return query.getResultList();
    }
}
