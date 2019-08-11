package hellojpa.exam;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Member5Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            Member5 member5 = new Member5();
            member5.setUsername("user1");
            member5.setCreatedBy("kim");
            member5.setCreatedDate(LocalDateTime.now());

            em.persist(member5);

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
