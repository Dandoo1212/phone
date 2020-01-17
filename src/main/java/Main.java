import com.britenet.converter.PeselFacade;
import com.britenet.service.PhoneNumberFacade;

public class Main {

  public static void main(String[] args) {
      App app = new App(new PeselFacade(), new PhoneNumberFacade());
      app.runApp();
  }
}
