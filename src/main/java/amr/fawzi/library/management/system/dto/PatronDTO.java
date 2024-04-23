package amr.fawzi.library.management.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PatronDTO {

    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Pattern(regexp = "\\d{10}", message = "Contact information must be a 10-digit number")
    private String contactInformation;

    // Getters and setters
}
