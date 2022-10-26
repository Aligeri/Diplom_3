package model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static util.ExpectedTexts.CREATE_BURGER;

public class MainPage {

    // Кнопоки в шапке
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private ElementsCollection headerButtons;

    // Кнопка Войти в аккаунт
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    // Вкладки Булки, Соусы, Начинки
    @FindBy(how = How.CLASS_NAME, using = "tab_tab__1SPyG")
    private ElementsCollection tabs;

    // Открытая вкладка
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    private SelenideElement currentTab;

    // Нажать кнопку Войти в аккаунт
    public LoginPage clickLoginButton() {
        loginButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
        return loginPage;
    }

    // Нажать кнопку Личный кабинет до авторизации
    public LoginPage clickHeaderButtonBeforeAuthorization() {
        headerButtons.get(2).click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
        return loginPage;
    }

    // Нажать кнопку Личный кабинет после авторизации
    public ProfilePage clickHeaderButtonAfterAuthorization() {
        headerButtons.get(2).click();
        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.loadingProfilePage();
        return profilePage;
    }

    // Выбрать вкладку Булки
    public void clickBunsTab() {
        tabs.get(0).click();
    }

    // Выбрать вкладку Соусы
    public void clickSaucesTab() {
        tabs.get(1).click();
    }

    // Выбрать вкладку Начинки
    public void clickFillingsTab() {
        tabs.get(2).click();
    }

    // Сравнить полученный текст с ожидаемым
    public void compareText(String expectedText) {
        currentTab.shouldHave(exactText(expectedText));
    }

    // Открылась главная страница
    public void loadingMainPage() {
        $(byText(CREATE_BURGER)).shouldBe(visible);
    }
}