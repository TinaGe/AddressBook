package com.tina.addbook;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.tina.addbook.bean.AddressBookEntity;
import com.tina.addbook.bean.ContactEntry;
import com.tina.addbook.controller.AddressBooksController;

/**
 * @author Tina 1.Add a new Address Book and save to local folder
 *
 */
public class AddressBookApp {

	public static void main(String[] args) {
		AddressBooksController addBooksCon = new AddressBooksController();

		// clear all fils

		addBooksCon.clearAddressBooks();

		System.out.println("start----------------");
		// new address book and add contacts
		ContactEntry c1, c2, c3, c4, c5, c6;
		c1 = new ContactEntry("c1", "04 2171 0961");
		c2 = new ContactEntry("c2", "04 2171 0962");
		c3 = new ContactEntry("c3", "04 2171 0963");
		c4 = new ContactEntry("c4", "04 2171 0964");
		c5 = new ContactEntry("c5", "04 2171 0965");
		c6 = new ContactEntry("c6", "04 2171 0966");

		AddressBookEntity ab1 = new AddressBookEntity("new1.csv");
		
//		AddressBookEntity ab2 = new AddressBookEntity("new2.csv");
//		ab2.addContact(c1);
//		ab2.addContact(c2);
//		// ab2.addContact(c3);

//		AddressBookEntity ab3 = new AddressBookEntity("new3.csv");
//		// ab3.addContact(c1);
//		ab3.addContact(c2);
//		ab3.addContact(c3);
//		ab3.addContact(c5);

//		AddressBookEntity ab4 = new AddressBookEntity("new4.csv");
//		ab4.addContact(c1);
//		ab4.addContact(c2);
//		ab4.addContact(c3);
//		ab4.addContact(c4);

		 addBooksCon.addressBookAdd(ab1);
		 ab1.addContact(c3);
		 addBooksCon.addressBookAdd(ab1);
//		 addBooksCon.addressBookAdd(ab2);
//		 addBooksCon.addressBookAdd(ab3);
//		addBooksCon.addressBookAdd(ab4);

		// delete contacts
//		AddressBookEntity ab5 = new AddressBookEntity("new3.csv");
//		ab4.addContact(c1);
//		ab4.addContact(c3);
		// addBooksCon.delAddBookContacts(ab5);
		// System.out.println("successful");

		// print one address book
		System.out.println("print ab1++++++++++");
		Collection<ContactEntry> contacts = addBooksCon.getContacts(ab1);
		addBooksCon.printOut(contacts);

//		System.out.println("print ab2++++++++++");
//		contacts = addBooksCon.getContacts(ab2);
//		addBooksCon.printOut(contacts);
//
//		System.out.println("print ab3++++++++++");
//		contacts = addBooksCon.getContacts(ab3);
//		addBooksCon.printOut(contacts);

		// print a unique set of selected address books
//		System.out.println("print ab1 ab2 ab3++++++++++");
//		List<AddressBookEntity> selectedAddressbook = new ArrayList<AddressBookEntity>();
//		selectedAddressbook.add(ab1);
//		selectedAddressbook.add(ab2);
//		selectedAddressbook.add(ab3);
//		contacts = addBooksCon.getUniqConFromSelected(selectedAddressbook);
//		addBooksCon.printOut(contacts);

		// print a unique set of all contacts across multiple address book
//		System.out.println("print getUniqConFromAll++++++++++");
//		contacts = addBooksCon.getUniqConFromAll();
//		addBooksCon.printOut(contacts);

		List<AddressBookEntity> result = addBooksCon.loadAddressBooks();
		System.out.println("finish:--------" + result.size());
	}

}
