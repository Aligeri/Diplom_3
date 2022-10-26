import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import model.MainPage;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static util.Addresses.MAIN_PAGE_URL;
import static util.ExpectedTexts.*;

public class ConstructorTabsTest {
    MainPage mainPage;

    @Before
    public void configuration() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @Test
    @DisplayName("Переход к разделу: «Булки»")
    public void openBunsTab() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.compareText(BURGER_BUNS);
    }

    @Test
    @DisplayName("Переход к разделу: «Соусы»")
    public void openSaucesTab() {
        mainPage.clickSaucesTab();
        mainPage.compareText(BURGER_SAUCES);
    }

    @Test
    @DisplayName("Переход к разделу: «Начинки»")
    public void openFillingsTab() {
        mainPage.clickFillingsTab();
        mainPage.compareText(BURGER_FILLINGS);
    }
}