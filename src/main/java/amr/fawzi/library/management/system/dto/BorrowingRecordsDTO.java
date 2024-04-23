package amr.fawzi.library.management.system.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BorrowingRecordsDTO {

    private long id;

    @NotNull(message = "Book is required")
    private BookDTO book;

    @NotNull(message = "Patron is required")
    private PatronDTO patron;

    @NotNull(message = "Borrow date is required")
    private LocalDate borrowDate;

    private LocalDate returnDate;


}
