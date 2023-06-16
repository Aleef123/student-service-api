package com.aleef.projects.studentservices.service;

import com.aleef.projects.studentservices.domain.Courses;
import com.aleef.projects.studentservices.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    private static List<Student> studentList = new ArrayList<>();
    SecureRandom rand = new SecureRandom();
    static{
        Courses course1 = Courses.builder().id("Course1").description("Spring").name("10Steps").
                steps(List.of("Learn Maven", "Import Project", "First Example", "Second Example")).build();
        Courses course2 = Courses.builder().id("Course2").description("Spring MVC").name("10 Examples").
                steps(List.of("Learn Maven", "Import Project", "First Example", "Second Example")).build();
        Courses course3 = Courses.builder().id("Course3").description("Spring Boot").name("6K Students").
                steps(List.of("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example")).
                build();
        Courses course4 = Courses.builder().id("Course4").description("Maven").
                name("Most popular maven course on internet!").
                steps(List.of("Pom.xml", "Build Life Cycle", "Parent POM", "Importing into Eclipse")).build();
        Student ranga = Student.builder().id("Student1").name("Ranga Karanam").description("Hiker, Programmer and Architect").
                courses(new ArrayList<>(List.of(course1, course2, course3, course4))).build();
        Student satish = Student.builder().id("Student2").name("Satish T").description("Hiker, Programmer and Architect").
                courses(new ArrayList<>(List.of(course1, course2, course3, course4))).build();
        studentList.add(ranga);
        studentList.add(satish);
    }
    public List<Student> returnAllStudents(){
        return studentList;
    }
    public Student retrieveStudentData(String studentId) {
        log.info(studentId);
        for (Student student:studentList) {
            if(student.getId().equals(studentId)){
                return student;
            }
            else {
                log.info("student not found");
            }
        }
        return null;
    }
    public List<Courses> retrieveCourseStudentRegisteredFor(String studentId){
        for (Student student:studentList
             ) {if(studentId==student.getId()){
                 return student.getCourses();
        }
        else{
            log.info("Student not found");
        }}
        return null;
    }
    public Courses retrieveCourseDetailsForStudent(String studentId,String courseId) {
        for (Student student : studentList) {
            if (studentId == student.getId()) {
                for (Courses course : student.getCourses()) {
                    if (courseId == course.getId()) {
                        return course;
                    }else{
                        log.info("course not found");
                    }
                }
            }else{
                log.info("Student not fount");
            }
        }return null;
    }
    public void addCourse(String studentId,Courses course){
        for (Student student:studentList) {
            if(studentId==student.getId()){
                student.setCourses((List<Courses>) course);
            }else{
                log.info("unable to add course to the student");
            }
        }
    }
}
