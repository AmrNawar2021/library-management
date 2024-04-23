package amr.fawzi.library.management.system.servicies;

import amr.fawzi.library.management.system.entities.Patron;
import amr.fawzi.library.management.system.exception.ResourceNotFoundException;
import amr.fawzi.library.management.system.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));

        // Update patron details
        // (e.g., patron.setName(patronDetails.getName()), patron.setContactInformation(patronDetails.getContactInformation()), etc.)

        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
        patronRepository.delete(patron);
    }
}

