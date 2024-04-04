package com.example.crm.controller;

import com.example.crm.model.Contact;
import com.example.crm.service.AddUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class CustomerController {

    @Autowired
    private AddUpdateService addUpdateService;


    @PostMapping("/addcontacts")
    public ResponseEntity<?> addOrUpdateContact(@RequestBody Contact contact) {
        if (contact.getId() != null) {
            return updateContact(contact);
        } else {
            return addNewContact(contact);
        }
    }
}
