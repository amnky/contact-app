package com.techlabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    @NotNull(message = "firstname should not be null")
    private String firstName;
    @NotNull(message = "lastname should not be null")
    @Column(name="last_name")
    private String lastName;

    @Column(name="is_admin")
    private boolean isAdmin;

    @Column(name="is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contacts> contactsList;

    public Users(String firstName, String lastName, boolean isAdmin,boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.isActive=isActive;
    }

    public void addContact(Contacts contacts){
        contactsList.add(contacts);
        contacts.setUsers(this);
    }
    public void removeContact(Contacts contacts){
        contacts.setUsers(null);
    }
}
