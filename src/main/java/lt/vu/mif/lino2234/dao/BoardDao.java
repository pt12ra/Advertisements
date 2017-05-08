package lt.vu.mif.lino2234.dao;

import lt.vu.mif.lino2234.entities.Board;

import java.util.List;

public interface BoardDao {

    Board save(Board entity);
    Board findOne(Long id);
    Board update(Board entity);
    void delete(Long id);
    List<Board> getAll();
    List<Board> getAllByUserId(Long userId);
}
