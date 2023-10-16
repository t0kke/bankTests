package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    SelenideElement
            headerBlock = $(".header__wrap"),
            headerLogo = $(".header__logo-img"),
            individualsButton = $(byText("Физическим лицам")),
            smallBusinessButton = $(byText("Малому бизнесу")),
            aboutBankButton = $(byText("О банке")),
            hiddenItemsButton = $("img[src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAxSURBVHgB7ckxCgAgEAPBFH4v/y/9SRBbQcTjyh1Ik5UA4J/tudfVxnkk0U21AcDDAoCDE5etukh7AAAAAElFTkSuQmCC']"),
            corporativeClientsButton = $(byText("Корпоративным клиентам")),
            sliderIndividualPage = $("div[class='slick-slide slick-active slick-current'] div div[class='slider--slide']");

    ElementsCollection secondHeaderBlock = $$(".header__secondary-menu");

    @Step("Открыть главную страницу сайта")
    public MainPage openMainPage() {
        open("");
        sliderIndividualPage.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличия всех разделов главного хээдера")
    public MainPage checkMainHeader() {
        headerBlock.shouldBe(visible);
        headerLogo.shouldBe(visible);
        individualsButton.shouldBe(visible);
        smallBusinessButton.shouldBe(visible);
        aboutBankButton.shouldBe(visible);
        hiddenItemsButton.shouldBe(visible).click();
        corporativeClientsButton.shouldBe(visible);
        return this;
    }

    @Step("Проверка ссылок главного хэддера")
    public MainPage checkLinksInButtons() {
        individualsButton.shouldHave(href("/ru/"));
        smallBusinessButton.shouldHave(href("/ru/small_business"));
        aboutBankButton.shouldHave(href("/ru/about"));
        hiddenItemsButton.shouldBe(visible).click();
        corporativeClientsButton.shouldHave(href("ru/business"));
        return this;
    }

    @Step("Проверка наличия всех пунктов второго хэддера")
    public MainPage checkSecondHeader(List<String> sectionSecondHeader) {
        sectionSecondHeader.forEach(element -> secondHeaderBlock.find(text(element)).shouldBe(visible));
        return this;
    }

}
