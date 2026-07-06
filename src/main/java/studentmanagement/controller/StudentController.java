package studentmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentmanagement.dto.StudentRequest;
import studentmanagement.dto.StudentResponse;
import studentmanagement.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {
        StudentResponse responseData = studentService.createStudent(request);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responseData = studentService.getAllStudents();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        StudentResponse responseData = studentService.getStudentById(id);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequest request) {
        StudentResponse responseData = studentService.updateStudent(id, request);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
