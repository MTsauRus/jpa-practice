package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // DB에 ORDER가 예약어로 잡혀있는 경우가 있음. 이를 피하기 위해 ORDERS로 함
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne // 연관관계의 주인. Order의 orders에 mappedBy"member" 해야 한다.
    @JoinColumn(name = "MEMBER_ID") // 연관관계를 맺고 있는 엔티티의 PK를 해당 테이블의 외래키 "MEMBER_ID"로 저장.
    private Member member;

    @OneToMany(mappedBy = "order") // 연관관계의 주인은 orderItem의 order. 외래 키를 orderItem이 가지고 있음.
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) { // 연관관계 편의 메소드. 무한 루프 등의 문제 방지를 위해 한쪽에만 만들자.
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // enum 타입은 enumerated. 타입은 무조건 STRING
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
