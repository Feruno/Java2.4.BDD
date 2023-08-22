package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.dataTest.DataHelp;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeVerif = $("[data-test-id='code'] input");
    private SelenideElement buttonVerif = $("[data-test-id='action-verify'] span");

    public VerificationPage() {
        codeVerif.shouldBe(Condition.visible);
    }

    public DashBoardPage validVerify(DataHelp.VerificationCode verificationCode) {
        codeVerif.setValue(verificationCode.getCode());
        buttonVerif.click();
        return new DashBoardPage();
    }

    public DashBoardPage validVerifyForCucumber(String validCode) {
        codeVerif.setValue(validCode);
        buttonVerif.click();
        return new DashBoardPage();
    }
}
