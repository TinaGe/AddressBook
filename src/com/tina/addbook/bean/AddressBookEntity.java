package com.tina.addbook.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tina
 * Bean of Address Book
 *
 */
public class AddressBookEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String addressBookName;
	private List<ContactEntry> contacts;

	public AddressBookEntity(String name) {
		this(name, new ArrayList<ContactEntry>());
	}

	public AddressBookEntity(String addressBookName, List<ContactEntry> contacts) {
		this.addressBookName = addressBookName;
		if (contacts != null) {
			this.contacts = contacts;
		} else {
			this.contacts = new ArrayList<ContactEntry>();
		}

	}

	public void addContact(ContactEntry contact) {
		if (contacts != null && contact != null && !contacts.contains(contact)) {
			contacts.add(contact);
		}
	}


	public void deleteContacts(List<ContactEntry> contactsList) {
		if (!contactsList.isEmpty() && contactsList != null && contactsList.size() != 0) {
			contacts.removeAll(contactsList);
		}
	}

	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public List<ContactEntry> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactEntry> contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		return (addressBookName.length());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AddressBookEntity) {
			AddressBookEntity addressBook = (AddressBookEntity) obj;
			return this.addressBookName.equals(addressBook.getAddressBookName());
		}
		return false;
	}
}
