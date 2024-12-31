package org.vinn.openECommerce.api.pagePayment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    public PaymentMethod() {}

    public PaymentMethod(String name) {
        this.name = name;
    }
}