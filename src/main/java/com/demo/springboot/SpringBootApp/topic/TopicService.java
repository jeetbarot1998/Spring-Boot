package com.demo.springboot.SpringBootApp.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
    List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic(1, "namee", "desc"),
            new Topic(2, "namee1", "desc1"),
            new Topic(3, "namee3", "desc3")
    ));

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Topic getTopic(int id){
        return topics.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public void updateTopic(Topic topic, int id){
        for(int i = 0 ; i< topics.size(); i++){
            Topic t = topics.get(i);
            if(t.getId() == id){
                topics.set(i, topic);
                return;
            }
        }
    }

    public void deleteTopic(int id){
        topics.removeIf(t -> t.getId() == id);
    }
}
