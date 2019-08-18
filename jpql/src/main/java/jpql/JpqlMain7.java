package jpql;


import jpql.domain.Member;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpqlMain7 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);


            em.flush();
            em.clear();

//            String query = "select m.username FROM Member m";  //상태필드 : 경로 탐색의 끝, 탐색 x

//            String query = "select m.team FROM Member m";  //단인 값 연관 경로 : 묵시적 내부 조인(inner join) 발생 탐색 O

//            String query = "select t.members FROM Team t";  //컬렉션 값 연관 경로 : 묵시적 내부 조인 발생, 탐색 x ex) t.members.username X
            String query = "select m.username FROM Team t join t.members m";  //해결방법 명시적 조인.

            //묵시적 조인을 쓰지 말기, 명시적조인으로 해결

            List<Collection> result = em.createQuery(query, Collection.class)
                    .getResultList();

            System.out.println("result = " + result);

//            for (Object o : result) {
//                System.out.println("o = " + o);
//            }

//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

//            for (Member s : result) {
//                System.out.println("s = " + s);
//            }

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
