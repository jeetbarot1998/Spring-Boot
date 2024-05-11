package com.demo.springboot.SpringBootApp.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/JPA")
public class TopicControllerJPA {

    @Autowired
    private ObjectMapper objectMapper; // Autowire ObjectMapper if it's a bean

    @Autowired
    private TopicServiceJPA topicService;

    @RequestMapping("/topic")
    public List<Topic> getAllTopics(){
        return topicService.getTopics();
    }

    @RequestMapping("/topic/{id}")
    public Topic getTopic(@PathVariable int id){
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/topic")
    public void addTopic(@RequestBody Topic topic){
        System.out.println("================= TOPIC ===================" + topic);
        topicService.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topicFormData")
    @ResponseBody
    public void MultipartFileUpload(@RequestParam("file") MultipartFile file,
                                     Topic topic) {
        if (!file.isEmpty()) {
            try {
                // Process the file content (e.g., save it to a storage location)
                byte[] fileContent = file.getBytes();
                String currentDirectory = System.getProperty("user.dir");

                // Adjust file path for saving
                String filePath = Paths.get(currentDirectory, file.getOriginalFilename()).toString();
                Files.write(Paths.get(filePath), fileContent);

                // Convert file content to JSON or load into an object
//                String jsonFileContent = otherField; // Assuming file content is in JSON format

                // Load JSON content into an object (e.g., Topic class)
//                Topic topic = objectMapper.readValue(jsonFileContent, Topic.class);

                // Access other form fields if needed (e.g., otherField)
                System.out.println("Topic: " + topic);
            } catch (IOException e) {
                // Handle IOException if file processing fails
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topic/{id}")
    public void UpdateTopic(@RequestBody Topic topic, @PathVariable int id){
        topicService.updateTopic(topic, id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/topic/{id}")
    public void DeleteTopic(@PathVariable int id){
        topicService.deleteTopic(id);
    }

}
