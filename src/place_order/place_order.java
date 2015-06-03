package place_order;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class place_order {

	Sheet s,t;
	WebDriver driver= new FirefoxDriver();
	String baseUrl = "http://beta.lendsecure1.com";
	
	@BeforeSuite
	public void setUp()
	{
		//login.setup();
		
		driver.get(baseUrl);
		driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
		driver.findElement(By.name("email")).sendKeys("chq473@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Summer123");
		driver.findElement(By.name("login")).click();
	}
	@Test
	public void place_order() throws Exception
	{
		FileInputStream fi = new FileInputStream("C:/BookA.xls");
		Workbook w = Workbook.getWorkbook(fi);
		s = w.getSheet(0);
		t= w.getSheet(1);
				
		for (int row=1; row < s.getRows(); row++)
		{
			String prop_address = s.getCell(0,row).getContents();
			System.out.println("Property Address "+ prop_address);
			Thread.sleep(1000);
			//driver.findElement(By.cssSelector("a[href=\"/placeOrder\"]")).click();
			driver.findElement(By.id("order_appraisal_navlink")).click();
			driver.findElement(By.name("propzip")).sendKeys("92108");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.id("propstate"));
			Thread.sleep(5000);
			WebElement prop_add= driver.findElement(By.id("propaddress1"));
			Thread.sleep(20000);
			//prop_add.sendKeys(prop_address);
			driver.findElement(By.name("propaddress1")).sendKeys(prop_address);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			//driver.findElement(By.cssSelector("input[value='N']")).click();
		    driver.findElement(By.name("submit")).click();
			String type_appraisal = s.getCell(1,row).getContents();
			System.out.println("Type of Appraisal "+ type_appraisal);
			driver.findElement(By.id(type_appraisal)).click();
			driver.findElement(By.id("date_requested")).click();
			driver.findElement(By.xpath("/html/body/div[4]/table/tbody/tr[3]/td[5]/a")).click();
			String prop_type1 = s.getCell(2,row).getContents();
			new Select (driver.findElement(By.id("prop_type"))).selectByVisibleText(prop_type1);
			String occupancy_status = s.getCell(3,row).getContents();
			new Select(driver.findElement(By.id("occstatus"))).selectByVisibleText(occupancy_status);
			String loan_type = s.getCell(4,row).getContents();
			new Select(driver.findElement(By.id("loantype"))).selectByVisibleText(loan_type);
			String loan_purpose = s.getCell(5,row).getContents();
			new Select(driver.findElement(By.id("loanpurpose"))).selectByVisibleText(loan_purpose);
			//WebElement upload = driver.findElement(By.id("sales_contract"));
			// driver.findElement(By.id("sales_contract")).sendKeys("C:\\Users\\kchaag\\Documents\\FHA Appraisal Checklist.pdf");
			driver.findElement(By.name("no_units")).sendKeys("1");
			driver.findElement(By.name("legal_descrip")).sendKeys("Test");
			driver.findElement(By.name("borrower")).sendKeys("Test Borrower");
			driver.findElement(By.name("borrower_phone")).sendKeys("3363363333");
			new Select(driver.findElement(By.id("fill_multiclient_select"))).selectByVisibleText("Client Pricing Parent");
			driver.findElement(By.id("bor_contact_info")).click();
			driver.findElement(By.id("bor_pay")).click();
			driver.findElement(By.name("submit")).click();
			driver.findElement(By.name("submit")).click();
			String oid = driver.findElement(By.xpath("/html/body/div[3]/table/tbody/tr/td/table/tbody/tr[5]/td[2]")).getText();
			System.out.println(oid);
			int column_no = s.getColumns();
			jxl.write.Label output = new jxl.write.Label(row,column_no+1,oid);
			//writerport(oid);
			System.out.println("Test Passed");
		}
			
	}
/*public void writerport(String t)
	{
		try
				{
			FileOutputStream fo = new FileOutputStream("C:/Book2.xls");
				
				WritableWorkbook book = Workbook.createWorkbook(fo);
				WritableSheet sheet = book.createSheet("output", 0);
				Label l = new Label (0,0,t);
				sheet.addCell(l);
				book.write();
				book.close();
	            }
	 catch(Exception e)
	{
		 e.printStackTrace();
	}
}*/
	

	@AfterSuite
	public void quit()
	{
		driver.findElement(By.cssSelector("a[href=\"/logout\"]")).click();
	}




}
