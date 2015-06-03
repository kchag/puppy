package place_order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class login {
	static WebDriver driver1;
	
	public static void setup() {
		{
		    driver1 = new FirefoxDriver();
			String baseUrl = "https://www.lendsecure1.com";
			
			driver1.get(baseUrl);
			driver1.findElement(By.cssSelector("a[href=\"/login\"]")).click();
			driver1.findElement(By.name("email")).sendKeys("chq473@gmail.com");
			driver1.findElement(By.name("password")).sendKeys("Summer123");
			driver1.findElement(By.name("login")).click();
		}

	}

}
