package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryOptions")
public class DeliveryOption implements EntityModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "DeliveryOptionID")
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    public DeliveryOption() {

    }

    public DeliveryOption(String name) {
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
