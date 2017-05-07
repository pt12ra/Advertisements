package lt.vu.mif.lino2234.bo;

import lt.vu.mif.lino2234.views.AdvertisementView;

import java.util.List;

public interface AdvertisementBo {

    AdvertisementView saveToEntity(AdvertisementView view);
    AdvertisementView findOne(Long id);
    void delete(Long id);
    List<AdvertisementView> getAll();
}
