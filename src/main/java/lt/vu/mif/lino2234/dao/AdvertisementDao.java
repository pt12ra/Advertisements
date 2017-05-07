package lt.vu.mif.lino2234.dao;

import lt.vu.mif.lino2234.entities.Advertisement;

import java.util.List;

public interface AdvertisementDao {

    void save(Advertisement entity);
    Advertisement findOne(Long id);
    Advertisement update(Advertisement entity);
    void delete(Long id);
    List<Advertisement> getAll();
}
