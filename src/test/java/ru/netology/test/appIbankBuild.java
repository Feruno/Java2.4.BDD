package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.dataTest.DataHelp;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPageV1;


import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class appIbankBuild {

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

        var res = dashBoardPage.getCardBalance(0);

        var transactionPage = dashBoardPage.cardSelection(1);

        var updDashBoardPage = transactionPage.setInfoTransactionSecondCard(1_000, DataHelp.getFirstCardInfo());
        transactionPage.validTransaction();

        var balanceFirstCard = dashBoardPage.getCardBalance(0);
        var balanceSecondCard = dashBoardPage.getCardBalance(1);

        Assertions.assertEquals(balanceFirstCard, 9_000);

        Assertions.assertEquals(balanceSecondCard, 11_000);
    }
}
