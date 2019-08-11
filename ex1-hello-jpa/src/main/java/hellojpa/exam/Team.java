package hellojpa.exam;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team  extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member4> member4s = new ArrayList<>();

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    public void addMember(Member4 member) {
        member.setTeam(this);
        member4s.add(member);
    }

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

    public List<Member4> getMember4s() {
        return member4s;
    }

    public void setMember4s(List<Member4> member4s) {
        this.member4s = member4s;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", member4s=" + member4s +
                '}';
    }
}
