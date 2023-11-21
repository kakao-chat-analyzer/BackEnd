package project.kakaochatanalyzer.Login.repository;

import project.kakaochatanalyzer.Login.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByuserId(String Id);
    Optional<Member> findByuserPw(String Pw);
    Optional<Member> findByuserName(String name);
    List<Member> findAll();
}
