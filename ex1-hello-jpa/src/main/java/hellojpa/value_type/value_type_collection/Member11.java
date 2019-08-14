package hellojpa.value_type.value_type_collection;

import hellojpa.value_type.embedded.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member11 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address11 homeAddress;


    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD" , joinColumns =
    @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME ")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "ADDRESS11" , joinColumns =
//    @JoinColumn(name = "MEMBER_ID"))
//    private List<Address11> addressHistory = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<Address11Entity> addressHistory = new ArrayList<>();


    public Address11 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address11 homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

//    public List<Address11> getAddressHistory() {
//        return addressHistory;
//    }
//
//    public void setAddressHistory(List<Address11> addressHistory) {
//        this.addressHistory = addressHistory;
//    }


    public List<Address11Entity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address11Entity> addressHistory) {
        this.addressHistory = addressHistory;
    }

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


}
