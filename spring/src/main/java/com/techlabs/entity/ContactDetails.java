package com.techlabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contact_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contacts contacts;

    public ContactDetails(String email, long mobileNo, Contacts contacts) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.contacts = contacts;
    }
}
