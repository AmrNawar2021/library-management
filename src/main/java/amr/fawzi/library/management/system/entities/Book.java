package amr.fawzi.library.management.system.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 9999, message = "Publication year cannot exceed 9999")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "\\d{3}-\\d{10}", message = "ISBN must be in the format XXX-XXXXXXXXXX")
    private String isbn;

    private boolean available;

}
