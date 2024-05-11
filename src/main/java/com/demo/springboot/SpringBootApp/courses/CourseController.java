package com.demo.springboot.SpringBootApp.courses;

import com.demo.springboot.SpringBootApp.topic.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
//@RequestMapping("/JPA")
public class CourseController {

    @Autowired
    private ObjectMapper objectMapper; // Autowire ObjectMapper if it's a bean

    @Autowired
    private CourseService courseService;

    @RequestMapping("topic/{id}/course")
    public List<Course> getAllCourses(@PathVariable Integer id){
        return courseService.getTopics(id);
    }

    @RequestMapping("/topic/{topicId}/course/{courseId}")
    public Course getCourse(@PathVariable int id){
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/topic/{topicId}/course")
    public void addCourse(@RequestBody Course course, @PathVariable Integer topicId){
        System.out.println("================= TOPIC ===================" + course);
        course.setTopic(new Topic(topicId, "ABC", "DEF"));
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topicFormData")
    @ResponseBody
    public void MultipartFileUpload(@RequestParam("file") MultipartFile file,
                                    Course course) {
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

                // Load JSON content into an object (e.g., Course class)
//                Course course = objectMapper.readValue(jsonFileContent, Course.class);

                // Access other form fields if needed (e.g., otherField)
                System.out.println("Course: " + course);
            } catch (IOException e) {
                // Handle IOException if file processing fails
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topic/{topicId}/course/{courseId}")
    public void UpdateCourse(@RequestBody Course course, @PathVariable int topicId, @PathVariable int courseId){
        course.setTopic(new Topic(topicId, "ABC", "DEF"));
        courseService.updateCourse(course);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/topic/{topicId}/course/{courseId}")
    public void DeleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
    }

}
