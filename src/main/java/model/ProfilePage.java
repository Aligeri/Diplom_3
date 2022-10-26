package model;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static util.ExpectedTexts.EXIT;

public class ProfilePage {

    // Кнопка Выход
    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement exitButton;

    // Кнопка Конструктор
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private SelenideElement constructorButton;

    // Логотип StellarBurgers
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoStellarBurgers;

    // Нажать кнопку Выход
    public LoginPage clickLogout() {
        exitButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
        return loginPage;
    }

    // Нажать кнопку Конструктор
    public MainPage clickConstructor() {
        constructorButton.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.loadingMainPage();
        return mainPage;
    }

    // Нажать на логотип с текстом StellarBurgers
    public MainPage clickLogoStellarBurgers() {
        logoStellarBurgers.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.loadingMainPage();
        return mainPage;
    }

    // Открылась страница профиля пользователя
    public void loadingProfilePage() {
        $(byText(EXIT)).shouldBe(visible);
    }
}