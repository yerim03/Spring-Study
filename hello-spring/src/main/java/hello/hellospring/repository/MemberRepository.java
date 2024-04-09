package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//멤버리포지토리를 인터페이스로 구현
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);    //Id로 회원 조회
    Optional<Member> findByName(String name);    //name으로 회원 조회
    List<Member> findAll();  //저장된 모든 회원 리스트 조회
}
