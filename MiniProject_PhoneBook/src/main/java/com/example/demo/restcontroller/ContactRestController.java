package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Contact;
import com.example.demo.exception.ContactNotFoundException;
import com.example.demo.service.IContactService;

@RestController
@RequestMapping("/v1/api/contact")
public class ContactRestController {

	@Autowired
	private IContactService service;
	
	//1. create one contact
	@PostMapping("/create")
	public ResponseEntity<String> createContact(
			@RequestBody Contact contact
			)
	{
		 service.saveContact(contact);
		String message = "Contact '"+contact.getContactId()+"' CREATED";
		
		//return new ResponseEntity<String>(message, HttpStatus.OK);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);//201
	}
	
	//2. fetch all contacts
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getAllContacts() {
		List<Contact> list = service.getAllContacts();
		//return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		return ResponseEntity.ok(list);
	}
	
	//3. fetch one by contactId
	@GetMapping("/find/{contactId}")
	public ResponseEntity<Contact> getOneContact(
			@PathVariable("contactId") Integer contactId
			) 
	{
		ResponseEntity<Contact> response = null;
		try {
			Contact contact= service.getContactById(contactId);
			response = ResponseEntity.ok(contact);
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	//4. remove one by contactId
	@DeleteMapping("/remove/{contactId}")
	public ResponseEntity<String> deleteStudent(
			@PathVariable("contactId") Integer contactId
			) 
	{
		ResponseEntity<String> response = null;
		try {
			service.deleteContactById(contactId);
			response = ResponseEntity.ok("Contact '"+contactId+"' REMOVED");
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	//5. update contact
	@PutMapping("/modify")
	public ResponseEntity<String> updateContact(
			@RequestBody Contact contact
			)
	{
		ResponseEntity<String> response = null;
		try {
			service.updateContact(contact);
			response = ResponseEntity.ok("Contact '"+contact.getContactId()+"' UPDATED");
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
