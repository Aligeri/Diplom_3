package model;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static util.ExpectedTexts.PASSWORD_RECOVERY;

public class ForgotPasswordPage {
    // Кнопка Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;

    // Нажать кнопку Войти
    public LoginPage clickLogin() {
        loginButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loadingLoginPage();
        return loginPage;
    }

    // Открылась страница восстановления пароля
    public void loadingForgotPasswordPage() {
        $(byText(PASSWORD_RECOVERY)).shouldBe(visible);
    }
}
