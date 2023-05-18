import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
public class PetPerfect_Parameters {
	
	
	
	WebDriver driver = new EdgeDriver();
	Random rand= new Random();
	SoftAssert Assert = new SoftAssert();
	static String url = "https://ecom-pet-store.myshopify.com/";
	static String dogs_page = "https://ecom-pet-store.myshopify.com/collections/frontpage";
	WebDriverWait webDriverWait =new WebDriverWait(driver, Duration.ofMinutes(2));
	String Cart_Url="https://ecom-pet-store.myshopify.com/cart";
	 static String CONTACT_URL = "https://ecom-pet-store.myshopify.com/pages/contact";
	 static String EXPECTED_MSG = "Thanks for contacting us. We'll get back to you as soon as possible.";
}
