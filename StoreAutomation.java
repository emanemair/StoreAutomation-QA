package MyPackage;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;


public class StoreAutomation {

	String URL = "https://www.automationteststore.com/"; 
	WebDriver driver = new ChromeDriver(); 
	String[] first_name = {"Reem" , "Ahmad" , "Omar"}; 
	String [] last_name = {"Faisal" , "Mahmoud" , "Yousef"}; 
	Random rand = new Random() ; 
	String PublicUserName = ""; 
	String PublicPass = ""; 
	
	
 	@BeforeTest 
	public void MySetup() {
		
		driver.get(URL);
		driver.manage().window().maximize(); 
		
	} 
	
 	// LoginAndRegist
 	/*
 	 * This Test Navigate to The Login and Registration Page 
 	 * 
 	 * 
 	 */
	
	@Test (priority = 1 )
	public void LoginAndRegist() {
		
		WebElement LoginLink = driver.findElement(By.linkText("Login or register")); 
		LoginLink.click(); // Open the login and registration page 
		
		WebElement  ContinueRegistration = driver.findElement(By.xpath("//button[@title='Continue']")); 
		ContinueRegistration.click(); // Open the registration page 

	} // End of LoginAndRegist Test
	
	// Registration Test 
	/*
	 * - Fills out the registration form with random names, email, and address details.
	   - Selects a country and state from dropdown menus.
       - Generates a random username and password and submits the registration form.
	 */

	@Test (priority = 2 )
	public  void  Registration() throws InterruptedException {
		
		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname")); 
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname")) ; 
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement Address = driver.findElement(By.id("AccountFrm_address_1")); 
		WebElement City = driver.findElement(By.id("AccountFrm_city")); 
		WebElement PostCode = driver.findElement(By.id("AccountFrm_postcode")); 
		WebElement Country = driver.findElement(By.id("AccountFrm_country_id")); 
		WebElement RegionAndState = driver.findElement(By.id("AccountFrm_zone_id")); 
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname")); 
		WebElement Password = driver.findElement(By.id("AccountFrm_password")); 
		WebElement ConfirmPassword = driver.findElement(By.id("AccountFrm_confirm")); 

		
		WebElement AgreeButton = driver.findElement(By.id("AccountFrm_agree")); 
		WebElement Submit = driver.findElement(By.xpath("//button[@title='Continue']")); 
		String LogoutLink ="https://automationteststore.com/index.php?rt=account/logout"; 
		// Define Variable 
		
		int name_index = rand.nextInt(first_name.length) ; // will generate a random number 0 - 4 
		int RandNumForEmail = rand.nextInt(2547) ; 
		
		String FirstName = first_name[name_index]; // assign the first name 
		String LastName = last_name[name_index]; // assign the last name 
		String UserName =  FirstName+ LastName ; 
		PublicUserName = UserName; 
		String Email = UserName + String.valueOf(RandNumForEmail)+"@gmail.com" ; 
		FirstNameInput.sendKeys(FirstName);
		LastNameInput.sendKeys(LastName); 
		EmailInput.sendKeys(Email);
		Address.sendKeys("Random Address");
		PostCode.sendKeys("111011"); 
		City.sendKeys("Amman") ; 
		
		Select CountrySelect = new Select(Country); 
		CountrySelect.selectByValue("108");
		Thread.sleep(1000);
		Select RegionAndStateSelect = new Select(RegionAndState); 
		RegionAndStateSelect.selectByVisibleText("'Amman");
		LoginNameInput.sendKeys(UserName); 
		/*
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		String LowerCase = "abcdefghijklmnopqrstuvwxyz"; 
		String Numbers = "0123456789"; 
		String Symbols = "!@#$%^&*";
		*/ 
		String user_password = "pass@qw"; 
		PublicPass = user_password; 
		Password.sendKeys(user_password);
		ConfirmPassword.sendKeys(user_password);
		AgreeButton.click();
		Thread.sleep(1000); 
		Submit.click(); 

		driver.get(LogoutLink);
	
		
	} // End of Registration Test 
	
	
	// LoginTest 
	/*
	 * This test navigates to the login page and uses the credentials generated during registration to log into the account.
	 */
	
	@Test (priority = 3 )
	public void LoginTest() throws InterruptedException {
		
		String LoginUrl = "https://automationteststore.com/index.php?rt=account/login"; 
		driver.get(LoginUrl);
		WebElement UserNameInput= driver.findElement(By.id("loginFrm_loginname"));
		WebElement PasswordInput = driver.findElement(By.id("loginFrm_password")); 
		Thread.sleep(1000);
		WebElement LoginButton = driver.findElement(By.xpath("//button[@title='Login']")); 
	 
		UserNameInput.sendKeys(PublicUserName); 
		PasswordInput.sendKeys(PublicPass); 
		LoginButton.click(); 
		
	}// End of LoginTest 
	
	
	
	//Select Men's Section 
	/*
	 * Navigates to the men's section of the store.
	 */
	@Test (priority = 4)
	public void SelectMenSection() {
		
		String MenSectionUrl = "https://automationteststore.com/index.php?rt=product/category&path=58" ;
		driver.get(MenSectionUrl);
		
	}// End of Men's Section Test 
	
	
	// SelectRandomMainProductCate
	/*
	 * Finds and clicks a random product category from the list on the page.
	 */
	@Test (priority = 5)
	public void SelectRandomMainProductCate ()  {
		
		List<WebElement> MainProductsList = driver.findElements(By.cssSelector(".col-md-2.col-sm-2.col-xs-6.align_center"));
		int RandProductIndex = rand.nextInt(MainProductsList.size()); 
		MainProductsList.get(RandProductIndex).click();
		
	}// End of SelectRandomMainProductCate Test 
	
	
	// GetRandomSubProduct Test
	/*
	 * Finds and clicks a random sub-product within the selected category.
	 */
	@Test (priority = 6 )
	public void GetRandomSubProduct() throws InterruptedException{
		
		List<WebElement> SubProductList = driver.findElements(By.cssSelector(".thumbnails.grid.row.list-inline"));
		if(SubProductList.size() == 1  ) {
			WebElement SubProduct = driver.findElement(By.className("prdocutname"));
			SubProduct.click();
		}else if (SubProductList.size() > 1 ) {
			
			List<WebElement> SubProducts = driver.findElements(By.className("productname")); 
			int RandomSubProductIndex = rand.nextInt(SubProducts.size()); 
			SubProducts.get(RandomSubProductIndex).click(); 
			
		}
	
		Thread.sleep(1000);

	}// end of  GetRandomSubProduct Test
	
	
	
	// CheckProductAvailabilityAndAddToCart Test 
	/*
	   - Checks if the product available in the stock by checking of the  cart button is available on the product page.
	   - If not, it navigates back to the men's section and repeats product selection until the product is available 
	 */
	@Test (priority = 8 )
	public void CheckProductAvailability() throws InterruptedException {
		
		
		boolean hasAnchor = driver.findElements(By.cssSelector("a.cart")).size() > 0 ; 
		
		while(!hasAnchor) {

			Thread.sleep(1000); 
			SelectMenSection(); 
			SelectRandomMainProductCate(); 
			GetRandomSubProduct(); 
			
			hasAnchor = driver.findElements(By.cssSelector("a.cart")).size() > 0;

		}
	
		
	} // End of CheckProductAvailabilityAndAddToCart 
	
	
	
	// AddToCart 
	/* 
	 * Add product to the cart by clicking add to cart button 
	 */
	@Test (priority = 10 )
	public void AddToCart()
	{
		WebElement AddToCart = driver.findElement(By.cssSelector("a.cart")); 
		AddToCart.click(); 
	}// End of AddToCart Test 
	
	
	
	// EnterRandomQuantity 
	/* 
	   - Generates a random number for the product quantity (between 2 and 10).
	   - Inputs this number into the quantity field on the product page.
	 */
	@Test (priority = 9 )
	public void EnterRandomQuantity() throws InterruptedException {
		
		int RandomProductQuantity =  rand.nextInt(9) + 2 ; 
		WebElement QuantityInput = driver.findElement(By.id("product_quantity")); 
		QuantityInput.clear();
		QuantityInput.sendKeys(String.valueOf(RandomProductQuantity));
		Thread.sleep(1000);  
		
		
	}// End of EnterRandomQuantity 
	
	
	
	// CheckoutAndConfirmOrder Test 
	/*
	 * This test clicks the checkout button and confirms the order, completing the purchase process.
	 */
	@Test (priority = 11 )
	public void CheckoutAndConfirmOrder(){
		
		WebElement CheckOutButtoon = driver.findElement(By.id("cart_checkout1")); 
		CheckOutButtoon.click(); 
		WebElement ConfirmOrderButton = driver.findElement(By.id("checkout_btn"));
		ConfirmOrderButton.click(); 
		
	} // End of CheckoutAndConfirmOrder
	
	
} // End of StoreAutomation 
	




