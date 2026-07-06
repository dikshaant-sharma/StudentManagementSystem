package studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
    @NotBlank(message = "First name is required")
    String firstName,

    @NotBlank(message = "Last name is required")
    String lastName,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank(message = "Course is required")
    String course,

    @NotNull(message = "GPA is required")
    @Min(value = 0, message = "GPA cannot be less than 0.0")
    @Max(value = 4, message = "GPA cannot be more than 4.0")
    Double gpa
) {}
