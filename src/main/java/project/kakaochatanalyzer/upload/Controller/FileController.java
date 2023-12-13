package project.kakaochatanalyzer.upload.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;
import project.kakaochatanalyzer.Login.service.MemberService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FileController {
    private RestTemplate restTemplate;

    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private DailydbService dailydbService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @PostMapping("/file")
    public ResponseEntity<List<Dailydb>> sendTextToFastAPI(@RequestParam("file") MultipartFile file, HttpSession session) {
        //// DUMMY ////
        Member members = new Member();
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        session.setAttribute("loggedInUser", members);
        //// DUMMY ////

        String URL = "http://localhost:8000/api/file";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 파일 fastapi로 post로 보내기
        ResponseEntity<List<Dailydb>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Dailydb>>() {
                }
        );

        // fastapi의 response 받기
        List<Dailydb> processedData = responseEntity.getBody();

        //session 정보
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        Optional<Member> userData = memberRepository.findByuserId(userId);
        Member member = userData.orElse(null);
        //DB 저장
        ChatRoom newChatRoom = chatRoomService.saveChatRoom(member);
        dailydbService.saveProcessedData(processedData, member, newChatRoom);

        return ResponseEntity.ok(processedData);
    }

    @PostMapping("/keyword")
    public int sendTextToFastAPI(HttpSession session,
                                 @RequestParam("date") LocalDate date,
                                 @RequestParam("chatroomNum") Long chatRoomId) {
        //// DUMMY ////
        Member members = new Member();
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        session.setAttribute("loggedInUser", members);
        //// DUMMY ////
        //키워드 추출 할 때 누름..
        // localhost:6797/api/user?date=2023-03-14?chatroomNum=30

        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        Optional<Member> realUser = memberRepository.findByuserId(userId);
        Long realId = realUser.get().getId();

        Optional<Dailydb> dailydbOptional = dailydbService.findByDateAndChatRoomIdAndMemberId(date, chatRoomId, realId);
        if (dailydbOptional.isEmpty()) {
            return 0;
        }
        Dailydb dailydb = dailydbOptional.get();
        String totalMessage = dailydb.getTotalMessage();

        String fastApiUrl = "http://localhost:8000/api/keyword";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        Keyword keyword = new Keyword();
        keyword.setTotalMessage(totalMessage);
        String asd = "null";
        List<String> s = List.of(asd);
        keyword.setKeywords(s);
        HttpEntity<Keyword> requestEntity = new HttpEntity<>(keyword, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Keyword> responseEntity = restTemplate.exchange(
                fastApiUrl,
                HttpMethod.POST,
                requestEntity,
                Keyword.class
        );
        Keyword processedData = responseEntity.getBody();
        dailydbService.updateDatabaseWithKeywords(dailydb, processedData.getKeywords());
        return 200;
    }

}
