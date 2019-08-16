package jpql;


import jpql.domain.Member;

import javax.persistence.*;

public class JpqlMain {
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

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<Member> query = em.createQuery("select m from Member m where  m.username = :username", Member.class);
//            query.setParameter("username", "member1");
//            Member singleResult = query.getSingleResult();

            //보통 체이닝으로 사용.
            Member singleResult = em.createQuery("select m from Member m where  m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());


//            Member result = query.getSingleResult();   //  -> 결과ㅏㄱ 정확히하나, 결과가 없으면 NoResult,, 둘 이상이면 nonUniqueResultException..

//            System.out.println("result = " + result);

//            List<Member> resultList = query.getResultList();
//
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m",String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");


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
