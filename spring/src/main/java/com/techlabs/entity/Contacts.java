package com.techlabs.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name="contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private int contactId;
    @NotNull(message = "firstName can not be null")
    @Column(name="first_name")
    private String firstName;
    @NotNull(message = "lastName can not be null")
    @Column(name="last_name")
    private String lastName;
    @NotNull(message = "isActive can not be null")
    @Column(name="is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "contacts", fetch = FetchType.LAZY)
    private List<ContactDetails> contactDetailsList;

    @ManyToOne
    @JoinColumn(name="user_id")
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

    public void addContactDetails(ContactDetails contactDetails){
        contactDetailsList.add(contactDetails);
        contactDetails.setContacts(this);
    }
    public void removeContactDetails(ContactDetails contactDetails){
        contactDetailsList.remove(contactDetails);
        contactDetails.setContacts(null);
    }

}
