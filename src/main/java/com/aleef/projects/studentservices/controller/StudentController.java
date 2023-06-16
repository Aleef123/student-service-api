package com.aleef.projects.studentservices.controller;

import com.aleef.projects.studentservices.domain.Student;
import com.aleef.projects.studentservices.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Student> getStudentData(@RequestParam(name = "studentId",required = false) final String studentId){
        log.info("Fetching details");
        Student studentDetails=studentService.retrieveStudentData(studentId);
        log.info("Data fetched successfully");
        return new ResponseEntity<>(studentDetails,HttpStatus.OK);
    }
}
