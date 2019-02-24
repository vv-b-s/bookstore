package bg.unwe.bookstore.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Carts")
public class Cart implements EntityModel<Integer> {

    @Id
    @Column(name = "CartID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserId", nullable = false)
    private String userId;

    @Column(name = "CreatedOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "LastModification", nullable = false)
    private LocalDateTime lastModification;

    @Column(name = "IsPaid", nullable = false)
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "DeliveryOptionID")
    private DeliveryOption deliveryOption;

    public Cart() {

    }

    public Cart(String userId, LocalDateTime createdOn, LocalDateTime lastModification, boolean isPaid, PaymentMethod paymentMethod, DeliveryOption deliveryOption) {
        this.userId = userId;
        this.createdOn = createdOn;
        this.lastModification = lastModification;
        this.isPaid = isPaid;
        this.paymentMethod = paymentMethod;
        this.deliveryOption = deliveryOption;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }
}
