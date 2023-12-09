package com.bank.testsuite;

import com.bank.pages.*;
import com.bank.testbase.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by Jay
 */
public class BankTest extends BaseTest {

    static String firstName = null;
    static String lastName = null;

    HomePage homePage = new HomePage();
    BankManagerLoginPage bml = new BankManagerLoginPage();
    AddCustomerPage ac = new AddCustomerPage();
    OpenAccountPage oap = new OpenAccountPage();
    CustomerLoginPage cl = new CustomerLoginPage();
    AccountPage acc = new AccountPage();
    CustomersPage cp = new CustomersPage();

    @BeforeSuite
    public void createDetails() {
        firstName = getAlphaNumericString(5);
        lastName = getAlphaNumericString(5);
    }

    @Test
    public void bankManagerShouldAddCustomerSuccessfully() {
        homePage.clickOnBankManagerLoginTab();
        bml.clickOnAddCustomerTab();
        ac.enterFirstName(firstName);
        ac.enterLastName(lastName);
        ac.enterPostcode("HA2 6JT");
        ac.clickOnAddCustomerButton();
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Customer added successfully"));
        alert.accept();
    }

    @Test
    public void bankManagerShouldOpenAccountSuccessfully() {
        bankManagerShouldAddCustomerSuccessfully();
        bml.clickOnOpenAccountTab();
        oap.selectCustomerName(firstName + " " + lastName);
        oap.selectCurrency("Pound");
        oap.clickOnProcessButton();
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Account created successfully "));
        alert.accept();
    }

    @Test
    public void customerShouldLoginAndLogoutSuccessfully() {
        bankManagerShouldOpenAccountSuccessfully();
        homePage.clickOnHomeBtn();
        homePage.clickOnCustomerLoginTab();
        cl.selectYourName(firstName + " " + lastName);
        cl.clickOnLoginButton();
        Assert.assertTrue(acc.isLogoutButtonDisplayed());
        acc.clickOnLogoutButton();
        Assert.assertTrue(cl.isYourNameLabelDisplayed());
    }

    @Test
    public void customerShouldDepositMoneySuccessfully() {
        bankManagerShouldOpenAccountSuccessfully();
        homePage.clickOnHomeBtn();
        homePage.clickOnCustomerLoginTab();
        cl.selectYourName(firstName + " " + lastName);
        cl.clickOnLoginButton();
        acc.clickOnDepositTab();
        acc.enterAmount(100);
        acc.clickOnDepositWithdrawButton();
        Assert.assertEquals(acc.getErrorSuccessMessage(), "Deposit Successful");
    }

    @Test
    public void customerShouldWithdrawMoneySuccessfully() {
        customerShouldDepositMoneySuccessfully();
        homePage.clickOnHomeBtn();
        homePage.clickOnCustomerLoginTab();
        cl.selectYourName(firstName + " " + lastName);
        cl.clickOnLoginButton();
        acc.clickOnWithDrawlTab();
        acc.enterAmount(50);
        acc.clickOnDepositWithdrawButton();
        Assert.assertEquals(acc.getErrorSuccessMessage(), "Transaction successful");
    }

    @Test
    public void bankManagerShouldDeleteCustomerSuccessfully() {
        bankManagerShouldOpenAccountSuccessfully();
        homePage.clickOnHomeBtn();
        homePage.clickOnBankManagerLoginTab();
        bml.clickOnCustomerTab();
        cp.searchCustomerByName(firstName);
        cp.deleteCustomer();
    }
}
