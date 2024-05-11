package com.demo.springboot.SpringBootApp.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getTopics(Integer topicId){
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public Course getCourse(int id){
        Optional<Course> byId = courseRepository.findById(id);
        return byId.orElse(null);
    }

    public void updateCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(int id){
        courseRepository.deleteById(id);
    }

}
