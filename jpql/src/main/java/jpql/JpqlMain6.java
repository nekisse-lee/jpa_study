package jpql;


import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain6 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);


            em.flush();
            em.clear();

//            String query = "select concat('a','b')  FROM  Member m";
//            String query = "select substring(m.username, 2,3)  FROM  Member m";
//            String query = "select locate('de','abcdefg')  FROM  Member m";
//            String query = "select size(t.members)  FROM  Team t";
//            String query = "select index(t.members)  FROM  Team t";
//            String query = "select function('group_concat', m.username) FROM Member m";  // 사용자 정의 합수
            String query = "select group_concat(m.username)  FROM Member m";  // 사용자 정의 합수

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
