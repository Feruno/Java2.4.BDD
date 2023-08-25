package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.dataTest.DataHelp;

import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {

    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement card = $("[data-test-id='from'] input");
    private SelenideElement actionTransfer = $("[data-test-id='action-transfer'] span");

    public TransactionPage() {
        amount.shouldBe(Condition.visible);
    }

    public int setInfoTransactionSecondCard(Integer amountTr, DataHelp.CardInfo cardNum) {
        amount.setValue(String.valueOf(amountTr));
        card.setValue(String.valueOf(cardNum));

        return 0;
    }

    public DashBoardPage validTransaction() {
        actionTransfer.click();
        return new DashBoardPage();
    }

}
