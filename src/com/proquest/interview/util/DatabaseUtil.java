package com.proquest.interview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.proquest.interview.phonebook.Person;

/**
 * This class is just a utility class, you should not have to change anything here
 * 
 * @author rconklin
 */
public class DatabaseUtil {
    // XXX ddavison: moving these to the class level, and making static to enable access, but making private.
    private static Connection cn;
    private  static Statement stmt;
    public static void initDB() {
        try {
            cn = getConnection();
            stmt = cn.createStatement();
            
            // XXX ddavison: changing schema to match implementation of firstname / lastname vs just "name"
            stmt.execute("CREATE TABLE PHONEBOOK (FIRSTNAME varchar(255), LASTNAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
            stmt.execute("INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) VALUES('Chris', 'Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
            stmt.execute("INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) VALUES('Dave', 'Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
            cn.commit();
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() {
        // XXX ddavison: moving the try catch block here because it offers more clarity on why it fails.
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
        } catch (Exception x) {
            System.err.println("There was an error connecting to the database!");
            x.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Put 1 or more {@link Person} into the database.
     * @param persons
     */
    public static void putPersons(Person ... persons) {
        String query = "INSERT INTO PHONEBOOK (FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS) VALUES ";
        try {
            for (Person person : persons)
                query += "('"+person.getFirstName()+"', '"+person.getLastName()+"', '"+person.getPhoneNumber()+"', '"+person.getAddress()+"'),";
            // XXX ddavison: Strip the comma. it will ALWAYS be there, no matter the length.
            //  and it will never be blank because the parameter is, of course, required. 
            query = query.substring(0, query.length() -1); 
            
            // XXX ddavison: open the connection. note that we open it after the previous for loop. this is optimal.
            cn = getConnection();
            stmt = cn.createStatement();
            stmt.execute(query);
            cn.commit();
            cn.close();
        } catch (Exception x) {
            
        }
    }
}
