package addcontact.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String phoneNumber;
    private String email;
    private String country;
    private Date dateCreated;

    public AddContact(Long id, String name, String category, String phoneNumber, String email, String country) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
        this.dateCreated = convertToIST(LocalDateTime.now());
    }

    public AddContact() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    private Date convertToIST(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.of("Asia/Kolkata")).toInstant());
    }

    @Override
    public String toString() {
        return "AddContact [id=" + id + ", name=" + name + ", category=" + category + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", country=" + country + ", dateCreated=" + dateCreated + "]";
    }
}
