package com.hibernate.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class userDetails implements Serializable {
    @Id
//    @GeneratedValue
    @Column(name = "Id")
    private long Id;
    @Column(name = "User", length = 255)
    private String User;
    @Column(name="Gender")
    private Gender gender;
    @Column(name="Phone")
    private String[] phone;

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public userDetails() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        this.User = user;
    }

   
    
}
