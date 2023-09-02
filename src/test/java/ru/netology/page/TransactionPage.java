package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.dataTest.DataHelp;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {

    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement card = $("[data-test-id='from'] input");
    private final SelenideElement actionTransfer = $("[data-test-id='action-transfer'] span");
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransactionPage() {
        amount.shouldBe(Condition.visible);
    }

    public void setInfoTransactionSecondCard(Integer amountTr, DataHelp.CardInfo cardNum) {
        amount.setValue(String.valueOf(amountTr));
        card.setValue(String.valueOf(cardNum));
    }


    public DashBoardPage validTransaction() {
        actionTransfer.click();
        return new DashBoardPage();
    }

    public void findErrorMessage(String expectedText){
        errorMessage.shouldHave(Condition.exactText(expectedText), Duration.ofSeconds(15) ).shouldBe(Condition.visible);
    }

}
