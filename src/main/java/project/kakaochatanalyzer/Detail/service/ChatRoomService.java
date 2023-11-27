package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.List;
import java.util.Optional;

@Transactional
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public List<ChatRoom> getAllusers(){
        return chatRoomRepository.findAll();
    }

    //나중에 처리
    public Long findMaxRoomNumber(Long roomNum){
        Optional<ChatRoom> chatRoom=  chatRoomRepository.findByroomNumber(roomNum);
        return chatRoom.map(ChatRoom::getRoomNumber)
                .orElseThrow(() -> new IllegalStateException("roomNum에 해당하는 데이터가 없습니다."));
    }

    ////형진씨 Task1
    public ChatRoom saveChatRoom(Member member) {

        // Member Id를 ChatRoom db에서 조회해서
        // 없으면 roomNumber를 1로 저장하고 있으면 그 값 + 1로 저장
        List<ChatRoom> existChatRoom = chatRoomRepository.findByMemberId(member.getId());
        ChatRoom newChatRoom = new ChatRoom(member);

        if(existChatRoom.isEmpty()){
            newChatRoom.setRoomNumber(1L);
        }
        else{
            Long maxRoomNum = chatRoomRepository.findMaxRoomNumberByMemberId(member.getId()).orElse(0L);
            newChatRoom.setRoomNumber(maxRoomNum+1);
        }
        chatRoomRepository.save(newChatRoom);
        return newChatRoom;
        //return 0; return을 어떤 걸 줄지는 선택해야한다. 저장한 채팅방Number
    }
}