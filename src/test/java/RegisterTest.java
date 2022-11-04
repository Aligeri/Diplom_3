import client.User;
import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.LoginPage;
import model.MainPage;
import model.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static generators.UserDataGenerator.createRandomUser;
import static generators.UserDataGenerator.createRandomUserWithIncorrectPassword;
import static org.junit.Assert.assertEquals;
import static util.Addresses.LOGIN_PAGE_URL;
import static util.Addresses.MAIN_PAGE_URL;
import static util.ExpectedTexts.DELETE_USER_ERROR;
import static util.ExpectedTexts.REGISTRATION_INCORRECT_PASSWORD;

public class RegisterTest {
    MainPage mainPage;
    RegisterPage registerPage;
    LoginPage loginPage;
    User user;
    User userWithInvalidPassword;
    UserClient userClient;

    @Before
    public void configuration() {
        user = createRandomUser();
        userWithInvalidPassword = createRandomUserWithIncorrectPassword();
        userClient = new UserClient();
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void deleteUser() {
        closeWindow();
        try {
            userClient.deleteUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println(DELETE_USER_ERROR);
        }
    }
    @Test
    @DisplayName("Успешная регистрация")
    public void correctRegisteration() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButtonAndLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void incorrectRegistrationWithInvalidPassword() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        registerPage.setName(userWithInvalidPassword.getName());
        registerPage.setEmail(userWithInvalidPassword.getEmail());
        registerPage.setPassword(userWithInvalidPassword.getPassword());
        registerPage.clickRegisterButton();
        registerPage.compareText(REGISTRATION_INCORRECT_PASSWORD);
        try {
            userClient.deleteUser(userWithInvalidPassword);
        } catch (IllegalArgumentException e) {
            System.out.println(DELETE_USER_ERROR);
        }
    }
}