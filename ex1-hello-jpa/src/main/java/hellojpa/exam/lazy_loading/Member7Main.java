package hellojpa.exam.lazy_loading;


import hellojpa.exam.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Member7Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member7 member1 = new Member7();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member7 member2 = new Member7();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            //            Member7 m = em.find(Member7.class, member1.getId());

            System.out.println("==================");
//            List<Member7> member7s = em.createQuery("select m from Member7 m", Member7.class)
//                    .getResultList();  //  JPQL 사용시  N+1  문제를 일으킨다.

            List<Member7> member7s = em.createQuery("select m from Member7 m join  fetch  m.team", Member7.class)
                    .getResultList();  //  JPQL 사용시  N+1  문제를 일으킨다.
            // 해결법 LAZY +  패치조인

           System.out.println("==================");


//            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
//
//            System.out.println("======================");
//            System.out.println(m.getTeam().getName()); //Lazy 로딩 초기화 시점 team을 직접 사용할때 .
//            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member7 m1, Member7 m2) {
        System.out.println("(m1.getClass()==m2.getClass()) = " + (m1 instanceof Member7));
        System.out.println("(m1.getClass()==m2.getClass()) = " + (m2 instanceof Member7));
    }


}
