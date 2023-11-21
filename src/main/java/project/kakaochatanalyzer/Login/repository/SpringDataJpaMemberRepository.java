package project.kakaochatanalyzer.Login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByuserId(String Id);
    @Override
    Optional<Member> findByuserPw(String Pw);
    @Override
    Optional<Member> findByuserName(String name);
}