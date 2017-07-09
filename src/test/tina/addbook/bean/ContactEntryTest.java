package test.tina.addbook.bean;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tina.addbook.bean.ContactEntry;



public class ContactEntryTest {
	private static ContactEntry contact1;
	private static ContactEntry contact2;
	private static ContactEntry contact3;
	private static ContactEntry contact4;
	

	@BeforeClass
	public static void before() {
		contact1 = new ContactEntry("Tina","0421710968");
		contact2 = new ContactEntry("Tina","0421710969");
		contact3 = new ContactEntry("Ting","0421710969");
		contact4 = new ContactEntry("Tina","0421710968");
	}	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();


	@Test
	public void testContactsToString() {
		assertTrue("Tina, 0421710968".equals(contact1.toString()));
		assertFalse("Tina,0421710968".equals(contact1.toString()));
	}
	
	
	@Test
	public void testContactsEqual() {
		assertTrue(contact1.equals(contact4));
		assertFalse(contact1.equals(contact2));
        assertFalse(contact2.equals(contact3));
        assertFalse(contact1.equals(contact3));
	}

}
