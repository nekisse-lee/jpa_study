package jpql;


import jpql.domain.Member;
import jpql.domain.Team;

import javax.persistence.*;
import java.util.List;

public class JpqlMain8 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();


//            String query = "select m From Member m where m = :member";
            String query = "select m From Member m where m.id = :memberId";

            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
                    .setParameter("memberId", member1.getId())
                    .getSingleResult();

            System.out.println("findMember = " + findMember);

            String query2 = "select m From Member m where m.team = :team";
            List<Member> members = em.createQuery(query2, Member.class)
                    .setParameter("team", teamA)
//                    .getSingleResult();
                    .getResultList();

            System.out.println("members = " + members  + "\n ------------------");

            for (Member member : members) {
                System.out.println("member = " + member);
            }

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
