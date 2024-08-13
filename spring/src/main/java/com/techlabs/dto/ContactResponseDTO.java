package com.techlabs.dto;

public class ContactResponseDTO {
    private int contactId;
    private String firstName;
    private String lastName;
    private boolean isActive;

    public ContactResponseDTO(int contactId, String firstName, String lastName, boolean isActive) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
