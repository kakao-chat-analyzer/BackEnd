package project.kakaochatanalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.repository.MemberRepository;
import project.kakaochatanalyzer.Login.service.MemberService;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private DailydbRepository dailydbRepository;
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, DailydbRepository dailydbRepository, ChatRoomRepository chatRoomRepository){
        this.memberRepository = memberRepository;
        this.dailydbRepository=dailydbRepository;
        this.chatRoomRepository=chatRoomRepository;
    }
    @Bean
    public MemberService memberService() {return new MemberService(memberRepository);}
    @Bean
    public ChatRoomService chatRoomService(){return new ChatRoomService(chatRoomRepository);}
    @Bean
    public DailydbService dailydbService(){return new DailydbService(dailydbRepository);}


}
