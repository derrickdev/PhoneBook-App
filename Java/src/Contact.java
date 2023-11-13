import java.util.ArrayList;
import java.util.List;

class Contact {
    private String firstName;
    private String lastName;
    private List<PhoneNumber> phoneNumbers;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = new ArrayList<>();
    }

    public void addPhoneNumber(String number, PhoneNumberCategory category) {

        PhoneNumber phoneNumber = new PhoneNumber(number, category);
        phoneNumbers.add(phoneNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}

