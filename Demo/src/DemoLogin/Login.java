package DemoLogin;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LoginSettings.Settings;
import LoginSettings.TestClass;


@Listeners(TestClass.class)
public class Login {
	
	static WebDriver driver;
	static String Screenshotpath;
	
	static String sEmail;
	static String sPwd;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("BeforeTest");
		Settings.read();
		
		Screenshotpath =Settings.getScreenshotPath();
		sEmail =Settings.getUserName();
		sPwd =Settings.getPassword();
	}
	//Login page
		@Test
		@Parameters({"sDriverPath", "sDriver" })
		public static void Login(String sDriverPath, String sDriver) throws Exception {
			
			Settings.read();
			String sURL = Settings.getsURL();
			
			
			if(sDriver.equalsIgnoreCase("webdriver.chrome.driver"))
			{
				System.setProperty(sDriver, sDriverPath);
				driver= new ChromeDriver();
			}
			else if(sDriver.equalsIgnoreCase("webdriver.gecko.driver"))
			{
				System.setProperty(sDriver, sDriverPath);
				driver = new FirefoxDriver();
				
			}
			else if(sDriver.equalsIgnoreCase("webdriver.ie.driver"))
			{
			  System.setProperty(sDriver, sDriverPath);
			  driver= new InternetExplorerDriver();
			}
			else
			{
			  System.setProperty(sDriver, sDriverPath);
			  driver= new EdgeDriver();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			//Login Credentials
			driver.get(sURL);
			
		}
		@Test(priority=1)
		public void LoginButton() throws InterruptedException
		{
			//click on login button
			driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
			Thread.sleep(1000);
			
			//verify welcome text
			String data=driver.findElement(By.xpath("//h1[contains(.,'Welcome, Please Sign In!')]")).getText();
			System.out.println(data);
			
			if(data.equals("Welcome, Please Sign In!"))
			{
				System.out.println("welcome verify test is passed");
			}
			else
			{
				System.out.println("welcome verify test is failed");
			}
		}
		@Parameters({"Email", "Pwd" })
		@Test(priority=2)
		public void credentials(String Email, String Pwd) throws InterruptedException
		{
			//click on email text box
			driver.findElement(By.xpath("//input[@id='Email']")).click();
			
			//enter email
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(Email);
			
			//click on pwd text box
			driver.findElement(By.xpath("//input[@id='Password']")).click();
			
			//enter pwd
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Pwd);
			
			//click on login button
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			Thread.sleep(2000);
		}
		@Test(priority=3)
		public void validateUser()
		{
			//copy value of user id
			String userdata=driver.findElement(By.xpath("//a[contains(text(),'atest@gmail.com')]")).getText();
			System.out.println(userdata);
			
			if(userdata.contains("atest"))
			{
				System.out.println("user is login");
			}
			else
			{
				System.out.println("user is not login");
			}
		}
		@Test(priority=4)
		public void clearCart() throws InterruptedException
		{
			//click on shopping cart 
			driver.findElement(By.xpath("//li[@id='topcartlink']/a/span")).click();
			
			//get qty for cart
			String qty=driver.findElement(By.xpath("//li[@id='topcartlink']/a/span[2]")).getText();
			System.out.println(qty);
			
			//click on chekbox selected product
			driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
			
			//click on qty text box
			driver.findElement(By.xpath("//td[5]/input")).click();
			Thread.sleep(1000);
			
			//remove the value
			WebElement cartvalue=driver.findElement(By.xpath("//td[5]/input"));
			cartvalue.click();
			Thread.sleep(1000);
			cartvalue.clear();
			//cartvalue.sendKeys(Keys.CONTROL, +"a");
			//cartvalue.sendKeys(Keys.DELETE);
			
			//click on update cart
			driver.findElement(By.xpath("//input[@name='updatecart']")).click();
			Thread.sleep(2000);
			
			//verify qty for cart
			if(qty.equals("0"))
			{
				System.out.println("cart is clear");
			}
			else
			{
				System.out.println("cart is not clear");
			}
			
			
		}
		//5
		@Test(priority=5)
		public void ValidAccountId ()
		{
			String Expected_ValidUserAccountId = "atest@gmail.com";
			String ValidUserAccountId = driver.findElement(By.className("account")).getText();
			
			if (ValidUserAccountId.equals(Expected_ValidUserAccountId))
			{
				System.out.println("ValidAccountID");
			}
			else
			{
				System.out.println("Not ValidAccountID");
			}
		}
		
		//6
		@Test(priority=6)
		public void cartstatus()
		{
			driver.findElement(By.className("cart-label")).click();
			driver.findElement(By.name("removefromcart")).click();
			driver.findElement(By.name("updatecart")).click();
		}
		
		//7
		@Test(priority=7)
		public void selectbooks()
		{
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[1]/a")).click();
		}
		
		//8
		@Test(priority=8)
		public void selectbooksfromlist()
		{
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div[1]/a/img")).click();

		}
		
		//9
		@Test(priority=9)
		public void pricedetails()
		{
			String Price = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div/form/div/div[1]/div[2]/div[6]/div[2]/span")).getText();
			System.out.println("The Price of Book is" + Price);
			WebElement Quantity = driver.findElement(By.id("addtocart_13_EnteredQuantity"));
			Quantity.clear();
			Quantity.sendKeys("4");
			
		}
		
		//10
		@Test(priority=10)
		public void addcart()
		{
			driver.findElement(By.id("add-to-cart-button-13")).click();

		}
		
		//11
		@Test(priority=11)
		public void validatecart()
		{
			Alert alt = driver.switchTo().alert();
			String alertmessage = alt.getText();
			System.out.println("The Alert message is"+ alertmessage );
			//System.out.println(driver.findElement(By.xpath("/html/body/div[3]/p")).getText());
			
		}
		
		//12
		@Test(priority=12)
		public void clickshoppingcart()
		{
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[3]/a/span[1]")).click();
			String SubTotal = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/form/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[2]/span/span")).getText();
			System.out.println("The SubTotal is"+ SubTotal);
		}
		
		//13
		@Test(priority=13)
		public void checkout()
		{
			driver.findElement(By.id("termsofservice")).click();
			driver.findElement(By.id("checkout"));

		}
		
		//14
		@Test(priority=14)
		public void address()
		{
			WebElement Address = driver.findElement(By.id("billing-address-select"));

			Select selection = new Select(Address);
			selection.selectByVisibleText("New Address");

		}
		
		//15
		@Test(priority=15)
		public void filladdress()
		{

         driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Sneha");
         driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Mitkari");
         driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("atest@gmail.com");
         driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("Jayam Solutions Pvt Ltd");

         WebElement Country = driver.findElement(By.id("BillingNewAddress_CountryId"));
         Select selection1 = new Select(Country);
         selection1.selectByVisibleText("India");


         driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Risod");
         driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Sane Chowk");
         driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("444506");
         driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("7038764471");



		}
		@Test(priority=22)
		public void Logout() throws InterruptedException
		{
			//click on logout
			driver.findElement(By.xpath("//a[contains(@href, '/logout')]")).click();
			Thread.sleep(2000);
		}
		@AfterMethod
		public void tearDown(ITestResult result) {

			final String dir = System.getProperty("user.dir");
			String screenshotPath;
			//System.out.println("dir: " + dir);
			if (!result.getMethod().getMethodName().contains("Logout")) {
				if (ITestResult.FAILURE == result.getStatus()) {
					this.capturescreen(driver, result.getMethod().getMethodName(), "FAILURE");
					Reporter.setCurrentTestResult(result);

					Reporter.log("<br/>Failed to execute method: " + result.getMethod().getMethodName() + "<br/>");
					// Attach screenshot to report log
					screenshotPath = dir + "/" + Screenshotpath + "/ScreenshotsFailure/"
							+ result.getMethod().getMethodName() + ".png";

				} else {
					this.capturescreen(driver, result.getMethod().getMethodName(), "SUCCESS");
					Reporter.setCurrentTestResult(result);

					// Attach screenshot to report log
					screenshotPath = dir + "/" + Screenshotpath + "/ScreenshotsSuccess/"
							+ result.getMethod().getMethodName() + ".png";

				}

				String path = "<img src=\" " + screenshotPath + "\" alt=\"\"\"/\" />";
				// To add it in the report
				Reporter.log("<br/>");
				Reporter.log(path);
				
				
			}

		}

		public void capturescreen(WebDriver driver, String screenShotName, String status) {
			try {
				
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				if (status.equals("FAILURE")) {
					FileHandler.copy(scrFile,
							new File(Screenshotpath + "/ScreenshotsFailure/" + screenShotName + ".png"));
					Reporter.log(Screenshotpath + "/ScreenshotsFailure/" + screenShotName + ".png");
				} else if (status.equals("SUCCESS")) {
					FileHandler.copy(scrFile,
							new File(Screenshotpath + "./ScreenshotsSuccess/" + screenShotName + ".png"));

				}

				System.out.println("Printing screen shot taken for className " + screenShotName);

			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

}
