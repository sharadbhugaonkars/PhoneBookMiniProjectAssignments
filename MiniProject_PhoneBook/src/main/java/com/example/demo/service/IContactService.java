package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;


public interface IContactService {
	
	
	
	public String saveContact(Contact contact);

	public String updateContact(Contact contact);

	public String deleteContactById(Integer contactId);

	public Contact getContactById(Integer contactId);

	public List<Contact> getAllContacts();


}
