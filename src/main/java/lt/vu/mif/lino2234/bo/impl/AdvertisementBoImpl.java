package lt.vu.mif.lino2234.bo.impl;

import lt.vu.mif.lino2234.bo.AdvertisementBo;
import lt.vu.mif.lino2234.bo.BoardBo;
import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.dao.AdvertisementDao;
import lt.vu.mif.lino2234.dao.BoardDao;
import lt.vu.mif.lino2234.dao.UserDao;
import lt.vu.mif.lino2234.entities.Advertisement;
import lt.vu.mif.lino2234.views.AdvertisementView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named(value = "advertisementBo")
@RequestScoped
public class AdvertisementBoImpl implements AdvertisementBo {

    @Inject
    private AdvertisementDao advertisementDao;
    @Inject
    private UserBo userBo;
    @Inject
    private BoardBo boardBo;
    @Inject
    private UserDao userDao;
    @Inject
    private BoardDao boardDao;

    @Override
    @Transactional
    public AdvertisementView saveToEntity(AdvertisementView view) {
        Objects.requireNonNull(view, "Object 'view' must not be null");

        Advertisement entity = view.getId() != null ? advertisementDao.findOne(view.getId()) : new Advertisement();
        entity.setId(view.getId());
        entity.setTitle(view.getTitle());
        entity.setAuthor(userDao.findOne(view.getAuthor().getId()));
        entity.setBoard(boardDao.findOne(view.getBoard().getId()));
        entity.setDescription(view.getDescription());
        entity.setPrice(new BigDecimal(view.getPrice()));
        entity.setPublicationTime(view.getPublicationTime() != null ? LocalDateTime.parse(view.getPublicationTime()) : LocalDateTime.now());
        return buildView(entity.getId() == null ? advertisementDao.save(entity) : advertisementDao.update(entity));
    }

    @Override
    @Transactional
    public AdvertisementView findOne(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        return buildView(advertisementDao.findOne(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        advertisementDao.delete(id);
    }

    @Override
    @Transactional
    public List<AdvertisementView> getAll() {
        return advertisementDao.getAll().stream().map(this::buildView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AdvertisementView> getAllByBoardId(Long boardId) {
        Objects.requireNonNull(boardId, "Object 'boardId' must not be null");

        return advertisementDao.getAllByBoardId(boardId).stream().map(this::buildView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AdvertisementView> getAllByUserId(Long userId) {
        Objects.requireNonNull(userId, "Object 'userId' must not be null");

        return advertisementDao.getAllByUserId(userId).stream().map(this::buildView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AdvertisementView> getAllByBoardUser(Long boardId, Long userId) {
        Objects.requireNonNull(boardId, "Object 'boardId' must not be null");
        Objects.requireNonNull(userId, "Object 'userId' must not be null");

        return advertisementDao.getAllByBoardUser(boardId, userId).stream().map(this::buildView).collect(Collectors.toList());
    }

    private AdvertisementView buildView (Advertisement entity) {
        Objects.requireNonNull(entity, "Object 'entity' must not be null");

        AdvertisementView view = new AdvertisementView();
        view.setId(entity.getId());
        view.setTitle(entity.getTitle());
        view.setDescription(entity.getDescription());
        view.setPrice(entity.getPrice().toString());
        view.setPublicationTime(entity.getPublicationTime().toString());
        view.setAuthor(userBo.findOne(entity.getAuthor().getId()));
        view.setBoard(boardBo.findOne(entity.getBoard().getId()));
        return view;
    }
}
