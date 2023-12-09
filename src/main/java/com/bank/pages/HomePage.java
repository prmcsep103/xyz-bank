package com.bank.pages;

import com.bank.utility.Utility;
import org.openqa.selenium.By;

/**
 * Created by Jay
 */
public class HomePage extends Utility {

    By homeButtonLink = By.xpath("(//button[normalize-space()='Home'])[1]");
    By customerLoginTab = By.xpath("//button[contains(text(),'Customer Login')]");
    By bankManagerLoginTab = By.xpath("//button[normalize-space()='Bank Manager Login']");

    public void clickOnHomeBtn(){
        clickOnElement(homeButtonLink);
    }

    public void clickOnCustomerLoginTab(){
        clickOnElement(customerLoginTab);
    }

    public void clickOnBankManagerLoginTab()  {
        waitUntilElementToBeClickable(bankManagerLoginTab, 10);
        clickOnElement(bankManagerLoginTab);
    }


}
