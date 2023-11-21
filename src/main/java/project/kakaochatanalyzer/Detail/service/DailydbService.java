package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.dailydb;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;

import java.util.List;

@Transactional
public class DailydbService {
    private final DailydbRepository userRepository;
    @Autowired
    public DailydbService(DailydbRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<dailydb> getAllUsers() {
        return userRepository.findAll();
    }
    public List<dailydb> getAllEntities() {
        return userRepository.findAll();
    }
}