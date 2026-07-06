package studentmanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "students")
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    
    @Indexed(unique = true)
    private String email;
    
    private String course;
    private Double gpa;
    private LocalDate enrollmentDate;

    // Constructors
    public Student() {
    }

    public Student(String firstName, String lastName, String email, String course, Double gpa, LocalDate enrollmentDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.gpa = gpa;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
