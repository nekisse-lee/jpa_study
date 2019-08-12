package hellojpa;

import hellojpa.exam.Member5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SuppressWarnings("ALL")
public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            //영속
            Member5 member = em.find(Member5.class, 150L);
//            member.setName("ZZZZZ");



//            em.persist(member);  commit 시점에 flush()실행과 동시에 업데이트됨
            //값을 바꾸면 트랜잭션이 commit되는 시점에 자동으로 update 쿼리를 날림.
            System.out.println("=========================");
            tx.commit();
            System.out.println("============AFTER========");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
