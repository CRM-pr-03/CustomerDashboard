package addcontact.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import addcontact.model.AddContact;

import addcontact.repository.AddContactRepository;


@Service
public class AddContactService {

    @Autowired
    private AddContactRepository addContactRepository;

    public ResponseEntity<?> addContact(AddContact addContact) {
        if (!isValidPhoneNumber(addContact.getPhoneNumber())) {
            String message = "Please provide a valid phone number (10 digits).";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        if (!isValidEmail(addContact.getEmail())) {
            String message = "Please provide a valid email address.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        AddContact existingContact = addContactRepository.findByPhoneNumber(addContact.getPhoneNumber());
        if (existingContact != null) {
            String message = "Contact with phone number " + addContact.getPhoneNumber() + " already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        AddContact savedContact = addContactRepository.save(addContact);

        return ResponseEntity.status(HttpStatus.CREATED).body("Added contact successfully: ");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}");
    }

    public ResponseEntity<?> updateContact(AddContact addContact) {
        if (!addContactRepository.existsById(addContact.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }

        AddContact updatedContact = addContactRepository.save(addContact);
        return ResponseEntity.status(HttpStatus.OK).body("Updated contact successfully: ");
    }

    }
