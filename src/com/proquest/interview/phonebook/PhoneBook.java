package com.proquest.interview.phonebook;

public interface PhoneBook {
    // XXX: ddavison: Documentation is ALWAYS a good thing. Adding some.
    
    /**
     * Find a person by first name, last name
     * 
     * @param firstName
     *            The person's first name
     * @param lastName
     *            The person's last name
     * @return A {@link Person} object
     */
    public Person findPerson(String firstName, String lastName);

    /**
     * Add a person to the contact list
     * @param newPerson The {@link Person} to add
     */
    public void addPerson(Person newPerson);
    
    // XXX: ddavison: Adding this method because, why would you have `addPerson` without `removePerson`? keeps things consistent.
    /**
     * Remove a person from the list of contacts
     * @param newPerson The {@link Person} to remove
     */
    public void removePerson(Person person);

    // XXX: ddavison: adding this function, to add the ability for the implementing class to make any format they want. JSON, XML, etc.
    /**
     * Get all the contacts
     * @return
     */
    public String getAllContacts();
}
