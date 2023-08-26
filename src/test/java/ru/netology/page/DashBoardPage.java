package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement head = $("[data-test-id='dashboard']");
    private ElementsCollection card = $$(".list__item");

    private ElementsCollection cardButton = $$("[data-test-id='action-deposit']");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashBoardPage() {
        head.shouldBe(Condition.visible);
    }


    public int getSelectedCardBalance(Integer id) {
        val text = card.get(id).text();
        return extractBalance(text);
    }

    public int getCardBalance(Integer id) {
        // TODO: перебрать все карты и найти по атрибуту data-test-id
        for (int i = id; i < card.size(); i++) {
            val text = card.get(i).text();
            return extractBalance(text);
        }
        return 0;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public TransactionPage cardSelection(Integer numCard) {
        cardButton.get(numCard).click();
        return new TransactionPage();
    }


    public void verifyDashboadrPage() {
        head.shouldBe(Condition.visible);
    }
}
