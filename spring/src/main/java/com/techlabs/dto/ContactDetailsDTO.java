package com.techlabs.dto;

public class ContactDetailsDTO {
    private int contactId;
    private String email;
    private long mobileNo;

    public ContactDetailsDTO(int contactId, String email, long mobileNo) {
        this.contactId = contactId;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
