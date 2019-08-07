package hellojpa.mapping.identity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SuppressWarnings("ALL")
public class Member2Example {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            Member2 member = new Member2();
//            member.setId("ID_A");
            member.setUserName("C");

            System.out.println("========================");
            em.persist(member);
            System.out.println("member.getId() = " + member.getId());
            System.out.println("========================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
