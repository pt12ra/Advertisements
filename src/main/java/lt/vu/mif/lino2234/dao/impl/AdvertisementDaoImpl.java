package lt.vu.mif.lino2234.dao.impl;

import lt.vu.mif.lino2234.dao.AdvertisementDao;
import lt.vu.mif.lino2234.entities.Advertisement;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdvertisementDaoImpl implements AdvertisementDao {

    @Inject
    private EntityManager em;

    @Override
    public Advertisement save(Advertisement entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Advertisement findOne(Long id) {
        return em.find(Advertisement.class, id);
    }

    @Override
    public Advertisement update(Advertisement entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Advertisement entity = em.find(Advertisement.class, id);
        em.remove(entity);
    }

    @Override
    public List<Advertisement> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<Advertisement> root = cq.from(Advertisement.class);
        CriteriaQuery<Advertisement> all = cq.select(root);
        TypedQuery<Advertisement> query = em.createQuery(all);
        return query.getResultList();
    }
}
