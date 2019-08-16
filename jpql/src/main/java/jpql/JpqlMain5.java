package jpql;


import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain5 {
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
            member.setUsername("관리자");
//            member.setUsername(null);
            member.setAge(10);

            member.setTeam(team);
            member.setType(MemberType.ADMIN);

            em.persist(member);


            em.flush();
            em.clear();

//            String query = "select " +
//                                "case when m.age <= 10 then '학생요금' " +
//                                "     when m.age >= 60 then '경로요금' " +
//                                "     else '일반요금'" +
//                                "end " +
//                    "from Member m";

//            String query = "select coalesce(m.username, '이름 없는 회원') as username " +
//                    "from Member m";   //coalesce  하나씩 조해서 null이 아니면  반환
            String query = "select nullif(m.username, '관리자') as username " +
                    "from Member m"; //두 값이 같으면 null, 다르면 첫번째 값 반환
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
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
