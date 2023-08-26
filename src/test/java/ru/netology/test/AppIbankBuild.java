package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.dataTest.DataHelp;
import ru.netology.page.LoginPageV1;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppIbankBuild {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void appSuccessfulPathManually() {
        var authInfo = DataHelp.getAuthInfo();
        var loginPage = new LoginPageV1();

        var verificationPage = loginPage.validLogin(authInfo);

        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        var transactionPage = dashBoardPage.cardSelection(1);

        transactionPage.setInfoTransactionSecondCard(1_000, DataHelp.getFirstCardInfo());
        transactionPage.validTransaction();

        var actualBalanceFirstCard = dashBoardPage.getCardBalance(0);
        var expectedBalanceFirstCard = dashBoardPage.getSelectedCardBalance(0);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);

        var actualBalanceSecondCard = dashBoardPage.getCardBalance(1);
        var expectedBalanceSecondCard = dashBoardPage.getSelectedCardBalance(1);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }
}
