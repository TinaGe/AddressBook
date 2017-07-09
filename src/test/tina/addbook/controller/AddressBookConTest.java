package test.tina.addbook.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;  

import com.tina.addbook.bean.AddressBookEntity;
import com.tina.addbook.bean.ContactEntry;
import com.tina.addbook.controller.AddressBooksController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressBookConTest {
	private static AddressBooksController addBooksCon;
	private static AddressBookEntity addBook1,addBook2,addBook3;
	private static AddressBookEntity addBookDel,addBookSave;
	private static ContactEntry contact1,contact2,contact3,contact4,contact5,contact6;
	private static List<ContactEntry> contacts;

	@BeforeClass
	public static void before() {
		addBooksCon=new AddressBooksController();
		addBook1 = new AddressBookEntity("TinaCollegue.csv");
		addBook2 = new AddressBookEntity("TinaFamily.csv");
		addBook3 = new AddressBookEntity("TinaCustomer.csv");
		addBookSave = new AddressBookEntity("TinaNewCustomer.csv");
		addBookDel = new AddressBookEntity("TinaCollegue.csv");
		contact1 = new ContactEntry("Tina","0421710968");
		contact2 = new ContactEntry("Tom","0421710969");
		contact3 = new ContactEntry("Jerry","0421710968");
		contact4 = new ContactEntry("Bob","0421710969");
		contact5 = new ContactEntry("John","0421710968");
		contact6 = new ContactEntry("Paul","0421710969");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAddAddressBooks() {
		addBooksCon.clearAddressBooks();
		assertEquals(0,addBooksCon.getAddressBooks().size());
		addBooksCon.addressBookAdd(addBook1);
		addBooksCon.addressBookAdd(addBook1);
		assertEquals(1,addBooksCon.getAddressBooks().size());
		addBooksCon.addressBookAdd(addBook1);
		addBooksCon.addressBookAdd(addBook2);
		addBooksCon.addressBookAdd(addBook3);
		assertEquals(3,addBooksCon.getAddressBooks().size());
	}
	
	@Test
	public void testClearAddressBooks() {
		assertEquals(3,addBooksCon.getAddressBooks().size());
		addBooksCon.clearAddressBooks();
		assertEquals(0,addBooksCon.getAddressBooks().size());		
	}
	
	
	
	@Test
	public void testDelContacts() {
		addBooksCon.addressBookAdd(addBook1);
		addBooksCon.addressBookAdd(addBook2);
		addBooksCon.addressBookAdd(addBook3);
		int index = addBooksCon.getAddressBooks().indexOf(addBookDel);
		AddressBookEntity exsitAddBook = addBooksCon.getAddressBooks().get(index);
		
		addBooksCon.delAddBookContacts(addBookDel);		
		
		exsitAddBook.addContact(contact1);
		exsitAddBook.addContact(contact2);
		assertEquals(2,exsitAddBook.getContacts().size());  //before delete c1 and c2
		
		contacts=new ArrayList<ContactEntry>();
		contacts.add(contact1);
		contacts.add(contact3);
		addBookDel.setContacts(contacts);
		addBooksCon.delAddBookContacts(addBookDel);  //after delete c2
		assertEquals(1,exsitAddBook.getContacts().size());
	}

	@Test
	public void testLoadFiels() {
		assertEquals(3,addBooksCon.loadAddressBooksFiles().length);
	}
	
	@Test
	public void testLoadAddBooks() {
		addBooksCon.loadAddressBooks();
		assertEquals(3,addBooksCon.getAddressBooks().size());
		assertTrue(addBooksCon.getAddressBooks().contains(addBook1));
		assertTrue(addBooksCon.getAddressBooks().contains(addBook2));
		assertTrue(addBooksCon.getAddressBooks().contains(addBook3));
		assertFalse(addBooksCon.getAddressBooks().contains(addBookSave));
	}
	
	@Test
	public void testSaveAddBook() {
		assertEquals(3,addBooksCon.getAddressBooks().size());
		addBooksCon.saveAddressBook(addBookSave);
        addBooksCon.loadAddressBooks();
		assertEquals(4,addBooksCon.getAddressBooks().size());
	}
	
	@Test
	public void testShowContacts() {
		addPrintTestContacts();
		System.out.println(addBook1.getAddressBookName()+":");
		assertEquals(2,addBooksCon.getContacts(addBook1).size());
		System.out.println("\n"+addBook2.getAddressBookName()+":");
		assertEquals(3,addBooksCon.getContacts(addBook2).size());
		System.out.println("\n"+addBook3.getAddressBookName()+":");
		assertEquals(4,addBooksCon.getContacts(addBook3).size());
		System.out.println("\n---------------------------------");
	}
	
	
	@Test
	public void testShowUniqConFromAll() {
		System.out.println("\nUnique set of contacts from All Address Books");
		assertEquals(6,addBooksCon.getUniqConFromAll().size());
		System.out.println("\n---------------------------------");
		
	}
	
	
	@Test
	public void testShowUniqConFromSelected() {
		List<AddressBookEntity> selectedAddressbook=new ArrayList<AddressBookEntity>();
		selectedAddressbook.add(addBook1);
		selectedAddressbook.add(addBook2);

		System.out.println("\nUnique set of contacts from addBook1 and addBook2:");
		assertEquals(4,addBooksCon.getUniqConFromSelected(selectedAddressbook).size());
		System.out.println("\n---------------------------------");
		
		selectedAddressbook.remove(addBook1);
		selectedAddressbook.add(addBook3);
		
		System.out.println("\nUnique set of contacts from addBook2 and addBook3:");
		assertEquals(5,addBooksCon.getUniqConFromSelected(selectedAddressbook).size());
		System.out.println("\n---------------------------------");
	}
	
	
	public void addPrintTestContacts() {
		addBooksCon.clearAddressBooks();
		addBook1 = new AddressBookEntity("TinaCollegue.csv");
		addBook2 = new AddressBookEntity("TinaFamily.csv");
		addBook3 = new AddressBookEntity("TinaCustomer.csv");

		addBook1.addContact(contact1);
		addBook1.addContact(contact5);
		
		addBook2.addContact(contact1);
		addBook2.addContact(contact4);
		addBook2.addContact(contact3);
		
		addBook3.addContact(contact2);
		addBook3.addContact(contact3);
		addBook3.addContact(contact4);
		addBook3.addContact(contact6);
		
		addBooksCon.addressBookAdd(addBook1);
		addBooksCon.addressBookAdd(addBook2);
		addBooksCon.addressBookAdd(addBook3);
	}
	
}
