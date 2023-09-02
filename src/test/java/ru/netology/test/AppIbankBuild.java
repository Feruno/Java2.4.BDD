package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.dataTest.DataHelp;
import ru.netology.page.LoginPageV1;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.dataTest.DataHelp.generateInvalidAmount;
import static ru.netology.dataTest.DataHelp.generateValidAmount;

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

        var balanceFirstCard = dashBoardPage.getSelectedCardBalance(0);
        var balanceSecondCard = dashBoardPage.getSelectedCardBalance(1);

        var sumTransaction = 1_000;

        var generSumTransaction = generateValidAmount(balanceFirstCard);

        var transactionPage = dashBoardPage.cardSelection(1);

        transactionPage.setInfoTransactionSecondCard(generSumTransaction, DataHelp.getFirstCardInfo());

        transactionPage.validTransaction();

        var actualBalanceFirstCard = dashBoardPage.getSelectedCardBalance(0);
        var expectedBalanceFirstCard = balanceFirstCard - generSumTransaction;
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);

        var actualBalanceSecondCard = dashBoardPage.getSelectedCardBalance(1);
        var expectedBalanceSecondCard = balanceSecondCard + generSumTransaction;
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void invalidAmountTransaction() {
        var authInfo = DataHelp.getAuthInfo();
        var loginPage = new LoginPageV1();

        var verificationPage = loginPage.validLogin(authInfo);

        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        var balanceFirstCard = dashBoardPage.getSelectedCardBalance(0);
        var balanceSecondCard = dashBoardPage.getSelectedCardBalance(1);

        var sumTransaction = generateInvalidAmount(balanceFirstCard);

        var transactionPage = dashBoardPage.cardSelection(1);

        transactionPage.setInfoTransactionSecondCard(sumTransaction, DataHelp.getFirstCardInfo());

        transactionPage.validTransaction();

        var actualBalanceFirstCard = dashBoardPage.getSelectedCardBalance(0);
        var expectedBalanceFirstCard = balanceFirstCard - sumTransaction;
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);

        var actualBalanceSecondCard = dashBoardPage.getSelectedCardBalance(1);
        var expectedBalanceSecondCard = balanceSecondCard + sumTransaction;
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }


}
