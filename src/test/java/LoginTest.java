import client.User;
import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static generators.UserDataGenerator.createRandomUser;
import static org.junit.Assert.assertEquals;
import static util.Addresses.LOGIN_PAGE_URL;
import static util.Addresses.MAIN_PAGE_URL;

public class LoginTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ForgotPasswordPage forgotPasswordPage;
    ProfilePage profilePage;
    User user;
    UserClient userClient;

    @Before
    public void configuration() {
        user = createRandomUser();
        userClient = new UserClient();
        userClient.createUser(user);
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void deleteUser() {
        profilePage = mainPage.clickHeaderButtonAfterAuthorization();
        profilePage.clickLogout();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
        closeWindow();
        userClient.deleteUser(user);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginByMainPageWithLoginButton() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginByMainPageWithHeaderButton() {
        loginPage = mainPage.clickHeaderButtonBeforeAuthorization();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginByRegisterPageWithLoginButton() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        loginPage = registerPage.clickLoginAndLogin();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginByForgotPasswordPageWithLoginButton() {
        loginPage = mainPage.clickLoginButton();
        forgotPasswordPage = loginPage.clickRecoverPassword();
        loginPage = forgotPasswordPage.clickLogin();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }
}