package amr.fawzi.library.management.system.repository;

import amr.fawzi.library.management.system.entities.Book;
import amr.fawzi.library.management.system.entities.BorrowingRecord;
import amr.fawzi.library.management.system.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

}
