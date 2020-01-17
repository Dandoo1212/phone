import com.britenet.converter.PeselFacade;
import com.britenet.converter.PeselInfo;
import com.britenet.service.PhoneNumber;
import com.britenet.service.PhoneNumberFacade;

import java.util.Scanner;

public class App {

  private PeselFacade peselFacade;
  private PhoneNumberFacade phoneNumberFacade;
  private Scanner scanner;

  public App(PeselFacade peselFacade, PhoneNumberFacade phoneNumberFacade) {
    this.peselFacade = peselFacade;
    this.phoneNumberFacade = phoneNumberFacade;
    this.scanner = new Scanner(System.in);
  }

  public void runApp() {

    String choice = showMenu();
    runFacade(choice);
  }

  private void runFacade(String choice) {
    switch (choice) {
      case "1":
        validatePesel();
        break;
      case "2":
        validateNumber();
        break;
      default:
        System.out.println("Wrong choice");
    }
  }

  private String showMenu() {
    System.out.println("Choose one:");
    System.out.println("1. Validate Pesel number");
    System.out.println("2. Validate Phone number");
    return scanner.nextLine();
  }

  private void validateNumber() {
    System.out.println("Provide phone number");
    String number = scanner.nextLine();
    PhoneNumber phoneNumber = phoneNumberFacade.formatPhoneNumber(number);
    System.out.println(phoneNumber);
  }

  private void validatePesel() {
    System.out.println("Provide pesel");
    String pesel = scanner.nextLine();
    PeselInfo peselInfo = peselFacade.extractInfo(pesel);
    System.out.println(peselInfo);
  }
}
