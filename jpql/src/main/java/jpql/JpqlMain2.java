package jpql;


import jpql.domain.Member;
import jpql.domain.MemberDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain2 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();  //엔티티 프로젝션
//            List<Team> teamResult = em.createQuery("select m.team from Member m", Team.class)
//                    .getResultList();
//            List<Team> teamResult = em.createQuery("select t from Member m join m.team t", Team.class)
//                    .getResultList();   //엔티티 프로젝션


//             em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();  //임베디드 타입 프로젝션

//            em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();  //스칼라 타입 프로젝션

            /*  여러값 조회 */
            //1
//            List resultList = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age =  " + result[1]);

            //2
//            List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member m")
//                    .getResultList();
//            Object[] result = resultList.get(0);
//            System.out.println("username = " + result[0]);
//            System.out.println("age =  " + result[1]);

//            3
            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

//            Member findMember = result.get(0);
//            findMember.setAge(20);

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
