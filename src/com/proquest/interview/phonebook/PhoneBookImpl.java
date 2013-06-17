package com.proquest.interview.phonebook;

import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
    private List<Person> people = new ArrayList<Person>();

    @Override
    public void addPerson(Person newPerson) {
        people.add(newPerson); // XXX ddavison:: We should make `people` be private because what is preventing someone from doing,
                               // myPhoneBookImpl.people.add(Person)
    }

    public static void main(String[] args) {
        DatabaseUtil.initDB(); // You should not remove this line, it creates the in-memory database

        // XXX ddavison:: need to create an object otherwise we'd have to make everything static, and we don't want that.
        PhoneBookImpl pb = new PhoneBookImpl();
        /*
         * TODO: create person objects and put them in the PhoneBook and database 
         * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI 
         * Cynthi Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
         */
        Person john    = new Person("John"   , "Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
        Person cynthia = new Person("Cynthia", "Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");
        
        pb.addPerson(john);
        pb.addPerson(cynthia);
        
        // TODO: print the phone book out to System.out
        System.out.println(pb.getAllContacts());
        // TODO: find Cynthia Smith and print out just her entry
        System.out.println(pb.findPerson("cynthia", "smith").toString());
        
        // TODO: insert the new person objects into the database
        DatabaseUtil.putPersons(john, cynthia);
    }

    @Override
    public Person findPerson(String firstName, String lastName) {
        for (Person pers : people)
            // XXX ddavison:: it's always a smart idea to compensate to any capital letters. If we search for JOhn Smith, it wouldn't return.  It makes things easier.
            if (firstName.toLowerCase().equals(pers.getFirstName().toLowerCase()) && lastName.toLowerCase().equals(pers.getLastName().toLowerCase()))
                return pers;

        System.out.println("Could not find person with the name " + lastName + ", " + firstName);
        return null;
    }

    
    @Override
    public String getAllContacts() {
        String contacts = "";
        // XXX ddavison:: I will use a semi-colon seperated list - but this can be easily replaced with something else like an xml object, or json object.
        for (Person p : people)
            contacts += p.toString() + "\n";
        
        return contacts;
    }

    @Override
    public void removePerson(Person person) {
        people.remove(person);
    }
}
