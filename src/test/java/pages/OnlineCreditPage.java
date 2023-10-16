package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OnlineCreditPage {
    SelenideElement
            mainTitle = $(".hero__title.title-l1"),
            calculateCreditButton = $(".hero__btn.btn"),
            creditCalculatorTitle = $(".form__title.title-l2"),
            creditAmountField = $("input[class='form__input input form__input--visible']"),
            creditPeriodField = $("input[class='form__input input']"),
            typeOfCreditRadioCredit = $x("//div[@class='onlinecredit_inhouse page']//*[.='Кредит']/../input"),
            payrollWithThisBankRadio = $x("//div[@class='onlinecredit_inhouse page']//*[.='Да']/../input"),
            equalPaymentsRadio = $x("//div[@class='onlinecredit_inhouse page']//*[.='Равными платежами']/../input"),
            monthlyPaymentField = $(".form__result-num"),
            totalOverpaymentField = $("div[class='form__overpayment form__overpayment--any'] p[class='form__overpayment-num']");

    private static double getInterestRate(int numberOfMonths) {
        double interestRate = 0;
        if (numberOfMonths == 7) {
            interestRate = 8;
        }
        if (numberOfMonths > 7 && numberOfMonths <= 12) {
            interestRate = 15.99;
        }
        if (numberOfMonths > 13 && numberOfMonths <= 24) {
            interestRate = 22.99;
        }
        if (numberOfMonths > 25 && numberOfMonths <= 36) {
            interestRate = 24.87;
        }
        if (numberOfMonths > 37 && numberOfMonths <= 48) {
            interestRate = 24.87;
        }
        if (numberOfMonths > 49 && numberOfMonths <= 60) {
            interestRate = 24.87;
        }
        return interestRate;
    }

    @Step("Открыть страницу 'Кредит без залога' по урлу: {url}")
    public OnlineCreditPage openCreditPage(String url) {
        open(url);
        mainTitle.shouldBe(visible).shouldHave(exactText("Кредит без залога онлайн"));
        return this;
    }

    @Step("Переход на блок калькулятора кредита")
    public OnlineCreditPage transitionToCreditCalculator() {
        calculateCreditButton.click();
        creditCalculatorTitle.shouldHave(visible).shouldHave(text("Кредитный калькулятор"));
        return this;
    }

    @Step("Проверка полей 'Сумма кредита' и 'На срок' предзаполненными данными")
    public OnlineCreditPage checkCreditAmountField() {
        creditAmountField.shouldBe(visible).shouldHave(exactValue("150000"));
        creditPeriodField.shouldBe(visible).shouldHave(exactValue("7"));
        return this;
    }

    @Step("Проверка предзаполнения всех полей калькулятора кредитов")
    public OnlineCreditPage prefillVerificationForm() {
        typeOfCreditRadioCredit.shouldBe(checked);
        payrollWithThisBankRadio.shouldBe(checked);
        equalPaymentsRadio.shouldBe(checked);
        creditAmountField.shouldBe(visible).shouldHave(exactValue("150000"));
        creditPeriodField.shouldBe(visible).shouldHave(exactValue("7"));
        monthlyPaymentField.shouldBe(visible);
        return this;
    }

    public int monthlyPaymentCalculation(int creditAmount, int numberOfMonths) {
        double interestRate = getInterestRate(numberOfMonths);
        double monthlyInterestRate = (interestRate / numberOfMonths) / 100;
        double annuityRatio;
        annuityRatio = (monthlyInterestRate * Math.pow(1.0 + monthlyInterestRate, numberOfMonths))
                / (Math.pow(1.0 + monthlyInterestRate, numberOfMonths) - 1.0);
        return (int) (creditAmount * annuityRatio);
    }

    public int totalOverpayment(int monthlyPayment, int numberOfMonths, int creditAmount) {
        return (monthlyPayment * numberOfMonths) - creditAmount;
    }

    @Step("Заполнение полей 'Сумма кредита' = {creditAmount} и 'На срок' = {numberOfMonths} в калькуляторе кредита")
    public OnlineCreditPage paymentAndLoanTerm(int creditAmount, int numberOfMonths) {
        creditAmountField.clear();
        creditAmountField.val(String.valueOf(creditAmount));
        creditPeriodField.clear();
        creditPeriodField.val(String.valueOf(numberOfMonths));
        creditPeriodField.pressEnter();
        return this;
    }


    public String getValueTotalOverpayment() {
        String valueTotalOverpayment;
        valueTotalOverpayment = (totalOverpaymentField.shouldBe(visible).getText())
                .replaceAll(" ", "").replaceAll("₸", "");
        return valueTotalOverpayment;
    }

    public String getValueMonthlyPayment() {
        String valueMonthlyPayment;
        valueMonthlyPayment = (monthlyPaymentField.shouldBe(visible).getText())
                .replaceAll(" ", "").replaceAll("₸", "");
        return valueMonthlyPayment;
    }

}


