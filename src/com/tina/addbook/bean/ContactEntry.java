package com.tina.addbook.bean;

import java.io.Serializable;

/**
 * @author Tina
 * 
 * Bean of Contact Entity
 *
 */
public class ContactEntry implements Serializable {
	
	private static final long serialVersionUID = 1L; 

	private String contactName;
	private String contactPhone;
	
	
	public ContactEntry(String contactName, String contactPhone){
		this.contactName=contactName;
		this.contactPhone=contactPhone;		
	}


	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Override
    public String toString() {
		return contactName + ", " + contactPhone;
	}
    
    @Override 
    public int hashCode() {
        return (contactName.length() + contactPhone.length());
    }
	
    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof ContactEntry) {
        	ContactEntry contact = (ContactEntry) obj;
            return (contactName.equals(contact.getContactName()) && contactPhone
                    .equals(contact.getContactPhone()));
        }
        return false;
    }
    
}
