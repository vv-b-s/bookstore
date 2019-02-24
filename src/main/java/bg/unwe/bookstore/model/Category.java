package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
public class Category implements EntityModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID", nullable = false)
    private Integer id;

    @Column(nullable = false, name = "Name")
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
