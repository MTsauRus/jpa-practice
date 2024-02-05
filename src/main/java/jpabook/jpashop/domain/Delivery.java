package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity // order과 1대1
public class Delivery extends BaseEntity{
    @Id @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}