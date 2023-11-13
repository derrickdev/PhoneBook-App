import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhonebookApplication {
    private List<Contact> contacts;

    public PhonebookApplication() {
        contacts = new ArrayList<>();
    }




    public Contact findContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public boolean validateFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstNameExpected = true;
            int phoneNumberCount = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("---")) {
                    isFirstNameExpected = true;
                    phoneNumberCount = 0;
                } else if (isFirstNameExpected) {
                    if (!line.trim().startsWith("First name")) {
                        return false; // Invalid file format
                    }
                    isFirstNameExpected = false;
                } else if (!line.trim().startsWith("Last name") && !line.trim().startsWith("MOBILE") &&
                        !line.trim().startsWith("OFFICE") && !line.trim().startsWith("HOME")) {
                    return false; // Invalid file format
                } else if (line.trim().startsWith("MOBILE")) {
                    phoneNumberCount++;
                }
            }

            return phoneNumberCount <= 5; // Phone number limit check
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

    }


    public void loadContacts(String fileName) {
        if (!validateFile(fileName)) {
            System.out.println("Invalid file format or phone number limit exceeded. Unable to load contacts." +
                    "\n You must have at most 5 number for a person and ensure that there is no letter inside the number categories input ");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Contact currentContact = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("---")) {
                    currentContact = null;
                } else if (currentContact == null) {
                    String[] nameParts = line.split(":");
                    if (nameParts.length == 2) {
                        String firstName = nameParts[0].trim();
                        String lastName = nameParts[1].trim();
                        currentContact = new Contact(firstName, lastName);
                        contacts.add(currentContact);
                    }
                } else {
                    String[] data = line.split(":");
                    if (data.length == 2) {
                        String categoryStr = data[0].trim();
                        String number = data[1].trim();
                        PhoneNumberCategory category = null;

                        try {
                            category = PhoneNumberCategory.valueOf(categoryStr.toUpperCase());
                        } catch (IllegalArgumentException ignored) {
                            // Invalid category, skip this line
                        }

                        if (category != null) {
                            if (currentContact.getPhoneNumbers().size() >= 5) {
                                System.out.println("Maximum limit of phone numbers reached for " +
                                        currentContact.getFirstName() + " " + currentContact.getLastName()+"\n" +
                                        "Invalid file format or phone number limit exceeded. Unable to load contacts\n" +
                                        " You must have at most 5 number for a person and ensure that there is no letter inside the number categories input");
                                    break;
                            }
                        }
                        currentContact.addPhoneNumber(number, category);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void searchByName(String keyword) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getFirstName().toLowerCase().contains(keyword.toLowerCase()) || contact.getLastName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Record(s) found:");
                System.out.println("First name: " + contact.getFirstName());
                System.out.println("Last name: " + contact.getLastName());
                for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
                    System.out.println(phoneNumber.getCategory() + ": " + phoneNumber.getNumber());
                }
                System.out.println("---");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No records found for the given name.");
        }
    }
    public void searchByNumber(String number) {
        boolean found = false;
        for (Contact contact : contacts) {
            for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
                if (phoneNumber.getNumber().equals(number)) {
                    System.out.println("Record(s) found:");
                    System.out.println("First name: " + contact.getFirstName());
                    System.out.println("Last name: " + contact.getLastName());
                    System.out.println(phoneNumber.getCategory() + ": " + phoneNumber.getNumber());
                    System.out.println("---");
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No records found for the given number.");
        }
    }
/*DSa*/
    /*searchByName:

The algorithm iterates through each contact in the contacts list.
For each contact, it checks if the first name or last name contains the provided keyword.
The time complexity of the contains method is O(n), where n is the length of the string being searched.
If a match is found, it prints the contact details.
Therefore, the worst-case time complexity of this algorithm is O(m * n), where m is the number of contacts and n is the average length of the names being searched.
searchByNumber:

The algorithm iterates through each contact in the contacts list.
For each contact, it iterates through each phone number in the contact's list of phone numbers.
It compares the provided number with each phone number using the equals method.
The time complexity of the equals method for strings is O(n), where n is the length of the string being compared.
If a match is found, it prints the contact details.
Therefore, the worst-case time complexity of this algorithm is O(m * p * n),
 where m is the number of contacts, p is the average number of phone numbers per contact, and n is the average length of the phone numbers being searched.
Overall, the running time of these algorithms depends on the size of the contacts list,
 the average lengths of the names and phone numbers, and the number of phone numbers per contact.*/

/* searchByName:

Time complexity: O(m * n)
m: Number of contacts in the contacts list.
n: Average length of the names being searched.
Space complexity: O(1) (constant space)
The space used is independent of the input size and remains constant throughout the execution.
searchByNumber:

Time complexity: O(m * p * n)
m: Number of contacts in the contacts list.
p: Average number of phone numbers per contact.
n: Average length of the phone numbers being searched.
Space complexity: O(1) (constant space)
The space used is independent of the input size and remains constant throughout the execution.
In both cases, the algorithms have a linear time complexity, meaning the running time grows linearly with the size of the input.
 The constants involved in the complexity depend on the average lengths of the names and phone numbers, as well as the average number of phone numbers per contact.*/
}
