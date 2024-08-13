package com.techlabs.dto;

public class ContactDetailsResponseDTO {
    private String email;
    private long mobileNo;

    public ContactDetailsResponseDTO(String email, long mobileNo) {
        this.email = email;
        this.mobileNo = mobileNo;
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
