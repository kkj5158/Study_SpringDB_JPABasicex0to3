package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction  tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

    // 비즈니스 로직
    private static void logic(EntityManager em) {

        /*
        // 멤버 등록

        String id = "id1";
        Member member = new Member();

        member.setId(id);
        member.setUserName("지한");
        member.setAge(2);

        em.persist(member);

        // 수정
        member.setAge(20);

        // 한건 조회

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember =" + findMember.getUserName() + ", age" + findMember.getAge());

        // 목록 조회

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("member.size =" + members.size());

        // 멤버 삭제

        //em.remove(member);


         */

        // 객체를 생성한 상태 (비영속 )

        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();

        member1.setId("member1");
        member1.setUserName("회원1");
        member1.setAge(22);

        member2.setId("member2");
        member2.setUserName("회원2");
        member2.setAge(22);


        member3.setId("member3");
        member3.setUserName("회원3");
        member3.setAge(22);




        // 객체를 저장한 상태 (영속)
        em.persist(member1);
        System.out.println("meber1 1차 캐시 저장 ");
        em.persist(member2);
        System.out.println("meber2 1차 캐시 저장 ");
        em.persist(member3);
        System.out.println("meber3 1차 캐시 저장 ");


        // 커밋은 바로바로 이루어 지지 않는다 . 우선적으로 1차캐시에 저장된다.


        /* 조회도 우선적으로 1차 캐시내에서 일어난다 -> 비즈니스 로직내에서 아직 db에 데이터가 들어가지 않은 상황임을

        이해하자 , 1차 캐시내에만 멤버들이 존재한다, 그러므로 우선적으로 1차 캐시내에서 데이터를 탐색하고 , 1차캐시안에

        멤버가 존재하지 않으면, 그때서야 데이터베이스내에서 멤버들을 서치한다.

         */



        Member findMember1 = new Member();
        Member findMember2 = new Member();
        Member findMember3 = new Member();

        findMember1 = em.find(Member.class, "member1");
        findMember2 = em.find(Member.class, "member2");
        findMember3 = em.find(Member.class, "member3");

        System.out.println("findMember 1 = " + findMember1.getUserName());
        System.out.println("findMember 2 = " + findMember2.getUserName());
        System.out.println("findMember 3 = " + findMember3.getUserName());

        Member a = em.find(Member.class, member1);
        Member b = em.find(Member.class, member1);

        // 동일성 비교
        System.out.println("동일성 비교 ");

        System.out.println(a==b);





        /* 실행하고 나서 로그를 살펴보자, insert 문 이전에 멤버를 탐색하여서 콘솔에 출력해주는 사실을 알수 있다, */













    }
}
