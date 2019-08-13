package hellojpa.value_type.value_type_collection;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address11 {
    private String city;
    private String street;
    private String zipcode;


    public Address11() {
    }

    public Address11(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address11 that = (Address11) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(zipcode, that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
