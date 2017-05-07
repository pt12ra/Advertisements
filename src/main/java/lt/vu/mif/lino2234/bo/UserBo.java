package lt.vu.mif.lino2234.bo;

import lt.vu.mif.lino2234.views.UserView;

import java.util.List;

public interface UserBo {

    UserView saveToEntity(UserView view);
    UserView findOne(Long id);
    void delete(Long id);
    List<UserView> getAll();
}
