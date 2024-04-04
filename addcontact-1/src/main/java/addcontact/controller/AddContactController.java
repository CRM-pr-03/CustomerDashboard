package addcontact.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import addcontact.model.AddContact;

import addcontact.service.AddContactService;

@RestController
@RequestMapping("/api/contacts")
public class AddContactController {

    @Autowired
    private AddContactService addContactService;

    @PostMapping("/addcontacts")
    public ResponseEntity<?> addOrUpdateContact(@RequestBody AddContact addContact) {
        if (addContact.getId() != null) {
            return updateContact(addContact);
        } else {
            return addNewContact(addContact);
        }
    }

    private ResponseEntity<?> updateContact(AddContact addContact) {
        ResponseEntity<?> responseEntity = addContactService.updateContact(addContact);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        return responseEntity;
    }
    
    private ResponseEntity<?> addNewContact(AddContact addContact) {
        ResponseEntity<?> responseEntity = addContactService.addContact(addContact);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
       
        }
        return responseEntity;
    }
}
