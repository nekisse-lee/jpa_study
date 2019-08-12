package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

//            Member findMember = em.find(Member.class, 1L);
            List<Member5> result = em.createQuery("select m from Member5 as m", Member5.class)
                .setFirstResult(5)
                .setMaxResults(8)
                .getResultList();

            for (Member5 member : result) {
//                System.out.println("member.getName() = " + member.getName());
            }

//            System.out.println("findMember.getId = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            findMember.setName("HelloJPA");

            //            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
