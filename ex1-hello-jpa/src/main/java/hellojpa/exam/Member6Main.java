package hellojpa.exam;



import org.hibernate.Hibernate;

import javax.persistence.*;

public class Member6Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            Member6 member1 = new Member6();
            member1.setUsername("member1");
            em.persist(member1);

//            Member6 member2 = new Member6();
//            member2.setUsername("member2");
//            em.persist(member2);

            em.flush();
            em.clear();

//            Member6 m1 = em.find(Member6.class, member1.getId());
            Member6 refMember = em.getReference(Member6.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass()); //Proxy

//            refMember.getUsername();

            //프록시 확인.
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            //프록시 강제초기화
            Hibernate.initialize(refMember);

//            em.detach(refMember);
//            em.close();
//            em.close();

//            System.out.println("refMember.getUsername() = " + refMember.getUsername());


//            Member6 reference = em.getReference(Member6.class, member1.getId());
//            Member6 findMember = em.find(Member6.class, member1.getId());
//            System.out.println("findMember.getClass() = " + findMember.getClass());
//
//            System.out.println("a == a : " + (refMember == findMember));


//            Member6 m2 = em.find(Member6.class, member2.getId());
//            Member6 m2 = em.getReference(Member6.class, member2.getId());

//            logic(m1, m2);

            //

//            Member6 findMember = em.find(Member6.class, member1.getId());
//            Member6 findMember = em.getReference(Member6.class, member1.getId()); //프록시객체
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("before findMember.getClass() = " + findMember.getClass());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());
//            System.out.println("after findMember.getClass() = " + findMember.getClass());

//            System.out.println("findMember.getUsername() = " + findMember.getUsername());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member6 m1, Member6 m2) {
        System.out.println("(m1.getClass()==m2.getClass()) = " + (m1 instanceof Member6));
        System.out.println("(m1.getClass()==m2.getClass()) = " + (m2 instanceof Member6));
    }


}
