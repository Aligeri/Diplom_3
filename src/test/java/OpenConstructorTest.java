import client.User;
import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.LoginPage;
import model.MainPage;
import model.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static generators.UserDataGenerator.createRandomUser;
import static org.junit.Assert.assertEquals;
import static util.Addresses.LOGIN_PAGE_URL;
import static util.Addresses.MAIN_PAGE_URL;

public class OpenConstructorTest {
    MainPage mainPage;
    LoginPage loginPage;
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
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void openMainPageWithConstructorButton() {
        loginPage = mainPage.clickHeaderButtonBeforeAuthorization();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickHeaderButtonAfterAuthorization();
        mainPage = profilePage.clickConstructor();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void openMainPageWithLogoStellarBurgers() {
        loginPage = mainPage.clickHeaderButtonBeforeAuthorization();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickHeaderButtonAfterAuthorization();
        mainPage = profilePage.clickLogoStellarBurgers();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }
}