package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 기본이 EAGER이므로 LAZY로 바꾸자.
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany // 중간 테이블을 만드는 과정
    @JoinTable(name = "CATEGORY_ITEM",
    joinColumns = @JoinColumn(name = "CATEGORY_ID"), // 내가 조인하는 것
    inverseJoinColumns = @JoinColumn(name = "ITEM_ID") // 반대쪽이 조인하는 것
    )
    private List<Item> items = new ArrayList<>();

}
