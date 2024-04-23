package amr.fawzi.library.management.system.servicies;

import amr.fawzi.library.management.system.entities.Book;
import amr.fawzi.library.management.system.entities.BorrowingRecord;
import amr.fawzi.library.management.system.entities.Patron;
import amr.fawzi.library.management.system.exception.ResourceNotFoundException;
import amr.fawzi.library.management.system.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);

        if (!book.isAvailable()) {
            throw new ResourceNotFoundException("Book is not available for borrowing");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());


        book.setAvailable(false);
        bookService.updateBook(book.getId(), book);

        return borrowingRecordRepository.save(borrowingRecord);
    }

    public void returnBook(Long bookId, Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new ResourceNotFoundException("No active borrowing record found for the book and patron"));

        // Set return date
        borrowingRecord.setReturnDate(LocalDate.now());

        // Set book as available
        book.setAvailable(true);
        bookService.updateBook(book.getId(), book);

        borrowingRecordRepository.save(borrowingRecord);
    }
}
