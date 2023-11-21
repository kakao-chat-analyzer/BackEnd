package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;

import java.util.List;

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


}
