package Backbase.ComputerDatabase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;


public class ComputerDatabaseTests {
	WebDriver driver;
	HomePage homePage;
	EditComputer editComputer;
	AddComputer addComputer;
	String totalComputers;

	// Initialize properties and start browser
	@BeforeClass
	public void openComputerDatabase() {
		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		this.driver = driver;
		homePage = new HomePage();
		editComputer = new EditComputer();
		addComputer = new AddComputer();
		
		driver.get("http://computer-database.herokuapp.com/computers");
		PageFactory.initElements(driver, homePage);
		PageFactory.initElements(driver, editComputer);
		PageFactory.initElements(driver, addComputer);
	}
	
	// Close browser
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
	
	// Test cases are primarily of CRUD operations
	
	@Test (description = "Verify to add a computer", priority = 1)
	public void addTest() {
		SoftAssert softAssert = new SoftAssert();
		String availableComputers = homePage.getTotalComputers();
		homePage.clickAddComputer();
		addComputer.enterComputerDetails("testAutomationComp", "1991-02-20", "", "IBM");
		addComputer.createComputer();
		String updatedComputers = homePage.getTotalComputers();		
		softAssert.assertNotEquals(availableComputers, updatedComputers);
		softAssert.assertEquals("Done! Computer " + "testAutomationComp" + " has been created", homePage.getCreateCompMsg());
		softAssert.assertAll();
	}
	
	@Test (description = "Verify to filter added computer", priority = 2)
	public void filterTest() {
		homePage.filterByName("testAutomationComp");
		homePage.clickFilteredElement("testAutomationComp");
		Assert.assertEquals("testAutomationComp", editComputer.getComputerName());
		homePage.cancelFilter();
	}
	
	@Test (description = "Verify to edit filtered computer", priority = 3)
	public void editTest() {
		homePage.filterByName("testAutomationComp");
		homePage.clickFilteredElement("testAutomationComp");
		editComputer.editDetails("testComputer", "", "", "Apple");
		editComputer.saveComputer();
		Assert.assertEquals("Done! Computer testComputer has been updated", homePage.getEditComputerMsg());		 
	}
	
	@Test (description = "Check when search element not available", priority = 4)
	public void checkNonAvailableItem() {
		homePage.filterByName("testAutomationComp");
		Assert.assertEquals("Nothing to display", homePage.getNotAvailableMsg());
		homePage.cancelFilter();
	}
	
	@Test (description = "Verify to delete the computer", priority = 5)
	public void deleteTest() {
		SoftAssert softAssert = new SoftAssert();
		String availableComputers = homePage.getTotalComputers();
		homePage.filterByName("testComputer");
		homePage.clickFilteredElement("testComputer");
		editComputer.deleteComputer();
		String updatedComputers = homePage.getTotalComputers();		
		softAssert.assertNotEquals(availableComputers, updatedComputers);
		softAssert.assertEquals("Done! Computer has been deleted", homePage.getDeleteComputerMsg());
	}
	
}
