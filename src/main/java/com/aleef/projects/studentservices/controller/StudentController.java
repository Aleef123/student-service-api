package com.aleef.projects.studentservices.controller;

import com.aleef.projects.studentservices.domain.Courses;
import com.aleef.projects.studentservices.domain.Student;
import com.aleef.projects.studentservices.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping(path="/retrieveStudentList",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudent(){
        log.info("Fetching details");
        List<Student> studentList;
        studentList = studentService.returnAllStudents();
        log.info("Data fetched successfully");
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping(path="/retrieveStudentData",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentData(@RequestParam(name = "studentId") final String studentId){
        log.info("Fetching details");
        Student studentDetails=studentService.retrieveStudentData(studentId);
        log.info("Data fetched successfully");
        return new ResponseEntity<>(studentDetails,HttpStatus.OK);
    }
    @GetMapping(path="/retrievestudentregisteredcourses",produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Courses>> getCourseStudentRegisteredFor(@RequestParam(name="studentId")final String studentId){
        log.info("Fetching required data");
        List <Courses> coursesList=new ArrayList<>();
        coursesList=studentService.retrieveCourseStudentRegisteredFor(studentId);
        log.info("data fetched successfully");
        return new ResponseEntity<>(coursesList,HttpStatus.OK);
    }
    @GetMapping(path="/GetCourseDetailsForStudent",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Courses> getCourseDetailsForStudent(@RequestParam(name = "studentId")final String studentId,
                                                              @RequestParam(name="courseId")final String courseId){
        log.info("Started fetching details");
        Courses courseDetails=studentService.retrieveCourseDetailsForStudent(studentId,courseId);
        log.info("Data fetched successfully");
        return  new ResponseEntity<>(courseDetails,HttpStatus.OK);
    }
    @PostMapping(path = "/addcourse")
    public ResponseEntity<String> addCourse(@RequestParam(name="studentId")final String studentId,
                                            @RequestBody Courses courses){
        log.info("Start adding course");
        studentService.addCourse(studentId,courses);
        log.info("course added successfully");
        return  new ResponseEntity<>("Added successfully",HttpStatus.OK);
    }
}
