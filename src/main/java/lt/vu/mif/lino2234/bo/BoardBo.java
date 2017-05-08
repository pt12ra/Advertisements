package lt.vu.mif.lino2234.bo;

import lt.vu.mif.lino2234.views.BoardView;

import java.util.List;

public interface BoardBo {

    BoardView saveToEntity(BoardView view);
    BoardView findOne(Long id);
    void delete(Long id);
    List<BoardView> getAll();
    List<BoardView> getAllByUserId(Long userId);
}
