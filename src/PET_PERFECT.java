import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PET_PERFECT extends PetPerfect_Parameters {

	@BeforeTest
	public void BeforeTest() {
		driver.get(url);

	}

	@Test(priority = 1)
	public void search_for_dog() throws InterruptedException {

		driver.get(dogs_page);
//create a list to select the products randomly 
		List<WebElement> Products = driver.findElements(By.className("grid-product__meta"));
		int productRandInd = rand.nextInt(Products.size());
		Products.get(productRandInd).click();

//  Create a list to store all available options for sizes and colors of the products

		List<WebElement> colors = driver.findElements(By.cssSelector("#ProductSelect-option-0 label"));

		List<WebElement> sizes = driver.findElements(By.cssSelector("#ProductSelect-option-1 label"));

		int colorRandInd = rand.nextInt(colors.size());

		int sizeRandInd = rand.nextInt(sizes.size());

		// select color & size of products randomly

		colors.get(colorRandInd).click();
		sizes.get(sizeRandInd).click();
		driver.findElement(By.className("btn__text")).click();
	}

	@Test(priority = 2)
	public void verify_checkout_process_and_screenshot_payment_page() throws IOException {
		// Starting the checkout process by clicking on the 'Checkout' button
		driver.get(Cart_Url);
		WebElement checkout_Button = driver.findElement(By.className("cart__checkout"));
		checkout_Button.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		// Declaring a WebElement to fill the required information
		WebElement email_field = driver.findElement(By.id("email"));
		WebElement last_field = driver.findElement(By.name("lastName"));
		WebElement address_field = driver.findElement(By.name("address1"));
		WebElement city_field = driver.findElement(By.name("city"));
		WebElement postal_field = driver.findElement(By.name("postalCode"));
		WebElement submit_button = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/div/main/form/div[1]/div/div[2]/div[2]/div[1]/button"));
		// Filling the information
		email_field.sendKeys("asdfsfa@gmail.com");
		last_field.sendKeys("johnson");
		address_field.sendKeys("Groove Street");
		city_field.sendKeys("San Andreas");
		postal_field.sendKeys("95249");

		// submit
		submit_button.click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/div[1]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/div/main/form/div[1]/div/div/div[2]/div[1]/button")));
		WebElement continue_to_payment = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/div/main/form/div[1]/div/div/div[2]/div[1]/button"));
		continue_to_payment.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/div[1]/div[1]/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/div/main/div/form/div[1]/div/div[1]/section/div/div[1]/p")));

		// steps to take a screenshot

		TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
		String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		File screenshotFile = new File("D:\\projects\\Pet-Store\\screenshot_" + dateTimeString + ".png");
		FileUtils.copyFile(screenshotDriver.getScreenshotAs(OutputType.FILE), screenshotFile);
		// Performing an assertion to ensure whether the screenshot was taken or not
		Assert.assertTrue(driver.findElement(By.id("step-section-primary-header")).isDisplayed());
	}

	@Test(priority = 3)
	public void search_for_cat() {
		//Selecting the Search icon
		WebElement searchIcon = driver.findElement(By.cssSelector("#AccessibleNav > li:nth-child(4) > a"));
		//Click Search icon
		searchIcon.click();
		//Select the search input field
		WebElement searchBar = driver.findElement(By.className("input-group-field"));
		//Send the word Cat
		searchBar.sendKeys("cat" + Keys.ENTER);
		//Get title of each  element
		List<WebElement> searchResulTitles = driver.findElements(By.className("grid-product__title"));
		//Define a boolean flag to  track the condition
		boolean flagIfTitleContainsCat = false;
//Creating a for loop to pass across all titles
		for (int i = 0; i < searchResulTitles.size(); i++) {
			//Define a string called "title" to store the formatted title
			String title = searchResulTitles.get(i).getText().toLowerCase();
			System.out.println(title);
			//Building an if condition structure to verify the existence of the word cat then apply changes on the boolean flag, using the method "contains"
			if (title.contains("cat")) {
				flagIfTitleContainsCat = true;
			} else {
				flagIfTitleContainsCat = false;
				break;
			}
		}
		//Doing an assertion for the flag to be true, which means all titles contains the word "cat"
		Assert.assertEquals(flagIfTitleContainsCat, true);
	}

	@Test(priority = 4)
	public void verify_selecting_random_animal() {
		 // Navigating to the specified URL
		driver.get(url);
	    // Finding a list of pet types on the webpage
		List<WebElement> pet_type = driver.findElement(By.cssSelector("div.grid:nth-child(2)"))
				.findElements(By.className("collection-grid__item-link"));
		// Generating a random index to select a pet type
		int random_pet_type = rand.nextInt(0, pet_type.size() - 2);
		 // Clicking on a randomly selected pet type
		pet_type.get(random_pet_type).click();
	    // Finding the grid of pet items
		WebElement items_grid = driver.findElement(By.className("grid-collage"));
		 // Finding all the pet items in the grid
		List<WebElement> pet_items = items_grid.findElements(By.className("product--image"));
		// Asserting that the number of pet items is either 10 or 12
		Assert.assertTrue(pet_items.size() == 10 || pet_items.size() == 12,
				"The actual number of items is not as expected");
	}

	@Test(priority = 5)
	public void verify_filling_contact_form_and_submit() {
		
	    // Navigating to the required information
		
		driver.get(CONTACT_URL);
		driver.findElement(By.id("ContactFormName")).sendKeys("Carl");
		driver.findElement(By.id("ContactFormEmail")).sendKeys("a@vmb.com");
		driver.findElement(By.id("ContactFormPhone")).sendKeys("55292500611");
		driver.findElement(By.id("ContactFormMessage")).sendKeys("I want my order wrapped as a gift");
	   
		// Clicking the submit button on the Contact Form
		driver.findElement(By.className("btn")).click();
		
	    // Waiting for the success message element to be visible
		WebElement success_msg = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contact_form\"]/p")));
	    
		// Asserting that the success message matches the expected message
		Assert.assertEquals(EXPECTED_MSG, success_msg.getText());
	}

	@AfterTest

	public void AfterTest() {
	    // Asserting all the assertions made in the test
		Assert.assertAll();
		
	    // Quitting the driver
		 driver.quit();

	}

}
