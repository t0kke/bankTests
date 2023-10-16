package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainPageTests extends BaseTest {

    @Test
    @DisplayName("Проверка основного хэддера и его ссылок")
    void checkMainMenu() {
        mainPage.openMainPage()
                .checkMainHeader()
                .checkLinksInButtons();
    }

    @Test
    @DisplayName("Проверка наличия всех элементов во втором уровне хэддера")
    void checkSecondMenu() {
        mainPage.openMainPage()
                .checkSecondHeader(testData.titlesSecondHeader);
    }
}
