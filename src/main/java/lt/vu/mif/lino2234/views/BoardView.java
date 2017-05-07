package lt.vu.mif.lino2234.views;

import java.io.Serializable;
import java.util.List;

public class BoardView implements Serializable {

    private static final long serialVersionUID = -4638199187710319511L;

    private Long id;
    private String title;
    private List<UserView> subscribers;
    private List<AdvertisementView> advertisements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserView> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<UserView> subscribers) {
        this.subscribers = subscribers;
    }

    public List<AdvertisementView> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementView> advertisements) {
        this.advertisements = advertisements;
    }
}
