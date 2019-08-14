package hellojpa.value_type.value_type_collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS11")
public class Address11Entity {

    @Id
    @GeneratedValue
    private Long id;

    private Address11 address11;

    public Address11Entity(String city, String street, String zipcode) {
        this.address11 = new Address11(city, street, zipcode);
    }

    public Address11Entity(Address11 address11) {
        this.address11 = address11;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address11 getAddress11() {
        return address11;
    }

    public void setAddress11(Address11 address11) {
        this.address11 = address11;
    }
}
