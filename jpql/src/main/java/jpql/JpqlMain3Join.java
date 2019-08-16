package jpql;


import jpql.domain.Member;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain3Join {
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
//            member.setUsername("member1");
            member.setUsername("teamA");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();

//            String qlString = "select  m from Member m inner join m.team t"; // 이너 생략 가능
//            String qlString = "select  m from Member m left outer join m.team t";  //outer 생략 가능
//            String qlString = "select m from Member m, Team t where m.username = t.name";   //세타조인
//            String qlString = "select m from Member m left join m.team t on t.name='teamA'";       //on
            String qlString = "select m from Member m left join Team t on m.username = t.name";       // 연관관계가 없는 Entity 외부조인 .
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
