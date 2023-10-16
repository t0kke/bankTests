package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.MainPage;
import pages.OnlineCreditPage;
import testData.TestData;

public class BaseTest {
    MainPage mainPage = new MainPage();
    TestData testData = new TestData();
    OnlineCreditPage onlineCreditPage = new OnlineCreditPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://berekebank.kz/ru";
        Configuration.browser = "Chrome";
        Configuration.browserVersion = "117";
        Configuration.browserSize = "1920x1080";
    }
}
