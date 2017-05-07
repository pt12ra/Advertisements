package lt.vu.mif.lino2234.views;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserView implements Serializable {

    private static final long serialVersionUID = 6421655733488654998L;

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;
    private List<BoardView> subscriptions;
    private List<AdvertisementView> advertisements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<BoardView> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<BoardView> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<AdvertisementView> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementView> advertisements) {
        this.advertisements = advertisements;
    }
}
