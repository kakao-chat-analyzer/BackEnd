package project.kakaochatanalyzer.Login.service;

import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;

import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Long join(Member member){
        //회원 가입
        validateDuplicateMember(member); // ctrl + T ->메서드 추출
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByuserPw(member.getUserPw())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다. 비밀번호를 재 등록 해주세요.");
                });
    }

    public boolean authenticate(String userId, String userPw){
        // 로그인 시 해당 id와 비밀번호가 DB에 있는지 확인
        Optional<Member> optionalMember = memberRepository.findByuserId(userId);
        return optionalMember
                .map(member -> memberRepository.findByuserPw(userPw).isPresent()
                ).orElse(false);
    }
    public String getUserNameById(String userId){
        // userId를 이용해 해당 유저 이름 찾기
        return memberRepository.findByuserId(userId)
                .map(Member::getUserName)
                .orElseThrow(() -> new IllegalStateException("ID에 해당하는 사용자가 없습니다."));
    }

//    public List<Member> findMembers(){
//        return memberRepository.findAll();
//    }

//    public Optional<Member> findOne(Long memberId){
//        return memberRepository.findById(memberId);
//    }
}

