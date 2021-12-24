import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdressBook implements Serializable {
    
    public static String firstName, lastName, phoneNumber, userEmail;
    public static List<Entry> collection = new ArrayList<Entry>();

    static boolean running = true;
    static String fileName = null;
    static Scanner Scan = new Scanner(System.in);


    public static void main(String[] args) {
        
        while(running) {  
            System.out.println("Please choose what you'd like to do with the database: " +
                "\n Enter 0 to add a new entry." + 
                "\n Enter 1 to remove an entry." +
                "\n Enter 2 to search for an entry." +
                "\n Enter 3 to print all entries." +
                "\n Enter 4 to clear the address book." +
                "\n Enter 5 to save and quit the program." +
                "\n Enter 6 to load an addresss book."); 
            
            try {
                int answer = Scan.nextInt();
                switch(answer) {
                    case 0:
                        addEntry();
                        break;
                    case 1:
                        removeEntryWithEmail();
                        break;
                    case 2:
                        searchEntry();                            
                        break;
                    case 3:
                        printEntries();
                        break;
                    case 4:
                        clearCollection();
                        break;
                    case 5:
                        saveAndQuit();
                        break;
                    case 6:
                        System.out.println("Enter the file name to load");
                        loadAddressBook(Scan.next());
                        break;
                } 
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer between 0 and 6. Try again.");
                // Scan.reset();
                Scan.next();
            }

        }
        System.out.println("Thank you for using the address book program!");
        System.exit(0);
    }

    private static void loadAddressBook(String name) {
        FileInputStream fis = null;
        ObjectInputStream in = null;

        File file = new File(name + ".ser");
        
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                collection = (ArrayList<Entry>) in.readObject();
                fis.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\nThe file does not exist!");
        }
    }

    private static void saveAndQuit() {
        System.out.println("Enter file name: ");
        fileName = Scan.next() + ".ser";
        running = false;
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(collection);
            fos.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearCollection() {
        collection.clear();
        System.out.println("Address book has been cleared!");
    }

    private static void searchEntry() {
        System.out.println("Which method woul you like to search by:" +
        "\n Enter 0 to search by first name." +
        "\n Enter 1 to search by last name." +
        "\n Enter 2 to search by phone number." +
        "\n Enter 3 to search by email.");
        boolean exists = false;

        try {
            int choice = Scan.nextInt();
            switch(choice) {
                case 0:
                    System.out.println("Enter first name:");
                    firstName = Scan.next();
                    for (Entry entry : collection) {
                        if (entry.getFirstName().startsWith(firstName)) {
                            System.out.println("Entry result(s): ");
                            System.out.println(entry.toString());
                            exists = true;
                        } 
                    }
                    if (!exists) {
                        System.out.println("No entries found.");
                    }
                    break;
                case 1:
                    System.out.println("Enter last name: ");
                    lastName = Scan.next();
                    for (Entry entry : collection) {
                        if (entry.getLastName().startsWith(lastName)) {
                            System.out.println("Entry result(s): ");
                            System.out.println(entry.toString());
                            exists = true;
                        } 
                    }
                    if (!exists) {
                        System.out.println("No entries found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter phone number: ");
                    phoneNumber = Scan.next();
                    for (Entry entry : collection) {
                        if (entry.getPhoneNumber().equals(phoneNumber)) {
                            System.out.println("Entry result(s): ");
                            System.out.println(entry.toString());
                            exists = true;
                        } 
                    }
                    if (!exists) {
                        System.out.println("No entries found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter email: ");
                    userEmail = Scan.next();
                    for (Entry entry : collection) {
                        if (entry.getUserEmail().equals(userEmail)) {
                            System.out.println("Here is your entry:");
                            System.out.println(entry.toString());
                            exists = true;
                        } 
                    }
                    if (!exists) {
                        System.out.println("No entries found.");
                    }
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("You must choose an integer between 0 and 3.");
            // Scan.reset();
            Scan.next();
        }
    }

    private static void printEntries() {
        int num = 0;
        if (collection.size() > 0) {
            for(Entry entry : collection) {
                num ++;
                System.out.println("Entry " + num + ":" + 
                "\n First Name: " + entry.getFirstName() +
                "\n Last Name: " + entry.getLastName() +
                "\n Phone Number: " + entry.getPhoneNumber() +
                "\n Email: " + entry.getUserEmail() + 
                "\n ------------------");
            }
        } else {
            System.out.println("The address book is empty.");
        }
    }

    private static void removeEntryWithEmail() {
        String Email;

        System.out.println("Provide the email of the entry you would like to remove");
        Email = Scan.next();

        Iterator<Entry> i = collection.iterator();

        while(i.hasNext()) 
        {
            Entry entry = (Entry) i.next();
            if(entry.getUserEmail().equals(Email)) {
                System.out.println("Deleted the following entry: " + 
                "\n First Name: " + entry.getFirstName() +
                "\n Last Name: " + entry.getLastName() +
                "\n Phone Number: " + entry.getPhoneNumber() +
                "\n Email: " + entry.getUserEmail());
                i.remove();
            }
        }

    }

    private static void addEntry() {
        System.out.println("Enter first name: ");
        firstName = Scan.next();

        System.out.println("Enter last name: ");
        lastName = Scan.next();

        System.out.println("Enter phone number: ");
        phoneNumber = Scan.next();

        System.out.println("Enter email address: ");
        userEmail = Scan.next();

        boolean duplicate = false;
        for (Entry entry : collection) {
            if (entry.getUserEmail().equals(userEmail)) {
                duplicate = true;
            } else {
                duplicate = false;
            }
        }

        if (!duplicate) {
            Entry newEntry = new Entry(firstName, lastName, phoneNumber, userEmail);
            collection.add(newEntry);
            System.out.println("Added new entry!");
        } else {
            System.out.println("This user email already exists.");
        }
    }

}