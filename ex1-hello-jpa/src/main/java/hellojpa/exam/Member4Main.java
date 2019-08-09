package hellojpa.exam;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member4Main {
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
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member4 findMember4 = em.find(Member4.class, member.getId());

//            Long findTeamId = findMember4.getTeamId();
            Team findTeam = findMember4.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());
//            Team findTeam1 = em.find(Team.class, findTeamId);

            Team newTeam = em.find(Team.class, 100L);
            findMember4.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
