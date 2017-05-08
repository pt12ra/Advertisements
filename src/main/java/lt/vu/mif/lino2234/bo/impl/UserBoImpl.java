package lt.vu.mif.lino2234.bo.impl;

import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.dao.UserDao;
import lt.vu.mif.lino2234.entities.User;
import lt.vu.mif.lino2234.views.UserView;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named(value = "userBo")
@SessionScoped
public class UserBoImpl implements UserBo, Serializable{

    @Inject
    private UserDao userDao;

    @Override
    @Transactional
    public UserView saveToEntity(UserView view) {
        Objects.requireNonNull(view, "Object 'view' must not be null");

        User entity = view.getId() != null ? userDao.findOne(view.getId()) : new User();
        entity.setId(view.getId());
        entity.setUsername(view.getUsername());
        entity.setName(view.getName());
        entity.setSurname(view.getSurname());
        entity.setPhoneNumber(view.getPhoneNumber());
        entity.setEmail(view.getEmail());
        entity.setRegistrationDate(view.getRegistrationDate() != null ? view.getRegistrationDate() : LocalDate.now());
        entity.setSubscriptions(new ArrayList<>());
        entity.setAdvertisements(new ArrayList<>());
        return buildView(entity.getId() == null ? userDao.save(entity) : userDao.update(entity));
    }

    @Override
    @Transactional
    public UserView findOne(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        return buildView(userDao.findOne(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Object 'id' must not be null");

        userDao.delete(id);
    }

    @Override
    @Transactional
    public List<UserView> getAll() {
        return userDao.getAll().stream().map(this::buildView).collect(Collectors.toList());
    }

    private UserView buildView (User entity) {
        Objects.requireNonNull(entity, "Object 'entity' must not be null");

        UserView view = new UserView();
        view.setId(entity.getId());
        view.setUsername(entity.getUsername());
        view.setName(entity.getName());
        view.setSurname(entity.getSurname());
        view.setPhoneNumber(entity.getPhoneNumber());
        view.setEmail(entity.getEmail());
        view.setRegistrationDate(entity.getRegistrationDate() != null ? view.getRegistrationDate() : LocalDate.now());
        view.setSubscriptions(new ArrayList<>());
        view.setAdvertisements(new ArrayList<>());
        return view;
    }
}
