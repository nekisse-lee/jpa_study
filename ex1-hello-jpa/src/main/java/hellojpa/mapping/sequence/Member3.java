package hellojpa.mapping.sequence;

import javax.persistence.*;

@Entity
@SequenceGenerator(
    name = "member3_seq_generator",
    sequenceName = "MEMBER3_SEQ",
    initialValue = 1, allocationSize = 50)
public class Member3 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member3_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String userName;

    public Member3() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
