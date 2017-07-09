package com.tina.addbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tina.addbook.bean.AddressBookEntity;
import com.tina.addbook.bean.ContactEntry;
import com.tina.addbook.controller.AddressBooksController;

/**
 * @author Tina
 *
 *         Integration test for Address Book App
 */
public class AddressBookApp {

	public static void main(String[] args) {
		AddressBooksController addBooksCon = new AddressBooksController();

		// clear all fils
		addBooksCon.clearAddressBooks();

		// Initial new address books and new contacts
		ContactEntry c1, c2, c3, c4, c5, c6;
		c1 = new ContactEntry("c1", "04 2171 0961");
		c2 = new ContactEntry("c2", "04 2171 0962");
		c3 = new ContactEntry("c3", "04 2171 0963");
		c4 = new ContactEntry("c4", "04 2171 0964");
		c5 = new ContactEntry("c5", "04 2171 0965");
		c6 = new ContactEntry("c6", "04 2171 0966");

		AddressBookEntity ab1, ab2, ab3, ab4, ab5;
		ab1 = new AddressBookEntity("TinaWork.csv");

		ab2 = new AddressBookEntity("TinaFamily.csv");
		ab2.addContact(c1);
		ab2.addContact(c6);

		ab3 = new AddressBookEntity("TinaCollegue.csv");
		ab3.addContact(c2);
		ab3.addContact(c3);
		ab3.addContact(c5);

		ab4 = new AddressBookEntity("TinaClassmates.csv");
		ab4.addContact(c1);
		ab4.addContact(c2);
		ab4.addContact(c3);
		ab4.addContact(c4);

		// new Address with new contacts
		addBooksCon.addressBookAdd(ab1);
		addBooksCon.addressBookAdd(ab2);
		addBooksCon.addressBookAdd(ab3);
		addBooksCon.addressBookAdd(ab4);

		// add one duplicate contacts and one new contact
		addBooksCon.addressBookAdd(ab2);
		ab2.addContact(c4);
		ab2.addContact(c6);

		// delete contacts from existing address book
		ab5 = new AddressBookEntity("TinaFamily.csv");
		ab5.addContact(c1);
		ab5.addContact(c3);
		addBooksCon.delAddBookContacts(ab5);

		// print one empty address book
		System.out.println("TinaWork.csv:");
		Collection<ContactEntry> contacts = addBooksCon.getContacts(ab1);
		System.out.println("----------------------------");

		// print one address book with 2 values
		System.out.println("TinaFamily.csv:");
		contacts = addBooksCon.getContacts(ab2);
		System.out.println("----------------------------");

		// print one address book with 2 values
		System.out.println("TinaClassmates.csv:");
		contacts = addBooksCon.getContacts(ab4);
		System.out.println("----------------------------");

		// print a unique set across selected address books
		System.out.println("Unique set of contacts from TinaWork.csv,TinaFamily.csv and TinaClassmates.csv:");
		List<AddressBookEntity> selectedAddressbook = new ArrayList<AddressBookEntity>();
		selectedAddressbook.add(ab1);
		selectedAddressbook.add(ab2);
		selectedAddressbook.add(ab4);
		contacts = addBooksCon.getUniqConFromSelected(selectedAddressbook);
		System.out.println("----------------------------");

		// print a unique set of contacts across all address book
		System.out.println("Unique set of contacts from all address books:");
		contacts = addBooksCon.getUniqConFromAll();
	}

}
