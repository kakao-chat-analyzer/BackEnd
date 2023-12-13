package project.kakaochatanalyzer.Login.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Login.MemberForm;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.service.MemberService;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final MemberService memberService;
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인을 눌렀을 시
    @PostMapping("login")
    public ResponseEntity<Member> login(MemberForm form, HttpSession session){
        boolean isAuthenticated = memberService.authenticate(form.getUserId(),form.getUserPw());
        //로그인 유효성 검사
        if (isAuthenticated){
            Member member = new Member();
            String userId = form.getUserId();

            // userId에 따른 유저 이름 가져오기
            String userName = memberService.getUserNameById(userId);
            Long id = memberService.getUserNameByuserId(userId);
            member.setId(id);
            member.setUserId(userId);
            member.setUserName(userName);

            //session 설정
            session.setAttribute("loggedInUser",member);

            return ResponseEntity.ok(member); // 200
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //401
        }
    }

    // 회원 가입을 눌렀을 시
    @PostMapping("register")
    public ResponseEntity<Member> create(MemberForm form){
        // 새 member 추가
        Member member = new  Member();
        member.setUserId(form.getUserId());
        member.setUserPw(form.getUserPw());
        member.setUserName(form.getUserName());
        member.setUserEmail(form.getUserEmail());
        try{
            // Member DB 등록
            memberService.join(member); // pw 중복 확인
            return ResponseEntity.ok(member); // 200
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
        }
    }

    // 유저 정보 (로그인 유무)
    @GetMapping("user")
    public ResponseEntity<Member> user(HttpSession session){
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        // userName 별로 db조회
        if (loggedInUser != null) {
            // 로그인이 된 경우
            return ResponseEntity.ok(loggedInUser); // 200
        } else {
            // 로그인되지 않은 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }
    }
}