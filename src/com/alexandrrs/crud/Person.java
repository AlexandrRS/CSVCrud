package com.alexandrrs.crud;

import java.util.Date;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class Person {

    private String firstName;
    private String lastName;
    private Sex sex;
    private Date birthDate;
    private String address;
    private boolean isDeleted;

    public Person(String firstName, String lastName, Sex sex, Date birthDate, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Person(String firstName, String lastName, Sex sex, Date birthDay, String address, boolean isDeleted) {
        this(firstName, lastName, sex, birthDay, address);
        this.isDeleted = isDeleted;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + ", "  + (sex == Sex.Male? 'M' : 'W') + ", " + PropertiesHelper.getUserDateFormat().format(birthDate) + ", " + address;
    }
}
