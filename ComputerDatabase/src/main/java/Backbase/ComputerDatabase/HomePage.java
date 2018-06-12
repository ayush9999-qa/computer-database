package Backbase.ComputerDatabase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	@FindBy(xpath = "//h1/a")
	private WebElement title;
	
	@FindBy(xpath = "//section[@id='main']/h1")
	private WebElement totalComputers;
	
	@FindBy(id = "add")
	private WebElement addComputer;
	
	@FindBy(id = "searchsubmit")
	private WebElement filterByName;
	
	@FindBy(id = "searchbox")
	private WebElement searchBox;
	
	@FindBy(xpath = "//table/tbody/tr")
	private List<WebElement> containerElements;
	
	@FindBy(xpath = "//li[@class='current']/a")
	private WebElement currentList;	
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement createComputerMsg;
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement deleteComputerMsg;
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement editComputerMsg;
	
	@FindBy(css = "div.well")
	private WebElement notAvailableMsg;
	
	public void clickAddComputer() {
		addComputer.click();
	}
	
	public void filterByName(String computerName) {
		searchBox.sendKeys(computerName);
		filterByName.click();		
	}
	
	public void clickFilteredElement(String computerName) {
		containerElements.get(containerElements.size()-1).findElement(By.linkText(computerName)).click();
	}
	
	public void cancelFilter() {
		title.click();
	}
	
	public String getTotalComputers() {
		String computers = totalComputers.getText();
		return computers.split("\\s+")[0];
	}
	
	public String getDeleteComputerMsg() {
		return deleteComputerMsg.getText();
	}
	
	public String getEditComputerMsg() {
		return editComputerMsg.getText();
	}
	
	public String getNotAvailableMsg() {
		return notAvailableMsg.getText();
	}
	
	public String getCreateCompMsg() {
		return createComputerMsg.getText();
	}

}
