package hellojpa.exam.lazy_loading;

import hellojpa.exam.BaseEntity;
import hellojpa.exam.Team;

import javax.persistence.*;

@Entity
public class Member7 extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)  //프록시로 가져옴. 실제 team을 사용하는 시점에 초기화
//    @ManyToOne(fetch = FetchType.EAGER)  //즉시로딩 모든것을 한번에 가져옴. ex member와 team을 같이,  JPQL N+1개의 문제점..
    @JoinColumn
    private Team team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }




}
