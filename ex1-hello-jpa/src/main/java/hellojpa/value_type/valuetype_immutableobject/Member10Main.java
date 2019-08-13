package hellojpa.value_type.valuetype_immutableobject;


import hellojpa.value_type.embedded.Address;
import hellojpa.value_type.embedded.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member10Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Address address = new Address("city", "street", "10000");

            Member10 member = new Member10();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //변경시 완전 새로운 객체로 갈아끼운다.
            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);

            //값을 복사해서 사용   -> 불변객체로 만들어서 사용. 생성자로만 설정, setter금지 혹은 private로 만듬.
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member10 member2 = new Member10();
//            member2.setUsername("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);

            //
//            member.getHomeAddress().setCity("newCity");

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
