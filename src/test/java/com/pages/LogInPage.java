package com.pages;

import com.configuration.BaseDriver;
import com.configuration.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogInPage extends BaseTest {

    public LogInPage(BaseDriver baseDriver) {
        this.baseDriver = baseDriver;
        PageFactory.initElements(baseDriver.driver, this);
    }

    @FindBy(xpath = "//input[@id=\"username\"]")
    private WebElement usernameInputEle;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordInputEle;

    @FindBy(xpath = "//form[@id=\"loginForm\"]" +
            "//input[@type=\"submit\"]")
    private WebElement loginButtonClickEle;

    /**
     * The user navigate to product login page
     */
    //will Navigate to Product URL Page
    public void goToApp(String productURL) {
        try {
            baseDriver.gotoUrl(productURL);
        } catch (Exception e) {
            baseDriver.captureFailedScenarioScreenshot("UsernameFieldIssue");
            Assert.fail("appLogin Failed while navigated to " + productURL);
        }
    }

    /**
     * The user enter username
     */
    public LogInPage enterUsername(String userName) {
        try {
            baseDriver.waitForElementVisible(usernameInputEle);
            baseDriver.inputText(usernameInputEle,userName);
        } catch (Exception e) {
            baseDriver.captureFailedScenarioScreenshot("UsernameFieldIssue");
            Assert.fail("Username field is not editable");
        }
        return this;
    }

    /**
     * The user enter password
     */
    public LogInPage enterPassword(String password) {
        try {
            baseDriver.waitForElementVisible(passwordInputEle);
            baseDriver.inputText(passwordInputEle,password);
        } catch (Exception e) {
            baseDriver.captureFailedScenarioScreenshot("PasswordFieldIssue");
            Assert.fail("Password field is not editable");
        }

        return this;
    }

    /**
     * The user click on Login button
     */
    public LogInPage clickOnLoginInButton() {
        try {
            baseDriver.clickAndWait(loginButtonClickEle);
        } catch (Exception e) {
            baseDriver.captureFailedScenarioScreenshot("LoginButtonFieldIssue");
            Assert.fail("Login Button field is not clickable");
        }

        return this;
    }

}
