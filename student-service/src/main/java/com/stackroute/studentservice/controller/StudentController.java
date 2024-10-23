package com.stackroute.studentservice.controller;

import com.stackroute.studentservice.entity.StudentDetails;
import com.stackroute.studentservice.service.StudentService;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/Student-Service")
@Api(value = "/Student Details", tags = "Student Service")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    @ApiOperation(value = "Create Default Student Details", notes = "Create Student Details",tags = {"Student-Service"})
    public String welcome() {

        return "Welcome to Takeintutee Application";
    }


    @PostMapping("/createStudent")
    public StudentDetails addStudent(@RequestBody String studentDetails) {
        return  studentService.addStudentDetails(studentDetails);
    }

    @PutMapping("/updateStudent?{id}")
    public Optional<StudentDetails> updateStudent(@PathVariable @NotNull int id,
                                                  @RequestBody StudentDetails studentDetails) {
        return studentService.updateStudentDetails(id,studentDetails);
    }

    @GetMapping("/findAllStudents")
    public List<StudentDetails> getStudents(){
        return studentService.readStudentDetails();
    }

    @GetMapping("/findById/{id}")
    public Optional<StudentDetails> searchStudentbyId(@PathVariable int id){

        return studentService.findStudentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,String> deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);

    }

}

