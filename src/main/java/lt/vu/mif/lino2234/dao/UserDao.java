package lt.vu.mif.lino2234.dao;

import lt.vu.mif.lino2234.entities.User;

import java.util.List;

public interface UserDao {

    User save(User entity);
    User findOne(Long id);
    User update(User entity);
    void delete(Long id);
    List<User> getAll();
    List<User> getAllByBoardId(Long boardId);
}
