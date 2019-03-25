package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "Authors")
public class Author implements EntityModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuthorID", nullable = false)
    private Integer id;

    @Column(nullable = false, name = "FirstName")
    private String firstName;

    @Column(nullable = false, name = "LastName")
    private String lastName;

    @Column(name = "Born")
    private Integer born;

    @Column(name = "Country")
    private String country;

    @Lob
    @Column(name = "Photo", columnDefinition = "varbinary(MAX)")
    private byte[] photo;

    public Author() {

    }

    public Author(String firstName, String lastName, Integer born, String country, byte[] photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.born = born;
        this.country = country;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBorn() {
        return born;
    }

    public void setBorn(Integer born) {
        this.born = born;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
