package model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static util.ExpectedTexts.ENTER;

public class LoginPage {

    // Поля Email и Пароль
    @FindBy(how = How.CLASS_NAME, using = "input__textfield")
    private ElementsCollection fields;

    // Кнопка Войти
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    // Ссылки на страницы Зарегистрироваться и Восстановить пароль
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private ElementsCollection links;

    // Заполнить Email
    public void setEmail(String email) {
        fields.get(0).setValue(email);
    }

    // Заполнить Пароль
    public void setPassword(String password) {
        fields.get(1).setValue(password);
    }

    // Нажать кнопку Войти
    public MainPage clickLogin() {
        loginButton.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.loadingMainPage();
        return mainPage;
    }

    // Нажать кнопку Зарегистрироваться
    public RegisterPage clickRegister() {
        links.get(0).click();
        RegisterPage registerPage = Selenide.page(RegisterPage.class);
        registerPage.loadingRegisterPage();
        return registerPage;
    }

    // Нажать кнопку Восстановить пароль
    public ForgotPasswordPage clickRecoverPassword() {
        links.get(1).click();
        ForgotPasswordPage forgotPasswordPage = Selenide.page(ForgotPasswordPage.class);
        forgotPasswordPage.loadingForgotPasswordPage();
        return forgotPasswordPage;
    }

    // Открылась страница авторизации
    public void loadingLoginPage() {
        $(byText(ENTER)).shouldBe(visible);
    }
}