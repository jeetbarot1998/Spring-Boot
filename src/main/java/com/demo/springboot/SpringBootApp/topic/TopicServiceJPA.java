package com.demo.springboot.SpringBootApp.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceJPA {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getTopics(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public void addTopic(Topic topic){
        topicRepository.save(topic);
    }

    public Topic getTopic(int id){
        Optional<Topic> byId = topicRepository.findById(id);
        return byId.orElse(null);
    }

    public void updateTopic(Topic topic, int id){
        topicRepository.save(topic);
    }

    public void deleteTopic(int id){
        topicRepository.deleteById(id);
    }

}
