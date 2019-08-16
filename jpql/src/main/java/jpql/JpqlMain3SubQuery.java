package jpql;


import jpql.domain.Member;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain3SubQuery {
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

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();

            String qlString = "select (select avg(m1.age) from Member m1) as  avgAge from Member m left join Team t on m.username = t.name";
//            String qlString = "select mm.age, mm.username from (select m.age, m.username from Member m) as mm";  // from 절의 서브쿼리는 현제 jpql에서 불가능. 조인으로 풀어서 해결..



           List<Member> result = em.createQuery(qlString, Member.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());


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
