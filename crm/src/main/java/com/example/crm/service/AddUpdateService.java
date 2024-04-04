package com.example.crm.service;

import com.example.crm.model.Contact;
import com.example.crm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddUpdateService {

    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<?> addContact(Contact contact) {
        if (!isValidPhoneNumber(contact.getPhoneNumber())) {
            String message = "Please provide a valid phone number (10 digits).";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        if (!isValidEmail(contact.getEmail())) {
            String message = "Please provide a valid email address.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Contact existingContact = contactRepository.findByPhoneNumber(contact.getPhoneNumber());
        if (existingContact != null) {
            String message = "Contact with phone number " + contact.getPhoneNumber() + " already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        Contact savedContact = contactRepository.save(contact);

        return ResponseEntity.status(HttpStatus.CREATED).body("Added contact successfully: ");
    }

}
