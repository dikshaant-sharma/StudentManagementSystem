package studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentmanagement.dto.StudentRequest;
import studentmanagement.dto.StudentResponse;
import studentmanagement.entity.Student;
import studentmanagement.exception.ResourceNotFoundException;
import studentmanagement.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        Student student = new Student(
            request.firstName(),
            request.lastName(),
            request.email(),
            request.course(),
            request.gpa(),
            LocalDate.now()
        );

        Student savedStudent = studentRepository.save(student);
        return mapToResponse(savedStudent);
    }


    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }


    public StudentResponse getStudentById(String id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToResponse(student);
    }


    public StudentResponse updateStudent(String id, StudentRequest request) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // If email is being changed, check if new email is already in use
        if (!student.getEmail().equalsIgnoreCase(request.email()) && studentRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setCourse(request.course());
        student.setGpa(request.gpa());

        Student updatedStudent = studentRepository.save(student);
        return mapToResponse(updatedStudent);
    }


    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getCourse(),
            student.getGpa(),
            student.getEnrollmentDate()
        );
    }
}
