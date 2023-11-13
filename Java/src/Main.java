import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhonebookApplication phonebook = new PhonebookApplication();
        phonebook.loadContacts("data.txt");

        Scanner scanner = new Scanner(System.in);
      try{
          int action = 0;
          while (action != 3) {
              System.out.println("*** Phonebook Search Application ***");
              System.out.println("Choose your action:");
              System.out.println("1: Find by name");
              System.out.println("2: Find by number");
              System.out.println("3: Exit");
              System.out.print("> ");
              action = scanner.nextInt();
              scanner.nextLine(); // Clear the newline character from the input buffer

              if (action == 1) {
                  System.out.println("Enter first or last name:");
                  System.out.print("> ");
                  String keyword = scanner.nextLine();
                  phonebook.searchByName(keyword);
              } else if (action == 2) {
                  System.out.println("Enter the phone number:");
                  System.out.print("> ");
                  String number = scanner.nextLine();
                  phonebook.searchByNumber(number);
              }
          }
      }catch (InputMismatchException e) {
          System.out.println("Wrong input ,please enter a matching number");
      }


        System.out.println("*** Exit ***");
          scanner.close();
    }

}
