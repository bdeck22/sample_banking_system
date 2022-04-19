// Data Model/Table of Users
package com.exambank.app.rest.Model;
import  javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private Double accountTotalAmount;

    // Empty Constructor
    public User() {

    }

    // Constructor with parameters
    public User(long id, String firstName, String lastName, String userName,
                String password, Double accountTotalAmount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.accountTotalAmount = accountTotalAmount;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAccountTotalAmount() {
        return accountTotalAmount;
    }

    public void setAccountTotalAmount(Double accountTotalAmount) {
        this.accountTotalAmount = accountTotalAmount;
    }

}
