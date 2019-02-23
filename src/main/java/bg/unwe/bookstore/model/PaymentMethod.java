package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "PaymentMethods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "PaymentMethodID")
    private int id;

    @Column(nullable = false, name = "Name")
    private String name;

    public PaymentMethod() {

    }

    public PaymentMethod(String name) {
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
