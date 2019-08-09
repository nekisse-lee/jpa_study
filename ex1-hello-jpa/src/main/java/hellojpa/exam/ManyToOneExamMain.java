package hellojpa.exam;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ManyToOneExamMain {
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
            em.persist(team);

            Member4 member = new Member4();
            member.setUsername("member1");
//            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member4 findMember4 = em.find(Member4.class, member.getId());

            List<Member4> member4s = findMember4.getTeam().getMember4s();
            for (Member4 member4 : member4s) {
                System.out.println("member4.getUsername() = " + member4.getUsername());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
