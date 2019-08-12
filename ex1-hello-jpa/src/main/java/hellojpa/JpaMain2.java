package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            //영속
//            System.out.println(" === BEFORE ===");
//            em.persist(member);
//            System.out.println(" === AFTER ===");
            Member5 findMember1 = em.find(Member5.class, 101L);
            Member5 findMember2 = em.find(Member5.class, 101L);
            System.out.println("findMember1==findMember2 = " + (findMember1==findMember2));
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            // 트랜잭션에 커밋시 쿼리 실행.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
