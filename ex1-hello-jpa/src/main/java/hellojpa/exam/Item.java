package hellojpa.exam;

import javax.persistence.*;

@Entity
//@Inheritance (strategy = InheritanceType.JOINED) //조인전략
//@Inheritance (strategy = InheritanceType.SINGLE_TABLE) //단일테이블전략 DTYPE기본 생
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS) //단일테이블전략 DTYPE기본 생
//@DiscriminatorColumn()
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private  String name;

    private int price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
