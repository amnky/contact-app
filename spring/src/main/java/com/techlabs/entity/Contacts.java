package com.techlabs.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "contactId can not be null")
    @Column(name="contact_id")
    private int contactId;
    @NotNull(message = "contactId can not be null")
    @Column(name="first_name")
    private String firstName;
    @NotNull(message = "contactId can not be null")
    @Column(name="last_name")
    private String lastName;
    @NotNull(message = "contactId can not be null")
    @Column(name="is_active")
    private boolean isActive;
    @OneToOne(mappedBy = "contact")
    private ContactDetails contactDetailsList;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private Users users;

    public Contacts(String firstName,String lastName,boolean isActive,Users users){
        this.firstName=firstName;
        this.lastName=lastName;
        this.isActive=isActive;
        this.users=users;

    }
    public Contacts(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;

    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addContactDetails(ContactDetails contactDetails){
        this.contactDetailsList=contactDetails;
        contactDetails.setContacts(this);
    }
    public void removeContactDetails(ContactDetails contactDetails){
        contactDetails.setContacts(null);
    }

    public ContactDetails getContactDetailsList() {
        return contactDetailsList;
    }

    public void setContactDetailsList(ContactDetails contactDetailsList) {
        this.contactDetailsList = contactDetailsList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
