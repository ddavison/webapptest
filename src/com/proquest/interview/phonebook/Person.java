package com.proquest.interview.phonebook;

public class Person {
    // ddavison: changing the following fields to have private access, and adding public accessors
    private String firstName;
    // ddavison: Adding a `lastName` field instead of having just "name" as it's much more clear what exactly we need.
    // ddavison: this is very useful because your user might just assume, hey, name, let me just put
    // ddavison: Daniel.
    private String lastName;
    private String phoneNumber;
    private String address;

    public Person(String firstName, String lastName, String phoneNumber, String address) {
        // ddavison: Adding a constructor for efficiency, and populating the information.
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a friendly version of the contact.<br>
     * Example: <blockquote>John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI</blockquote>
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + phoneNumber + ", " + address;
    }
}
