package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ProfilePage {

    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//span[contains(@class, 'd-icon_cross-big') and contains(@class, 'local-icon-theme-black')]")
    private WebElement closeAdYndxMusic;

    @FindBy(xpath = "//span[@class='button__label' and contains(text(), 'Войти')]")
    private WebElement loginBtnYndxMusic;

    @FindBy(css = "div.user__userpic-avatar")
    private WebElement profileBtn;

    @FindBy(css = "div.multi-auth__user-name")
    private WebElement userMenu;

    @FindBy(xpath = "//*[contains(text(), 'Выйти')]")
    private WebElement logoutLink;

    public void clickCloseAdYndxMusic() {
        closeAdYndxMusic.click(); }
    public void clickLoginBtnYndxMusic() {
        loginBtnYndxMusic.click(); }
    public void clickProfileBtn() {
        profileBtn.click(); }
    public String getUserName() {
        String userName = userMenu.getText();
        return userName; }
    public void logoutLink() {
        logoutLink.click(); }
    }
