package com.example.demo.service.impl;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.exception.ContactNotFoundException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.IContactService;


@Service
public class ContactServiceImpl implements IContactService {
	
	@Autowired

	private ContactRepository repo;

	@Override
	public String saveContact(Contact contact) {
		
		contact = repo.save(contact);
       if(contact.getContactId() != null) {
		return "Contact Created";
		
	}else {
		return "Contact Failed To Save";
	}
	}
	@Override
	public String updateContact(Contact contact) {
		if(repo.existsById(contact.getContactId())) {
		repo.save(contact);
		return "Contact Updated!!!";
		
		} else {
			throw new ContactNotFoundException("CONTACT '"+contact.getContactId()+"'NOT EXIST");
		}
	}

	/*@Override
	public List<Contact> deleteContactById(Integer contactId) {
		// TODO Auto-generated method stub
		List<Contact> list = repo.delete(getContactById(contactId));
		return list
		//return "Contact Deleted Successfully!!"
	}*/
	
	@Override
	public String deleteContactById(Integer contactId) {
		if(repo.existsById(contactId)) {
		 repo.deleteById(contactId);
		return "Contact Deleted Successfully!!";
		
		}else {
			return "No Record Found";
		}
	}

	/*@Override
	public Contact getContactById(Integer contactId) {
		
		 Optional<Contact> getoneById= repo.findById(contactId);
		 if (getoneById.isPresent()) {
		 return getoneById.get();
		 }
		 return null;
	}*/
	public Contact getContactById(Integer contactId) {
		return repo.findById(contactId)
				.orElseThrow(()->new ContactNotFoundException("CONTACT '"+contactId+"' NOT EXIST")
						);
	}
	

	@Override
	public List<Contact> getAllContacts() {
				
		List<Contact> list = repo.findAll();
		return list;
	}

}
