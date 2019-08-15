package hellojpa.jpql;


import hellojpa.value_type.value_type_collection.Address11;
import hellojpa.value_type.value_type_collection.Address11Entity;
import hellojpa.value_type.value_type_collection.Member11;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            //Criteria  사용 준비  동적쿼리사용에 좋음,
            // 단점은 복잡하고 어려움. 실용성이 없다. 유지보수..
            //대신에 QueryDSL 사용 권장 !
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member11> query = cb.createQuery(Member11.class);

            Root<Member11> m = query.from(Member11.class);

            CriteriaQuery<Member11> cq = query.select(m);


            String username = "name";
            if (username != null) {
                cq.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member11> resultList = em.createQuery(cq)
                    .getResultList();

//            String sqlString = "select m from Member11 as m where m.username like '%kim%'";
//            List<Member11> resultList = em.createQuery(
//                    sqlString
//                    , Member11.class).getResultList();
//
//            for (Member11 member11 : resultList) {
//                System.out.println("member11 = " + member11);
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
