package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SuppressWarnings("ALL")
public class FlushExamMain1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            //영속
            Member member = new Member(200L, "membr200");

            em.persist(member);

            em.flush();  // commit 시점이 아닌 즉시 실행

            System.out.println("============ commit 전=============");
            tx.commit();
            System.out.println("============ commit 후 ============");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
