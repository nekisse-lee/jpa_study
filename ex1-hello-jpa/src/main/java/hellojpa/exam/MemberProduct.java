package hellojpa.exam;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {

    @Id
    @GeneratedValue
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER4_ID")
    private Member4 member4;

    @ManyToOne
    @JoinColumn(name = "PRDUCT_ID")
    private Product product;

    private int count;
    private int price;
    private LocalDateTime orderDateTime;
}
