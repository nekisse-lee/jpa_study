package hellojpa.exam;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class ManyToOneExamMain2 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            //저장

            Team team = new Team();
            team.setName("TeamA");
//            team.getMember4s().add(member);
            em.persist(team);

            Member4 member = new Member4();
            member.setUsername("member1");
//            member.changeTeam(team);  //**3  연관관계 편의메서드 따라 한쪽에서 선택
            em.persist(member);   // *1

            team.addMember(member);  //**3  연관관계 편의메서드 상황에 따라 한쪽에선택

//            //역방향 (주인이 아닌 방향) 만 연관관계 설정.
            //순수 객체 생태를 고려해서 항상 양쪽 값에 세팅을하자.
            // *1, *2 -> Member4에서 set메서드 세팅
//            team.getMember4s().add(member);   //*2


            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member4> member4s = findTeam.getMember4s();

            for (Member4 m : member4s) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            System.out.println("====================");
            System.out.println("members = " + findTeam);
            System.out.println("====================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
