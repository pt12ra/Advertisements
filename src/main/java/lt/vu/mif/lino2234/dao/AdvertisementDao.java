package lt.vu.mif.lino2234.dao;

import lt.vu.mif.lino2234.entities.Advertisement;

import java.util.List;

public interface AdvertisementDao {

    Advertisement save(Advertisement entity);
    Advertisement findOne(Long id);
    Advertisement update(Advertisement entity);
    void delete(Long id);
    List<Advertisement> getAll();
    List<Advertisement> getAllByBoardId(Long boardId);
    List<Advertisement> getAllByUserId(Long userId);
    List<Advertisement> getAllByBoardUser(Long boardId, Long userId);
}
