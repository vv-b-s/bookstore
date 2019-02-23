package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryOptions")
public class DeliveryOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "DeliveryOptionID")
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    public DeliveryOption() {

    }

    public DeliveryOption(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
