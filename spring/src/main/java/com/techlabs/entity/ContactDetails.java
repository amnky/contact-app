package com.techlabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_details_id")
    private int contactDetailsId;

    @Column(name="email")
    @Email
    private String email;
    @Column(name="mobile_no")
    @NotNull
    private long mobileNo;

    @OneToOne
    @JoinColumn(name="contact_id",referencedColumnName = "contact_details_id")
    private Contacts contacts;

    public ContactDetails(String email, long mobileNo, Contacts contacts) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.contacts = contacts;
    }

    public int getContactDetailsId() {
        return contactDetailsId;
    }

    public void setContactDetailsId(int contactDetailsId) {
        this.contactDetailsId = contactDetailsId;
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

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
}
