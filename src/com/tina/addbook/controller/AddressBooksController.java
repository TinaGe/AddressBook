package com.tina.addbook.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tina.addbook.bean.AddressBookEntity;
import com.tina.addbook.bean.ContactEntry;

/**
 * @author Tina
 * 
 *         Address Books Controller 1. add new address books 2. update existing
 *         address books 3. add contacts into address books 4. delete existing
 *         contacts from address books 5. clear existing address books from APP
 *         folder 6. print all contacts from an address book 7. print unique
 *         contacts from selected address books 8. print unique contacts from
 *         all address books under the App folder 9. load all address books into
 *         persist List 10. create folder for store address books
 *
 */

public class AddressBooksController {

	private List<AddressBookEntity> addressBooks;
	final String folderName = "AddressBookFolder";

	public AddressBooksController() {
		createFolder();
		addressBooks = new ArrayList<AddressBookEntity>();
		addressBooks = loadAddressBooks();
	}

	/**
	 * @param addressbook
	 *            Add a new address book into persist list And save to file
	 */
	public void addressBookAdd(AddressBookEntity addressbook) {
		if (!addressBooks.contains(addressbook)) { // add a new address book
			addressBooks.add(addressbook);
		} else { // update existing address book
			int index = addressBooks.indexOf(addressbook);
			AddressBookEntity exsitAddBook = addressBooks.get(index);

			/** Duplicate contacts is not allowed in an address book **/
			for (ContactEntry contact : addressbook.getContacts()) {
				exsitAddBook.addContact(contact);
			}
		}
		saveAddressBook(addressbook);
	}

	/**
	 * @param addressbook
	 *            delete selected contacts from an address book
	 */
	public void delAddBookContacts(AddressBookEntity addressbook) {
		if (addressBooks.contains(addressbook)) {
			int index = addressBooks.indexOf(addressbook);
			AddressBookEntity exsitAddBook = addressBooks.get(index);
			exsitAddBook.deleteContacts(addressbook.getContacts());
			saveAddressBook(exsitAddBook);
			//System.out.println("Address Book " + addressbook.getAddressBookName() + " Updated");
		} else {
			System.out.println("This Address Book is not exist");
		}
	}

	/**
	 * @param addressBook
	 *            save address book in local folder
	 */
	public void saveAddressBook(AddressBookEntity addressBook) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(folderName + "/" + addressBook.getAddressBookName());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(fileOutput);
			ObjectOutput.writeObject(addressBook.getContacts());
			ObjectOutput.close();
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return all exist .CSV address book files in the folder
	 */
	public String[] loadAddressBooksFiles() {
		File file = new File(folderName);
		String suffix = "csv";
		String[] fileNames = null;
		if (file.exists()) {
			fileNames = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (dir.isDirectory() && name.endsWith(suffix)) {
						return true;
					} else {
						return false;
					}
				}
			});
		}
		return fileNames;
	}

	/**
	 * @return Load All Address Books in a Persist List
	 */
	@SuppressWarnings("unchecked")
	public List<AddressBookEntity> loadAddressBooks() {
		addressBooks = new ArrayList<AddressBookEntity>();
		List<ContactEntry> contacts = new ArrayList<ContactEntry>();
		String[] fileNames = loadAddressBooksFiles();
		if (fileNames != null && fileNames.length > 0) {
			try {
				ObjectInputStream ObjectInput = null;

				for (String fileName : fileNames) {
					ObjectInput = new ObjectInputStream(new FileInputStream(folderName + "/" + fileName));
					contacts = (List<ContactEntry>) ObjectInput.readObject();
					addressBooks.add(new AddressBookEntity(fileName, contacts));
				}
				ObjectInput.close();
				System.gc();
			} catch (EOFException ex) {
				System.out.println("");
			} catch (FileNotFoundException ex) {
				System.out.println("No address books stored");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return addressBooks;
	}

	/**
	 * Delete all address books in the folder
	 */
	public void clearAddressBooks() {
		addressBooks.clear();
		File file = new File(folderName);
		if (!(file.exists() && file.isDirectory())) {
			return;
		}
		File files[] = file.listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}

	}

	/**
	 * @param addressbook
	 * @return all contacts in an address book
	 */
	public List<ContactEntry> getContacts(AddressBookEntity addressbook) {
		List<ContactEntry> contacts = new ArrayList<ContactEntry>();
		int index = addressBooks.indexOf(addressbook);
		AddressBookEntity addressbook1 = addressBooks.get(index);
		contacts = addressbook1.getContacts();
		printOut(contacts);

		return contacts;
	}

	/**
	 * @return unique contacts from all address books
	 */
	public Set<ContactEntry> getUniqConFromAll() {
		Set<ContactEntry> uniqueContacts = new HashSet<ContactEntry>();
		uniqueContacts = getUniqConFromSelected(addressBooks);
		return uniqueContacts;
	}

	/**
	 * @param selectedAddressbook
	 * @return unique contacts from selected address books
	 */
	public Set<ContactEntry> getUniqConFromSelected(List<AddressBookEntity> selectedAddressbook) {
		Set<ContactEntry> uniqueContacts = new HashSet<ContactEntry>();

		for (AddressBookEntity addressBook : selectedAddressbook) {
			List<ContactEntry> contacts = addressBook.getContacts();

			for (ContactEntry contact : contacts) {
				if (!uniqueContacts.contains(contact)) {
					uniqueContacts.add(contact);
				}
			}
		}
		printOut(uniqueContacts);
		return uniqueContacts;
	}

	public void printOut(Collection<ContactEntry> contacts) {
		for (ContactEntry c : contacts) {
			System.out.println(c);
		}
	}

	
	/**
	 * create Address Book Folder
	 */
	public void createFolder(){
		File file = new File(folderName);
		if (!(file.exists() && file.isDirectory())) {
			file.mkdir();
		}
	}
	
	public List<AddressBookEntity> getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(List<AddressBookEntity> addressBooks) {
		this.addressBooks = addressBooks;
	}
}
