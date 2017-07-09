package test.tina.addbook.bean;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tina.addbook.bean.AddressBookEntity;
import com.tina.addbook.bean.ContactEntry;

public class AddressBookEntityTest {
	private static AddressBookEntity addBook1,addBook2,addBook3;
	private static ContactEntry contact1,contact2,contact3;
	private static List<ContactEntry> contacts;


	@BeforeClass
	public static void before() {
		addBook1 = new AddressBookEntity("TinaWork.csv");
		addBook2 = new AddressBookEntity("TinaFamily.csv",contacts);
		addBook3 = new AddressBookEntity("TinaWork.csv",contacts);
		contact1 = new ContactEntry("Tina","0421710968");
		contact2 = new ContactEntry("Tom","0421710969");
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testContactsEqual() {
		assertTrue(addBook1.equals(addBook3));
		assertFalse(addBook1.equals(addBook2));
	}
	
	@Test
	public void testAddContact() {
		addBook1.addContact(contact3);
		assertEquals(0,addBook1.getContacts().size());
		addBook2.addContact(contact3);
		assertEquals(0,addBook2.getContacts().size());	

		addBook1.addContact(contact1);
		assertEquals(1,addBook1.getContacts().size());
		addBook2.addContact(contact1);
		assertEquals(1,addBook2.getContacts().size());	
		
		addBook1.addContact(contact1);
		assertEquals(1,addBook1.getContacts().size());	
		addBook2.addContact(contact2);
		assertEquals(2,addBook2.getContacts().size());	
	}
	
	@Test
	public void testDelContacts() {
		contacts=new ArrayList<ContactEntry>();
		addBook3.addContact(contact1);
		addBook3.addContact(contact1);
		addBook3.addContact(contact2);
		contacts.add(contact2);
		contacts.add(contact3);
		contacts.add(contact2);
		addBook3.deleteContacts(contacts);
		assertEquals(1,addBook3.getContacts().size());	
	}
	
}
