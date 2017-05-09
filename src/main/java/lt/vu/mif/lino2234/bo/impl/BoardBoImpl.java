package lt.vu.mif.lino2234.bo.impl;

import lt.vu.mif.lino2234.bo.AdvertisementBo;
import lt.vu.mif.lino2234.bo.BoardBo;
import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.dao.AdvertisementDao;
import lt.vu.mif.lino2234.dao.BoardDao;
import lt.vu.mif.lino2234.dao.UserDao;
import lt.vu.mif.lino2234.entities.Board;
import lt.vu.mif.lino2234.views.AdvertisementView;
import lt.vu.mif.lino2234.views.BoardView;
import lt.vu.mif.lino2234.views.UserView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named(value = "boardBo")
@RequestScoped
public class BoardBoImpl implements BoardBo {

    @Inject
    private BoardDao boardDao;
    @Inject
    private UserDao userDao;
    @Inject
    private AdvertisementDao advertisementDao;
    @Inject
    private UserBo userBo;
    @Inject
    private AdvertisementBo advertisementBo;

    @Override
    @Transactional
    public BoardView saveToEntity(BoardView view) {
        Objects.requireNonNull(view, "Object 'view' must not be null");

        Board entity = view.getId() != null ? boardDao.findOne(view.getId()) : new Board();
        entity.setId(view.getId());
        entity.setTitle(view.getTitle());
        entity.setAdvertisements(new ArrayList<>());
        if (view.getAdvertisements() != null) {
            for(AdvertisementView advertisementView : view.getAdvertisements()) {
                entity.getAdvertisements().add(advertisementDao.findOne(advertisementView.getId()));
            }
        }
        entity.setSubscribers(new ArrayList<>());
        if (view.getSubscribers() != null) {
            for(UserView userView : view.getSubscribers()) {
                entity.getSubscribers().add(userDao.findOne(userView.getId()));
            }
        }
        return buildView(entity.getId() == null ? boardDao.save(entity) : boardDao.update(entity));
    }

    @Override
    @Transactional
    public BoardView findOne(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        return buildView(boardDao.findOne(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        boardDao.delete(id);
    }

    @Override
    @Transactional
    public List<BoardView> getAll() {
        return boardDao.getAll().stream().map(this::buildView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BoardView> getAllByUserId(Long userId) {
        Objects.requireNonNull(userId, "Object 'userId' must not be null");

        return boardDao.getAllByUserId(userId).stream().map(this::buildView).collect(Collectors.toList());
    }

    private BoardView buildView (Board entity) {
        Objects.requireNonNull(entity, "Object 'entity' must not be null");

        BoardView view = new BoardView();
        view.setId(entity.getId());
        view.setTitle(entity.getTitle());
        view.setSubscribers(new ArrayList<>());
        view.setAdvertisements(new ArrayList<>());
        return view;
    }
}
