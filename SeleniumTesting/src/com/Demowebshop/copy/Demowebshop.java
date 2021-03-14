package com.Demowebshop.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Demowebshop {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//1
		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		FirefoxDriver driver= new FirefoxDriver();
        
		driver.navigate().to("https://demowebshop.tricentis.com/");
		
		driver.findElement(By.className("ico-login")).click();
		
		/*String Expected_WelcomePleaseSignIn = "Welcome, Please Sign In!";
		
		String Welcome=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[1]/h1")).getText();
		
		if(Welcome.equals(Expected_WelcomePleaseSignIn))
		{
			System.out.println("Welcome on Login Successfully");
		}
		else
		{
			System.out.println("You are not on Login Page");
		}*/
		
		//4
		String Email = "atest@gmail.com";
		String Password ="123456";
		
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys(Password);
		//driver.findElement(By.className("button-1 login-button")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();

		//5
		/*String Expected_ValidUserAccountId = "atest@gmail.com";
		String ValidUserAccountId = driver.findElement(By.className("account")).getText();
		
		if (ValidUserAccountId.equals(Expected_ValidUserAccountId))
		{
			System.out.println("ValidAccountID");
		}
		else
		{
			System.out.println("Not ValidAccountID");
		}*/

	//6
		driver.findElement(By.className("cart-label")).click();
		driver.findElement(By.name("removefromcart")).click();
		driver.findElement(By.name("updatecart")).click();
	//7                              /html/body/div[4]/div[1]/div[2]/ul[1]/li[1]/a
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[1]/a")).click();
	//8
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div[1]/a/img")).click();
	//9
	String Price = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div/form/div/div[1]/div[2]/div[6]/div[2]/span")).getText();
	System.out.println("The Price of Book is" + Price);
	WebElement Quantity = driver.findElement(By.id("addtocart_13_EnteredQuantity"));
	Quantity.clear();
	Quantity.sendKeys("4");
	
	//10
	driver.findElement(By.id("add-to-cart-button-13")).click();
	
	//11
	/*Alert alt = driver.switchTo().alert();
	String alertmessage = alt.getText();
	System.out.println("The Alert message is"+ alertmessage );*/
	//System.out.println(driver.findElement(By.xpath("/html/body/div[3]/p")).getText());
	
	//12
driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[3]/a/span[1]")).click();
//   /html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/form
//driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/form")));

String SubTotal = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/form/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[2]/span/span")).getText();
System.out.println("The SubTotal is"+ SubTotal);

//13

driver.findElement(By.id("termsofservice")).click();
driver.findElement(By.id("checkout"));

//14

WebElement Address = driver.findElement(By.id("billing-address-select"));

Select selection = new Select(Address);
selection.selectByVisibleText("New Address");

//15

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
}

