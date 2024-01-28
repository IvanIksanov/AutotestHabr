package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestYandexMusic {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver")); //определение пути до драйвера и его настройка
        driver = new ChromeDriver(); //создание экземпляра драйвера
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize(); //окно разворачивается на полный экран
        driver.get(ConfProperties.getProperty("Googletranslate"));
        driver.get(ConfProperties.getProperty("loginpage")); //получение ссылки на Яндекс Музыку из файла настроек
    }

    @Test
    public void loginTestYM() throws InterruptedException {

        profilePage.clickCloseAdYndxMusic();
        profilePage.clickLoginBtnYndxMusic();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginCnt();

        Thread.sleep(3000); // ожидаем аутентификацию прежде чем переключится на главную вкладку

        driver.switchTo().window(mainWindowHandle); // переключаемся обратно на главную вкладку

        driver.navigate().refresh(); // обновляем страницу
        WebDriverWait pageLoadWait = new WebDriverWait(driver, 10);
        pageLoadWait.until(ExpectedConditions.urlContains(ConfProperties.getProperty("loginpage"))); // ожидаем загрузку страницы

        profilePage.clickProfileBtn(); // открываем меню

        String user = profilePage.getUserName();
        Assert.assertEquals(ConfProperties.getProperty("login"), user);

        profilePage.clickProfileBtn(); // закрываем меню
    }

    @AfterClass
    public static void logOutSession() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 0.2 * window.innerHeight);");

        profilePage.clickProfileBtn(); // открываем меню
        profilePage.logoutLink(); // нажимаем выйти
    }
}