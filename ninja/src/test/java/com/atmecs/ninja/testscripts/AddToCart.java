package com.atmecs.ninja.testscripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.atmecs.ninja.constants.ConstantPaths;
import com.atmecs.ninja.helpers.PageActions;
import com.atmecs.ninja.helpers.ReadExcelData;
import com.atmecs.ninja.helpers.ReadPropertiesFile;
import com.atmecs.ninja.helpers.ValidateTestResult;
import com.atmecs.ninja.testbase.InvokeBrowser;
import org.openqa.selenium.Keys;

public class AddToCart extends InvokeBrowser {
	ReadPropertiesFile read = new ReadPropertiesFile();

	String Xpath;

//To click on search
	@Test(priority = 2)
	public void addProduct() {
		Xpath = read.readPropertiesFile("loc.validate.title.xpath", ConstantPaths.LOCATORS_FILE);
		String actualtext = driver.findElement(By.xpath(Xpath)).getText();
		String exptext = read.readPropertiesFile("exptext", ConstantPaths.LOCATORS_FILE);
		ValidateTestResult.validateData(actualtext, exptext, "No match");
		log.info("Match found");

		Xpath = read.readPropertiesFile("loc.search.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);
		log.info("Clicked on the serach");

	}
//select product using data provider

	@Test(priority = 3, dataProvider = "data", dataProviderClass = ReadExcelData.class)
	public void enterData(String[] in) {
		Xpath = read.readPropertiesFile("loc.search.xpath", ConstantPaths.DATA_FILE);
		driver.findElement(By.xpath(Xpath)).sendKeys(in[0]);

		Xpath = read.readPropertiesFile("loc.button.search.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

		// driver.findElement(By.xpath("loc.search.xpath")).sendKeys(Keys.ENTER);
		Xpath = read.readPropertiesFile("loc.click.addtocart.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

		Xpath = read.readPropertiesFile("loc.search.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

		driver.findElement(By.xpath(Xpath)).sendKeys(Keys.CLEAR);

		String actvalue = read.readPropertiesFile("loc.validate.producttext.xpath", ConstantPaths.LOCATORS_FILE);
		String expvalue = read.readPropertiesFile("expvalue", ConstantPaths.LOCATORS_FILE);
		ValidateTestResult.validateData(actvalue, expvalue, "No match");
		log.info("Match found");
	}

}
