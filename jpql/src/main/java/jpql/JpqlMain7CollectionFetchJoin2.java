package jpql;


import jpql.domain.Member;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain7CollectionFetchJoin2 {
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



//            String query = "select t FROM Team t join fetch t.members as m where m.age > 10 ";
            //as m  패치 조인 대상에는 별칭을 줄 수 없음.
            //하이버네이트는 가능, 가급적 사용 X
            // 둘 이상의 컬렉션은 페치 조인 할 수 없다.

            //컬렉션을 페치 조인 하면 페이징 API(setFirstResult, setMaxResults)를 사용할 수 없다.
            //  - 1:1 , N:1같은 단일 값 연관 필드들은 페치 조인해도 페이징 가능
            //  - 하이버네이트는 경고 로그를 남기고 메모리에서 페이징(매위 위험)

//            String query = "select  t FROM Team t join fetch t.members m";//WARN: HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
//            String query = "select  m FROM Member m join fetch m.team t"; 해결방법 1 1:N을 N:1로 뒤집는다.
            String query = "select  t FROM Team t"; //해결방법 2  Team의 Members 에 @BatchSize 적용 또는 persistance.xml 에 그로벌 설정.

            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();

            System.out.println("result.size() = " + result.size());

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "|members=" +team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("->  member = " + member);
                }
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
