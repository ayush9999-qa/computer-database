package Backbase.ComputerDatabase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditComputer {
	@FindBy(id = "name")
	private WebElement computerName;
	
	@FindBy(id = "introduced")
	private WebElement introducedDate;
	
	@FindBy(id = "discontinued")
	private WebElement discontinuedDate;
	
	@FindBy(id = "company")
	private WebElement companyDropdown;
	
	@FindBy(css = "input.btn.primary")
	private WebElement saveComputer;
	
	@FindBy(css = "input.btn")
	private WebElement cancel;

	@FindBy(css = "input.btn.danger")
	private WebElement delete;
	
	public void editDetails(String compName, String introDate, String disDate, String comp) {
		computerName.clear();
		computerName.sendKeys(compName);
		introducedDate.clear();
		introducedDate.sendKeys(introDate);
		discontinuedDate.clear();
		discontinuedDate.sendKeys(disDate);
		companyDropdown.sendKeys(comp);
	}
	
	public void saveComputer() {
		saveComputer.click();
	}
	
	public void cancel() {
		cancel.click();
	}
	
	public void deleteComputer() {
		delete.click();
	}
	
	public String getComputerName() {
		return computerName.getAttribute("value");
	}
}
