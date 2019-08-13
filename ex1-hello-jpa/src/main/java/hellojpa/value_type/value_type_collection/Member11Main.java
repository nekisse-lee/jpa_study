package hellojpa.value_type.value_type_collection;


import hellojpa.value_type.embedded.Address;
import hellojpa.value_type.valuetype_immutableobject.Member10;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class Member11Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {


            Member11 member = new Member11();
            member.setUsername("member1");
            member.setHomeAddress(new Address11("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");


            member.getAddressHistory().add(new Address11("old1", "street", "10000"));
            member.getAddressHistory().add(new Address11("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========================START===========================");
            Member11 findMember = em.find(Member11.class, member.getId());

            //homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity"); xxxxxxxxxxxxxxxxxxxxxxx

            Address11 old = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address11("newCity", old.getStreet(), old.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //old1 -> new1
            // equals 사용으로 remove
            System.out.println("==========AddressHistory==========");
            findMember.getAddressHistory().remove(new Address11("old1", "street", "10000"));
            findMember.getAddressHistory().add(new Address11("newCity1", "street", "10000"));
            System.out.println("==========AddressHistory==========");

//            List<Address11> addressHistory = findMember.getAddressHistory();
//            for (Address11 address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

            tx.commit();
        } catch (Exception e) {

            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
