package com.bank.pages;

import com.bank.utility.Utility;
import org.openqa.selenium.By;

/**
 * Created by Jay
 */
public class BankManagerLoginPage extends Utility {

    By addCustomerTab = By.xpath("(//button[normalize-space()='Add Customer'])[1]");
    By openAccountTab = By.xpath("(//button[normalize-space()='Open Account'])[1]");
    By customersTab = By.xpath("(//button[normalize-space()='Customers'])[1]");

    public void clickOnAddCustomerTab(){
        clickOnElement(addCustomerTab);
    }

    public void clickOnOpenAccountTab(){
        clickOnElement(openAccountTab);
    }

    public void clickOnCustomerTab(){
        clickOnElement(customersTab);
    }
}
