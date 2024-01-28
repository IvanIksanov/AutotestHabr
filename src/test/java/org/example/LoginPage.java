package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;
    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement loginBtn;
    @FindBy(xpath = "//button[@id='passp:sign-in' and span[contains(text(), 'Продолжить')]]")
    private WebElement loginCnt;
    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwdField;


    public void inputLogin(String login) {
        Actions actions = new Actions(driver);
        loginField.click();
        actions.sendKeys(loginField, login).perform();
    }

    public void clickLoginBtn() {
        loginBtn.click(); }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd); }

    public void clickLoginCnt() {
        loginCnt.click(); }
    }