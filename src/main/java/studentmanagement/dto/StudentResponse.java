package studentmanagement.dto;

import java.time.LocalDate;

public record StudentResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    String course,
    Double gpa,
    LocalDate enrollmentDate
) {}
