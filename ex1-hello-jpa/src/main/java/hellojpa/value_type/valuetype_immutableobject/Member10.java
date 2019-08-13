package hellojpa.value_type.valuetype_immutableobject;

import hellojpa.value_type.embedded.Address;
import hellojpa.value_type.embedded.Period;

import javax.persistence.*;

@Entity
public class Member10 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;


    // 기간 Period
    @Embedded
    private Period workPeriod;

    //주소
    @Embedded
    private Address homeAddress;


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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
