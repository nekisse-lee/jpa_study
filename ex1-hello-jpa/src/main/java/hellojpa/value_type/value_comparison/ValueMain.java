package hellojpa.value_type.value_comparison;

import hellojpa.value_type.embedded.Address;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("(a==b) = " + (a == b));

        ValueAddress address1 = new ValueAddress("city", "street", "10000");
        ValueAddress address2 = new ValueAddress("city", "street", "10000");

        System.out.println("(address1 == address2) = " + (address1 == address2));
        System.out.println("(address1 equals address2) = " + (address1.equals(address2)));

    }

}
