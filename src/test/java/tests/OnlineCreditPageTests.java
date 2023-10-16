package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OnlineCreditPageTests extends BaseTest {
    @DisplayName("Проверка вычисления ежемесячного платежа и переплаты в кредитном калькуляторе")
    @ParameterizedTest
    @CsvSource(value = {
            "200000, 12",
            "1000000, 12",
            "8000000, 12"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void checkOnlineCalculator(int creditAmount, int numberOfMonths) {
        int calculatedMonthlyPayment = onlineCreditPage.monthlyPaymentCalculation(creditAmount, numberOfMonths);
        int calculatedTotalOverpayment = onlineCreditPage.totalOverpayment(calculatedMonthlyPayment, numberOfMonths, creditAmount);

        onlineCreditPage
                .openCreditPage("/onlinecredit_inhouse")
                .transitionToCreditCalculator()
                .checkCreditAmountField()
                .prefillVerificationForm();
        onlineCreditPage.paymentAndLoanTerm(creditAmount, numberOfMonths);

        assertEquals(Integer.parseInt(onlineCreditPage.getValueMonthlyPayment()), calculatedMonthlyPayment,
                "Сумма месячного платежа не соответствует расчетной");
        assertEquals(Integer.parseInt(onlineCreditPage.getValueTotalOverpayment()), calculatedTotalOverpayment,
                "Сумма общей переплаты не соотвествует расчетной");


    }
}
