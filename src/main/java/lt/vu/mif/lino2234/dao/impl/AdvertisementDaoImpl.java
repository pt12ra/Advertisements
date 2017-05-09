package lt.vu.mif.lino2234.dao.impl;

import lt.vu.mif.lino2234.dao.AdvertisementDao;
import lt.vu.mif.lino2234.entities.Advertisement;
import lt.vu.mif.lino2234.entities.Advertisement_;
import lt.vu.mif.lino2234.entities.Board;
import lt.vu.mif.lino2234.entities.Board_;
import lt.vu.mif.lino2234.entities.User;
import lt.vu.mif.lino2234.entities.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
@Named(value = "advertisementDao")
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

    @Override
    public List<Advertisement> getAllByBoardId(Long boardId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<Board> root = cq.from(Board.class);
        cq.where(cb.equal(root.get(Board_.id), boardId));
        Join<Board, Advertisement> boardAdvertisementJoin = root.join(Board_.advertisements);
        CriteriaQuery<Advertisement> all = cq.select(boardAdvertisementJoin);
        TypedQuery<Advertisement> query = em.createQuery(all);
        return query.getResultList();
    }

    @Override
    public List<Advertisement> getAllByUserId(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get(User_.id), userId));
        Join<User, Advertisement> userAdvertisementJoin = root.join(User_.advertisements);
        CriteriaQuery<Advertisement> all = cq.select(userAdvertisementJoin);
        TypedQuery<Advertisement> query = em.createQuery(all);
        return query.getResultList();
    }

    @Override
    public List<Advertisement> getAllByBoardUser(Long boardId, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<Advertisement> root = cq.from(Advertisement.class);
        Join<Advertisement, User> advertisementUserJoin = root.join(Advertisement_.author);;
        Join<Advertisement, Board> advertisementBoardJoin = root.join(Advertisement_.board);
        cq.where(cb.and(cb.equal(advertisementBoardJoin.get(Board_.id), boardId), cb.equal(advertisementUserJoin.get(User_.id), userId)));
        CriteriaQuery<Advertisement> all = cq.select(root);
        TypedQuery<Advertisement> query = em.createQuery(all);
        return query.getResultList();
    }
}
