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
import static util.ExpectedTexts.REGISTRATION;

public class RegisterPage {
    // Поля Имя, Email и Пароль
    @FindBy(how = How.CLASS_NAME, using = "input__textfield")
    private ElementsCollection fields;

    // Кнопка Зарегистрироваться
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButton;

    // Текст Некорректный пароль
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement invalidPasswordText;

    // Ссылка на страницу логина по кнопке Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginLink;

    // Заполнить Имя
    public void setName(String inputName) {
        fields.get(0).setValue(inputName);
    }

    // Заполнить Email
    public void setEmail(String inputEmail) {
        fields.get(1).setValue(inputEmail);
    }

    // Заполнить Пароль
    public void setPassword(String inputPassword) {
        fields.get(2).setValue(inputPassword);
    }

    // Нажать кнопку Зарегистрироваться
    public void clickRegisterButton() {
        registerButton.click();
    }

    // Нажать кнопку зарегистрироваться и подождать загрузку страницы логина
    public void clickRegisterButtonAndLogin() {
        registerButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
    }

    // Нажать кнопку Войти и подождать загрузку страницы логина
    public LoginPage clickLoginAndLogin() {
        loginLink.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
        return loginPage;
    }

    // Сравнить полученный текст с ожидаемым
    public void compareText(String expectedText) {
        invalidPasswordText.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    // Открылась страница регистрации
    public void loadingRegisterPage() {
        $(byText(REGISTRATION)).shouldBe(visible);
    }
}