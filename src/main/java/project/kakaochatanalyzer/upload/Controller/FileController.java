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

import java.io.IOException;
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
    public int sendTextToFastAPI(@RequestParam("file") MultipartFile file,HttpSession session){
        //// DUMMY ////
        Member members = new Member();
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        memberService.join(members);
        session.setAttribute("loggedInUser",members);
        //// DUMMY ////


        //ResponseEntity<Dailydb>

        String URL = "http://localhost:8000/api/file";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        //파일 fastapi로 post로 보내기
        ResponseEntity<List<Dailydb>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                requestEntity,
//                Dailydb.class  //Dailydb class로 변환
                new ParameterizedTypeReference<List<Dailydb>>() {}
        );

        //fastapi의 response 받기
        List<Dailydb> processedData = responseEntity.getBody();

        //data 확인
        System.out.println(processedData.get(0).getFrequently()); //
        System.out.println(processedData.get(0).getKeyword());
        System.out.println(processedData.get(0).getChatTimes());
        System.out.println(processedData.get(0).getTotalMessage());


        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        System.out.println("시작9");
        Optional<Member> userData = memberRepository.findByuserId(userId);
        System.out.println("시작4");
        Member member = userData.orElse(null);
        //DB 저장
        System.out.println("시작1");
        ChatRoom newChatRoom = chatRoomService.saveChatRoom(member);
        System.out.println("시작2"+newChatRoom.getRoomNumber());
        dailydbService.saveProcessedData(processedData, member, newChatRoom);


//        return ResponseEntity.ok(processedData);
        return 200;
    }
}